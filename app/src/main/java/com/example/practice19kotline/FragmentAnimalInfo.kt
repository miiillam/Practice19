package com.example.practice19kotline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentAnimalInfo : Fragment() {

    private lateinit var animalImage: ImageView
    private lateinit var animalInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal_info, container, false)
        animalImage = view.findViewById(R.id.animal_image)
        animalInfo = view.findViewById(R.id.animal_info)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val breed = arguments?.getString(ARG_BREED)
        updateAnimalInfo(breed)
    }

    private fun updateAnimalInfo(breed: String?) {
        if (breed == null) return

        val imageResId: Int
        val textResId: Int

        when (breed) {
            "Британская короткошерстная" -> {
                imageResId = R.drawable.cat_british
                textResId = R.string.cat_british
            }
            "Сиамская кошка" -> {
                imageResId = R.drawable.cat_siamese
                textResId = R.string.cat_siamese
            }
            "Немецкая овчарка" -> {
                imageResId = R.drawable.dog_german
                textResId = R.string.dog_german
            }
            "Лабрадор ретривер" -> {
                imageResId = R.drawable.dog_labrador
                textResId = R.string.dog_labrador
            }
            else -> {
                imageResId = 0
                textResId = R.string.unknown_animal
            }
        }

        if (imageResId != 0) animalImage.setImageResource(imageResId)
        animalInfo.setText(textResId)
    }

    companion object {
        private const val ARG_BREED = "breed"

        fun newInstance(breed: String?): FragmentAnimalInfo {
            val fragment = FragmentAnimalInfo()
            val args = Bundle()
            args.putString(ARG_BREED, breed)
            fragment.arguments = args
            return fragment
        }
    }
}