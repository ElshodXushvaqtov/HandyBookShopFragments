package com.example.handybook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.handybook.Darsliklar.DarsliklarData
import com.example.handybook.databinding.FragmentSearchBinding
import com.example.handybook.searchFragment.SearchAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchArr: MutableList<DarsliklarData>
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
        binding = FragmentSearchBinding.inflate(layoutInflater)

        searchData()
        binding.searchRv.adapter =
            SearchAdapter(searchArr, requireContext(), object : SearchAdapter.MyInterface {
                override fun onItemTap(book: DarsliklarData) {
                    val bundle = bundleOf("searchBook" to book)
                    findNavController().navigate(
                        R.id.action_mainFragment_to_batafsilFragment,
                        bundle
                    )
                }

            })
        binding.searchRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.searchRv.setHasFixedSize(true)
        binding.searchInputTxt.addTextChangedListener {
            var filter = mutableListOf<DarsliklarData>()
            if (it != null) {
                val changedList = searchArr
                for (i in changedList) {
                    if (i.darslikName.lowercase()
                            .contains(it.toString().lowercase()) || i.darslikAuthor.lowercase()
                            .contains(it.toString().lowercase())
                    )
                        filter.add(i)
                }
                var adapterSearched =
                    SearchAdapter(filter, requireContext(), object : SearchAdapter.MyInterface {
                        override fun onItemTap(book: DarsliklarData) {
                            val bundle = bundleOf("searchBook" to book)
                            findNavController().navigate(
                                R.id.action_mainFragment_to_batafsilFragment,
                                bundle
                            )
                        }
                    })
                binding.searchRv.adapter = adapterSearched
                binding.searchRv.setHasFixedSize(true)

            }
        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun searchData() {

        searchArr = mutableListOf()

        searchArr.add(DarsliklarData(R.drawable.algebra, "Algebra", "Davlat"))
        searchArr.add(DarsliklarData(R.drawable.fizika, "Fizika", "Nyuton"))
        searchArr.add(DarsliklarData(R.drawable.ona_tili, "Ona tili", "Navoiy"))
        searchArr.add(DarsliklarData(R.drawable.dengiz_qaroqchisi, "Qaroqchi", "Doyl"))
        searchArr.add(DarsliklarData(R.drawable.don_kristofor_big, "Kristofor", "Odam2"))
        searchArr.add(DarsliklarData(R.drawable.yulduzli_tunlar_big, "Yulduzli", "Pirimqul"))
        searchArr.add(DarsliklarData(R.drawable.ikkieshik_orasi, "O'tkir Hoshimov", "Odam1"))
        searchArr.add(DarsliklarData(R.drawable.otkankunlar, "Abdulla Qodirov", "Odam1"))
        searchArr.add(DarsliklarData(R.drawable.urush_tugasa, "Quchqor Norqobilov", "Odam1"))

    }

}