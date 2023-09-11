package com.example.handybook.Login_Reg

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.handybook.R
import com.example.handybook.databinding.FragmentRegistrationBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@SuppressLint("StaticFieldLeak")
class RegistrationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)
        binding.royhatdanOtishBtn.setOnClickListener {
            if (binding.ismReg.text!!.isNotEmpty() && binding.familiyaReg.text!!.isNotEmpty() && binding.emailReg.text!!.isNotEmpty() && binding.parolReg.text!!.isNotEmpty() && binding.parolCheckReg.text!!.isNotEmpty()) {
                Toast.makeText(requireContext(), "Successfully Registered :)", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_registrationFragment_to_boshSahifaFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please, complete all prompts above!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.backToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}