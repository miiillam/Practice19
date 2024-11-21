package com.example.practice19kotline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {
            val animal = intent.getStringExtra("animal")
            val fragment = FragmentAnimalInfo()

            // Передаем данные в фрагмент
            val bundle = Bundle()
            bundle.putString("animal", animal)
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_info, fragment)
                .commit()
        }
    }
}