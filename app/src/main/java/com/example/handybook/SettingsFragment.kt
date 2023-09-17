package com.example.handybook

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.handybook.Login_Reg.User
import com.example.handybook.databinding.FragmentSettingsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    lateinit var shared: SharedPreferences
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
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val gson = Gson()
        val userJson = shared.getString("active_user", null)
        val user: User = gson.fromJson(userJson, User::class.java)

        binding.logOutBtn.setOnClickListener {
            logoutDialog()
        }
        binding.personName.text = user.username
        Log.d("BBB", user.username)



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun logoutDialog() {
        val bottomSheetView = layoutInflater.inflate(R.layout.logout_sheet, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(bottomSheetView)

        val cancelButton: MaterialButton = bottomSheetView.findViewById(R.id.cancel_button)
        val logoutButton: MaterialButton = bottomSheetView.findViewById(R.id.logout_button)

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        logoutButton.setOnClickListener {
            shared.edit().putBoolean("isLoggedOut", true).apply()

            findNavController().popBackStack(R.id.mainFragment, true)
            findNavController().navigate(R.id.splashScreen)
            dialog.dismiss()
        }

        dialog.show()
    }
}