package com.example.handybook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.handybook.Barchasi.BarchasiData
import com.example.handybook.Barchasi.MyAdapterBarchasi
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

        barchasiData()
        binding.barchasiRv.setHasFixedSize(true)
        binding.barchasiRv.layoutManager = GridLayoutManager(requireParentFragment().context, 2)
        binding.barchasiRv.adapter = MyAdapterBarchasi(barchasiArr, requireContext())


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

    private fun barchasiData() {

        barchasiArr = arrayListOf()

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

    }

}