package com.example.handybook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.handybook.Login_Reg.User
import com.example.handybook.databinding.FragmentMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var shared: SharedPreferences
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
        binding = FragmentMainBinding.inflate(layoutInflater)
        loadFragment(BoshSahifaFragment())
        shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()
//        val userName = shared.getString("userName", "")
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(BoshSahifaFragment())
                    true
                }

                R.id.search -> {
                    loadFragment(SearchFragment())
                    true
                }

                R.id.saved -> {
                    loadFragment(SavedFragment())
                    true
                }

                R.id.settings -> {
                    loadFragment(SettingsFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }

        drawer = binding.drawerLayout
        val navigationView = binding.navView

        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        val toggle = ActionBarDrawerToggle(
            requireActivity(),
            binding.drawerLayout,
            R.string.open,
            R.string.close
        )
//        activity.supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, BoshSahifaFragment()).commit();
            navigationView.setCheckedItem(R.id.home_nav);
        }

        val header = binding.navView.getHeaderView(0)
        val userJson = shared.getString("active_user", null)
        val user: User = gson.fromJson(userJson, User::class.java)
        header.findViewById<TextView>(R.id.user_name).text =
            user.username
        binding.toolbar.title = "Bosh sahifa"
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_nav -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment()).commit()
                binding.bottomNavigationView.selectedItemId = R.id.home
            }

            R.id.search_nav -> {
                Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchFragment()).commit()
                binding.bottomNavigationView.selectedItemId = R.id.search
            }

            R.id.saved_nav -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SavedFragment()).commit()
                binding.bottomNavigationView.selectedItemId = R.id.saved
            }

            R.id.lang_nav -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SettingsFragment()).commit()
                binding.bottomNavigationView.selectedItemId = R.id.settings
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}