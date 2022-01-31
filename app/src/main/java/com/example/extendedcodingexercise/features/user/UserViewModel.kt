package com.example.extendedcodingexercise.features.user

import androidx.lifecycle.*
import com.example.extendedcodingexercise.R
import com.example.extendedcodingexercise.domain.User
import com.example.extendedcodingexercise.util.Event
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception
import java.lang.IllegalArgumentException

class UserViewModel @AssistedInject constructor(
    @Assisted private val userId: Int,
    private val userRepositoryImp: UserRepositoryImp
) : ViewModel() {

    private val _error = MutableLiveData<Event<Int>>()
    val error: LiveData<Event<Int>>
        get() = _error

    val photos: LiveData<User> = userRepositoryImp.getUser(userId)
    init {
        viewModelScope.launch {
            try {
                userRepositoryImp.refresh(userId)
            } catch (ex: Exception) {
                ex.printStackTrace()
                when (ex) {
                    is IllegalArgumentException ->
                        _error.value = Event(R.string.app_name)
                    is HttpException ->
                        _error.value = Event(R.string.user_avatar)
                }
            }
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(userId: Int): UserViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            userId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(userId) as T
            }
        }
    }
}