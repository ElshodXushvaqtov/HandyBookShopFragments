package com.example.handybook

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.handybook.Barchasi.BarchasiData
import com.example.handybook.Darsliklar.DarsliklarData
import com.example.handybook.databinding.FragmentBatafsilBinding
import com.example.handybook.module.Book
import com.example.handybook.romanlarRV.RomanlarData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BatafsilFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentBatafsilBinding
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
        binding = FragmentBatafsilBinding.inflate(layoutInflater)
        val shared = requireContext().getSharedPreferences("shared", Context.MODE_PRIVATE)
        val gson = Gson()

//        val romanlar = shared.getString("roman", null)
//        val books = gson.fromJson<ArrayList<BarchasiData>>(
//            romanlar,
//            object : TypeToken<ArrayList<Book>>() {}.type
//        )
        if (arguments?.containsKey("roman") == true) {
            val roman = arguments?.getSerializable("roman") as RomanlarData
            binding.bookImage.setImageResource(roman.bookImg)
            binding.rating.text = roman.bookRating
            binding.author.text = roman.bookAuthor
            binding.price.text = roman.bookAuthor
            binding.name.text = roman.bookName
            return binding.root
        }

//        val darsliklar = shared.getString("darslik", null)

        if (arguments?.containsKey("dars") == true) {
            val darslik = arguments?.getSerializable("dars") as DarsliklarData
            binding.bookImage.setImageResource(darslik.darslikImg)
            binding.rating.text = "5.0"
            binding.author.text = darslik.darslikAuthor
            binding.price.text = "$10.00"
            binding.name.text = darslik.darslikName
            return binding.root

        }

        if (arguments?.containsKey("searchBook") == true) {
            val searchBooks = arguments?.getSerializable("searchBook") as DarsliklarData
            binding.bookImage.setImageResource(searchBooks.darslikImg)
            binding.rating.text = "5.0"
            binding.author.text = searchBooks.darslikAuthor
            binding.price.text = "$10.00"
            binding.name.text = searchBooks.darslikName
            return binding.root

        }

        binding.shareBtn.setOnClickListener {
            showShareDialog()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BatafsilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun showShareDialog() {
        val shareView = layoutInflater.inflate(R.layout.share_dialog, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        dialog.setContentView(shareView)
        dialog.show()
    }
}