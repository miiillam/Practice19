package com.example.practice19kotline

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class FragmentAnimalList : Fragment() {

    private var callback: OnAnimalSelectedListener? = null
    private var items: Array<String>? = null

    interface OnAnimalSelectedListener {
        fun onAnimalSelected(item: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAnimalSelectedListener) {
            callback = context
        } else {
            throw RuntimeException("$context must implement OnAnimalSelectedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = arguments?.getStringArray(ARG_ITEMS)
            ?: arrayOf("Кошка", "Собака") // По умолчанию список животных
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal_list, container, false)
        val listView: ListView = view.findViewById(R.id.animal_list_view)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items!!)
        listView.adapter = adapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                callback?.onAnimalSelected(items!![position])
            }

        return view
    }

    companion object {
        private const val ARG_ITEMS = "items"

        fun newInstance(items: Array<String>?): FragmentAnimalList {
            val fragment = FragmentAnimalList()
            val args = Bundle()
            args.putStringArray(ARG_ITEMS, items)
            fragment.arguments = args
            return fragment
        }
    }
}
