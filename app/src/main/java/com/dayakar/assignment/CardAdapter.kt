package com.dayakar.assignment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.dayakar.assignment.databinding.CardItemBinding
import com.dayakar.assignment.model.Car

class CardAdapter(val carList:MutableList<Car>): RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.bind(carList[position])
    }


   inner class ViewHolder(private val binding:CardItemBinding):RecyclerView.ViewHolder(binding.root){

         fun bind(car: Car){
             binding.itemImage.setImageResource(car.image)
             binding.itemTitle.text=car.title

             itemView.setOnClickListener {
                 //passing car list to popup window with bundle
                 val bundle=Bundle().apply {
                     putParcelableArrayList("list", ArrayList<Parcelable>(carList))
                     putInt("selectedPosition", adapterPosition)
                 }
                 //This will open popup window
               val fm=  (itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                 fm.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                 val dialog=PopUpWindowFragment()
                 dialog.arguments=bundle
                 dialog.show(fm,"Dialog")
             }
         }

    }

}