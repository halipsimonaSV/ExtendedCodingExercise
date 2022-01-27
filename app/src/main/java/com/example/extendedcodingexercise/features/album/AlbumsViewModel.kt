package com.example.extendedcodingexercise.features.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.extendedcodingexercise.R
import com.example.extendedcodingexercise.data.repository.AlbumsRepository
import com.example.extendedcodingexercise.domain.Album
import com.example.extendedcodingexercise.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(private val albumsRepository: AlbumsRepository) :
    ViewModel() {
    val albums: LiveData<List<Album>> = albumsRepository.albumList

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _error = MutableLiveData<Event<Int>>()
    val error: LiveData<Event<Int>>
        get() = _error

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError

    private val _showPB = MutableLiveData<Boolean>()
    val showPB: LiveData<Boolean>
        get() = _showPB

    init {
        refreshAlbumList()
    }

    fun refreshAlbumList() {
        viewModelScope.launch {
            try {
                if (albumsRepository.albumList.value.isNullOrEmpty()) {
                    _showPB.value = true
                }
                _showError.value = false
                albumsRepository.getAlbums()
            } catch (ex: Exception) {
                ex.printStackTrace()
                when (ex) {
                    is IllegalArgumentException -> {
                        _showError.value = true
                        _error.setValue(Event(R.string.cannot_load_albums))
                    }
                    is HttpException -> {
                        _error.value = Event(R.string.api_problem)
                    }
                }
            } finally {
                _isRefreshing.value = false
                _showPB.value = false
            }
        }
    }
}