package com.dayakar.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil
import com.dayakar.assignment.databinding.ActivityMainBinding
import com.dayakar.assignment.model.Car

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        binding.recyclerView.adapter=CardAdapter(loadCars())

    }

    private fun loadCars():MutableList<Car>{
        val list= mutableListOf<Car>()
        list.add(Car("Ford FreeStyle",310,4,36,"AUTO",3,"SomeDescription",R.drawable.ford_freestyle))
        list.add(Car("Hyundai Grand i10",220,4,42,"AUTO",2,"SomeDescription",R.drawable.hyundai_grand_iten))
        list.add(Car("Kia Carnival",370,2,40,"AUTO",2,"SomeDescription",R.drawable.kia_carnival))
        list.add(Car("Maruthi Desire",290,4,35,"AUTO",3,"SomeDescription",R.drawable.maruthi_desire))
        list.add(Car("Tata Nexon EV",300,4,28,"AUTO",2,"SomeDescription",R.drawable.tata_nexon_ev))
        list.add(Car("Ford FreeStyle 2.0",450,6,36,"AUTO",4,"SomeDescription",R.drawable.ford_freestyle))

         return list

    }

}
