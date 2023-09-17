package com.example.handybook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.handybook.Barchasi.BarchasiData
import com.example.handybook.Barchasi.MyAdapterBarchasi
import com.example.handybook.Darsliklar.DarsliklarData
import com.example.handybook.databinding.FragmentBarchasiBinding
import com.example.handybook.module.Book

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BarchasiFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBarchasiBinding
    private lateinit var barchasiArr: ArrayList<BarchasiData>

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
        binding = FragmentBarchasiBinding.inflate(layoutInflater)
        if (arguments?.containsKey("Romanlar") == true) {
            binding.romanlarTxt.text = "Romanlar"
            loadRomanlar()
            binding.barchasiRv.setHasFixedSize(true)
            binding.barchasiRv.layoutManager = GridLayoutManager(requireParentFragment().context, 2)
            binding.barchasiRv.adapter = MyAdapterBarchasi(barchasiArr, requireContext())
            back()
            return binding.root
        }

        if (arguments?.containsKey("Darsliklar") == true) {
            binding.romanlarTxt.text = "Darsliklar"
            loadDarsliklar()
            binding.barchasiRv.setHasFixedSize(true)
            binding.barchasiRv.layoutManager = GridLayoutManager(requireParentFragment().context, 2)
            binding.barchasiRv.adapter = MyAdapterBarchasi(barchasiArr, requireContext())
            back()
            return binding.root

        } else if (arguments?.containsKey("barchasi") == true) {
            binding.romanlarTxt.text = "Barchasi"
            loadAll()
            binding.barchasiRv.setHasFixedSize(true)
            binding.barchasiRv.layoutManager = GridLayoutManager(requireParentFragment().context, 2)
            binding.barchasiRv.adapter = MyAdapterBarchasi(barchasiArr, requireContext())
            back()
            return binding.root
        }
        back()
        binding.barchasiRv.visibility = View.GONE
        binding.notFound.visibility = View.VISIBLE



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BarchasiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun back() {
        binding.backFromRomanlar.setOnClickListener {
            findNavController().navigate(R.id.mainFragment)
        }
    }

    private fun loadDarsliklar() {

        barchasiArr = arrayListOf()

        barchasiArr.add(BarchasiData("Algebra", R.drawable.algebra, "Davlat", "$10.00", "4.9"))
        barchasiArr.add(BarchasiData("Fizika", R.drawable.fizika, "Nyuton", "$11.00", "4.8"))
        barchasiArr.add(BarchasiData("Ona tili", R.drawable.ona_tili, "Navoiy", "$9.80", "4.75"))


    }

    private fun loadRomanlar() {

        barchasiArr = arrayListOf()

        barchasiArr.add(
            BarchasiData(
                "O'tkan kunlar",
                R.drawable.algebra,
                "Abdulla Qodiriy",
                "$12.00",
                "5.0"
            )
        )
        barchasiArr.add(
            BarchasiData(
                "Ikki eshik orasi",
                R.drawable.ikkieshik_orasi_big,
                "O'tkir Hoshimov",
                "$11.32",
                "4.9"
            )
        )
        barchasiArr.add(
            BarchasiData(
                "Yulduzli tunlar",
                R.drawable.yulduzli_tunlar_big,
                "Primqul Qodirov",
                "$10.65",
                "4.7"
            )
        )

    }

    private fun loadAll() {
        barchasiArr = arrayListOf()

        barchasiArr.add(
            BarchasiData(
                "Algebra",
                R.drawable.algebra,
                "Abdulla Qodiriy",
                "$12.00",
                "5.0"
            )
        )
        barchasiArr.add(
            BarchasiData(
                "Ikki eshik orasi",
                R.drawable.ikkieshik_orasi_big,
                "O'tkir Hoshimov",
                "$11.32",
                "4.9"
            )
        )
        barchasiArr.add(
            BarchasiData(
                "Yulduzli tunlar",
                R.drawable.yulduzli_tunlar_big,
                "Primqul Qodirov",
                "$10.65",
                "4.7"
            )
        )
        barchasiArr.add(
            BarchasiData(
                "O'tkan kunlar",
                R.drawable.otkankunlar_big,
                "Abdulla Qodiriy",
                "$12.00",
                "5.0"
            )
        )
        barchasiArr.add(
            BarchasiData(
                "Ikki eshik orasi",
                R.drawable.ikkieshik_orasi_big,
                "O'tkir Hoshimov",
                "$11.32",
                "4.9"
            )
        )
        barchasiArr.add(
            BarchasiData(
                "Yulduzli tunlar",
                R.drawable.yulduzli_tunlar_big,
                "Primqul Qodirov",
                "$10.65",
                "4.7"
            )
        )
        barchasiArr.add(BarchasiData("Algebra", R.drawable.algebra, "Davlat", "$10.00", "4.9"))
        barchasiArr.add(BarchasiData("Fizika", R.drawable.fizika, "Nyuton", "$11.00", "4.8"))
        barchasiArr.add(BarchasiData("Ona tili", R.drawable.ona_tili, "Navoiy", "$9.80", "4.75"))

    }


}