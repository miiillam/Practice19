package com.example.practice19kotline

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentAnimalList.OnAnimalSelectedListener {

    private var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Проверяем, есть ли второй контейнер
        isTwoPane = findViewById<View>(R.id.fragment_container_info) != null

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_list, FragmentAnimalList.newInstance(null))
                .commit()

            if (isTwoPane) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_info, FragmentAnimalInfo())
                    .commit()
            }
        }
    }

    override fun onAnimalSelected(animalOrBreed: String?) {
        if (animalOrBreed == null) return

        val breeds = when (animalOrBreed) {
            "Кошка" -> arrayOf("Британская короткошерстная", "Сиамская кошка")
            "Собака" -> arrayOf("Немецкая овчарка", "Лабрадор ретривер")
            else -> null
        }

        if (breeds != null) {
            // Обновляем список пород
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_list, FragmentAnimalList.newInstance(breeds))
                .commit()
        } else {
            // Отображаем информацию о породе
            if (isTwoPane) {
                val fragment = FragmentAnimalInfo.newInstance(animalOrBreed)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_info, fragment)
                    .commit()
            } else {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("breed", animalOrBreed)
                startActivity(intent)
            }
        }
    }
}