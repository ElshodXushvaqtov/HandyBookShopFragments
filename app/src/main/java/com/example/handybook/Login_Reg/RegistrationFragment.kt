package com.example.handybook.Login_Reg

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.handybook.R
import com.example.handybook.api.ApiClient
import com.example.handybook.api.ApiService
import com.example.handybook.databinding.FragmentRegistrationBinding
import com.example.handybook.module.User
import com.example.handybook.module.UserToken
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@SuppressLint("StaticFieldLeak")
class RegistrationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var user: User

    private var userList = mutableListOf<User>()
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(layoutInflater)


        val shared = requireContext().getSharedPreferences("shared", AppCompatActivity.MODE_PRIVATE)
        val edit = shared.edit()
        val gson = Gson()
        val convert = object : TypeToken<List<User>>() {}.type


        val dialogBinding = layoutInflater.inflate(R.layout.custom_dialog, null)
        val myDialog = Dialog(requireContext())
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        binding.backToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
        userList = mutableListOf()
        binding.royhatdanOtishBtn.setOnClickListener {

            val users = shared.getString("users", "")

            user = User(
                binding.ismReg.text.toString(),
                binding.parolReg.text.toString(),
                binding.emailReg.text.toString(),
            )

            if (check()) {
//                userList.add(user)
//                val s = gson.toJson(user)
//                edit.putString("users", s).apply()
//                shared.edit().putBoolean("isLoggedOut", false).apply()
                myDialog.show()
                val api = ApiClient.getInstance().create(ApiService::class.java)

                api.register(user).enqueue(object : Callback<UserToken> {
                    override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                        val bundle = bundleOf("person" to user)
                        Handler(Looper.getMainLooper()).postDelayed({
                            findNavController().navigate(
                                R.id.action_registrationFragment_to_mainFragment,
                                bundle
                            )
//                    shared.edit().putString("active_user", gson.toJson(user)).apply()
//                    Log.d("AAA", user.username)
//                    Log.d("AAB", s)
//                    Log.d("AAC", user.email)
//                    Log.d("BBC", userList.toString())
                            myDialog.dismiss()
                        }, 2000)
                    }

                    override fun onFailure(call: Call<UserToken>, t: Throwable) {
                        Log.d("AAA", "onFailure: $t")
                    }
                })


            }
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

    private fun check(): Boolean {
        if (binding.ismReg.text.toString().isEmpty() || binding.parolReg.text.toString()
                .isEmpty() || binding.emailReg.text.toString()
                .isEmpty() || binding.parolCheckReg.text.toString().isEmpty()
        ) {
            Toast.makeText(requireContext(), "Complete all gaps!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.parolReg.text.toString() != binding.parolCheckReg.text.toString()) {
            Toast.makeText(requireContext(), "Your password isn't the same!", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        for (i in userList.indices) {
            if (binding.ismReg.text.toString() == userList[i].username) {
                Toast.makeText(
                    requireContext(),
                    "User with this username already registered",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
        return true
    }
}

