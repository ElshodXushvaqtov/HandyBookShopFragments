package com.example.handybook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.handybook.BookType.BookTypeData
import com.example.handybook.BookType.MyAdapterType
import com.example.handybook.Books.BookApi
import com.example.handybook.Darsliklar.DarsliklarData
import com.example.handybook.Darsliklar.MyAdapterDarsliklar
import com.example.handybook.databinding.FragmentBoshSahifaBinding
import com.example.handybook.romanlarRV.MyAdapterBook
import com.example.handybook.romanlarRV.RomanlarData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BoshSahifaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBoshSahifaBinding
    private lateinit var typesArray: ArrayList<BookTypeData>
    private lateinit var darsliklarArray: ArrayList<DarsliklarData>
    private lateinit var books: ArrayList<RomanlarData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoshSahifaBinding.inflate(layoutInflater)

        dataType()
        binding.typeRV.setHasFixedSize(true)
        binding.typeRV.adapter =
            MyAdapterType(typesArray, requireContext(), object : MyAdapterType.MyInterface {
                override fun onItemTap(bookType: BookTypeData) {
                    val bundle = bundleOf(bookType.typeName to bookType)
                    findNavController().navigate(
                        R.id.action_mainFragment_to_barchasiFragment,
                        bundle
                    )
                }
            })

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()


        if (shared.getString("romanlar", null) == null) {
            BookApi(requireContext()).dataBooks()
        }
        val romanlarJson = shared.getString("romanlar", null)
        books = gson.fromJson(romanlarJson, object : TypeToken<ArrayList<RomanlarData>>() {}.type)
        setRomanlarData()
        binding.romanlarRv.setHasFixedSize(true)


        if (shared.getString("darsliklar", null) == null) {
            BookApi(requireContext()).dataDarsliklar()
        }
        val darsliklarJson = shared.getString("darsliklar", null)
        darsliklarArray =
            gson.fromJson(darsliklarJson, object : TypeToken<ArrayList<DarsliklarData>>() {}.type)
        setDarsliklarData()
        binding.darsliklarRV.setHasFixedSize(true)


        binding.barchaKitoblarTxt.setOnClickListener {
            val bundle = bundleOf("barchasi" to "")
            findNavController().navigate(R.id.action_mainFragment_to_barchasiFragment, bundle)
        }

        binding.searchInputt.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
        }



        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = BoshSahifaFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    private fun setRomanlarData() {
        binding.romanlarRv.adapter =
            MyAdapterBook(books, requireContext(), object : MyAdapterBook.MyInterface {
                override fun onItemTap(book: RomanlarData) {
                    val bundle = bundleOf("roman" to book)

                    findNavController().navigate(
                        R.id.action_mainFragment_to_batafsilFragment, bundle
                    )
                }
            })
        binding.romanlarRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setDarsliklarData() {
        binding.darsliklarRV.adapter = MyAdapterDarsliklar(darsliklarArray,
            requireContext(),
            object : MyAdapterDarsliklar.MyInterface {
                override fun onItemTap(darslik: DarsliklarData) {
                    val bundle = bundleOf("dars" to darslik)
                    findNavController().navigate(
                        R.id.action_mainFragment_to_batafsilFragment, bundle
                    )
                }
            })
        binding.romanlarRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun dataType() {

        typesArray = arrayListOf()

        typesArray.add(BookTypeData("Darsliklar"))
        typesArray.add(BookTypeData("Diniy Kitoblar"))
        typesArray.add(BookTypeData("Bepul Kitoblar"))
        typesArray.add(BookTypeData("Romanlar"))
        typesArray.add(BookTypeData("Xit"))
        typesArray.add(BookTypeData("Chegirma"))
    }


}


