package com.example.handybook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.handybook.booktypeRV.BookTypeData
import com.example.handybook.booktypeRV.MyAdapterType
import com.example.handybook.databinding.FragmentBoshSahifaBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BoshSahifaFragment : Fragment(){
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBoshSahifaBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var typesArray: ArrayList<BookTypeData>
    private lateinit var adapter: MyAdapterType
    lateinit var typesName: ArrayList<String>
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
        binding = FragmentBoshSahifaBinding.inflate(layoutInflater)
        data()
        binding.typeRV.setHasFixedSize(true)
        adapter = MyAdapterType(typesArray)
        binding.typeRV.adapter = adapter

            toggle= ActionBarDrawerToggle(activity,binding.drawerLayout,R.string.open,R.string.close)
            binding.drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BoshSahifaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun data() {

        typesArray = arrayListOf()

        typesArray.add(BookTypeData("Darsliklar"))
        typesArray.add(BookTypeData("Diniy Kitoblar"))
        typesArray.add(BookTypeData("Bepul Kitoblar"))
        typesArray.add(BookTypeData("Romanlar"))
        typesArray.add(BookTypeData("Darsliklar"))
        typesArray.add(BookTypeData("Diniy Kitoblar"))
        typesArray.add(BookTypeData("Bepul Kitoblar"))
        typesArray.add(BookTypeData("Romanlar"))

    }
}


