package com.example.extendedcodingexercise.features.album

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.extendedcodingexercise.databinding.FragmentAlbumsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsFragment : Fragment() {
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AlbumsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AlbumsAdapter(requireContext()){userId->
            findNavController().navigate(AlbumsFragmentDirections.actionAlbumsFragmentToUserDialog(userId))
        }
        binding.rvAlbums.adapter = adapter

        binding.swlRefreshAlbums.setOnRefreshListener {
            viewModel.refreshAlbumList()
        }

        viewModel.albums.observe(
            viewLifecycleOwner, { albums ->
                adapter.submitList(albums)
            }
        )

        viewModel.isRefreshing.observe(
            viewLifecycleOwner, { isRefreshing ->
                binding.swlRefreshAlbums.isRefreshing = isRefreshing
            }
        )

        viewModel.error.observe(
            viewLifecycleOwner, { error ->
                error.getContentIfNotHandled()
                    ?.let {
                        Toast.makeText(
                            requireContext(),
                            requireContext().getString(error.peekContent()),
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        )

        viewModel.showPB.observe(
            viewLifecycleOwner, { showPB ->
                if (showPB) {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.group.visibility = View.GONE
                    binding.rvAlbums.visibility = View.GONE
                } else {
                    binding.progressBar.visibility = View.GONE
                }
            }
        )
        viewModel.showError.observe(
            viewLifecycleOwner, { showError ->
                if (showError) {
                    binding.group.visibility = View.VISIBLE
                    binding.rvAlbums.visibility = View.GONE
                } else {
                    binding.group.visibility = View.GONE
                    binding.rvAlbums.visibility = View.VISIBLE
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
