package com.example.handybook

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.handybook.databinding.FragmentSplashBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentSplashBinding.inflate(layoutInflater)
        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val users = shared.getString("users", "")
        val isLoggedOut = shared.getBoolean("isLoggedOut", false)
        Handler(Looper.getMainLooper()).postDelayed({


            Handler(Looper.getMainLooper()).postDelayed({
                if (users == "") {
                    findNavController().navigate(R.id.splashScreen)
                } else if (isLoggedOut) {
                    findNavController().navigate(R.id.loginFragment)
                } else {
                    findNavController().navigate(R.id.mainFragment)
                }
            }, 3000)
        }, 2500)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}