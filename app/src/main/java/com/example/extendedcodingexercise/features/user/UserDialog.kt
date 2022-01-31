package com.example.extendedcodingexercise.features.user

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.extendedcodingexercise.databinding.DialogUserBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDialog : BottomSheetDialogFragment() {
    private var _binding: DialogUserBinding? = null
    private val binding get() = _binding!!
    private val args: UserDialogArgs by navArgs()
    private lateinit var lat: String
    private lateinit var lng: String

    @Inject
    lateinit var viewModelAssistedFactory: UserViewModel.Factory
    private val viewModel: UserViewModel by viewModels {
        UserViewModel.provideFactory(viewModelAssistedFactory, args.userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.photos.observe(viewLifecycleOwner, {
            binding.udTvName.text = it.name
            binding.udTvCompany.text = it.companyName
            binding.udTvAddress.text = it.city
            binding.udTvEmail.text = it.email
            binding.udTvWebsite.text = it.website
            binding.udUserPhone.text = it.phone

        })

        binding.ivEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(binding.udTvEmail.text.toString()))
            }
            try {
                startActivity(Intent.createChooser(intent, "p"))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "No apps can perform this action.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.ivAddress.setOnClickListener {
            val gmmIntentUri: Uri = Uri.parse("google.streetview:cbll=$lat,$lng")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            try {
                startActivity(mapIntent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "No apps can perform this action.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.ivPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$binding.udUserPhone.text.toString()")
            }
            try {
                startActivity(intent)
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(),
                    "No apps can perform this action.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.ivWebsite.setOnClickListener {
            var url = binding.udTvWebsite.text.toString().trim()
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            val webpage: Uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            try {
                startActivity(Intent.createChooser(intent, ""))
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(),
                    "No apps can perform this action.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.udTvEmail.setOnClickListener {

        }

    }
}