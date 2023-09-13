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
    private lateinit var arrRoman: ArrayList<RomanlarData>
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoshSahifaBinding.inflate(layoutInflater)

        dataType()
        binding.typeRV.setHasFixedSize(true)
        binding.typeRV.adapter = MyAdapterType(typesArray)

        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()

        if (shared.getString("books", null) == null) {
            BookApi(requireContext()).dataBooks()
        }

        var booksJson = shared.getString("books", null)
        books = gson.fromJson(booksJson, object : TypeToken<ArrayList<RomanlarData>>() {}.type)

        setMainDefaultRvUI()
        binding.romanlarRv.setHasFixedSize(true)

        binding.filter.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }


        dataDarsliklar()
        binding.darsliklarRV.setHasFixedSize(true)
        binding.darsliklarRV.adapter = MyAdapterDarsliklar(darsliklarArray)

        binding.barchaKitoblarTxt.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_barchasiFragment)
        }

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

    private fun setMainDefaultRvUI() {
        binding.romanlarRv.adapter =
            MyAdapterBook(books, requireContext(), object : MyAdapterBook.MyInterface {
                override fun onItemTap(book: RomanlarData) {
                    var bundle = bundleOf("book" to book)
                    findNavController().navigate(
                        R.id.action_mainFragment_to_batafsilFragment,
                        bundle
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
        typesArray.add(BookTypeData("Darsliklar"))
        typesArray.add(BookTypeData("Diniy Kitoblar"))
        typesArray.add(BookTypeData("Bepul Kitoblar"))
        typesArray.add(BookTypeData("Romanlar"))
    }


    private fun dataDarsliklar() {

        darsliklarArray = arrayListOf()

        darsliklarArray.add(DarsliklarData(R.drawable.algebra))
        darsliklarArray.add(DarsliklarData(R.drawable.fizika))
        darsliklarArray.add(DarsliklarData(R.drawable.ona_tili))
        darsliklarArray.add(DarsliklarData(R.drawable.algebra))
        darsliklarArray.add(DarsliklarData(R.drawable.fizika))
        darsliklarArray.add(DarsliklarData(R.drawable.ona_tili))

    }

}


