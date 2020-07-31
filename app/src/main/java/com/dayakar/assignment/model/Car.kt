package com.dayakar.assignment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(val title:String,val rentalCost:Int,val sittingCapacity:Int,val milage:Int,val steeringType:String,
               val luggages:Int,val description:String ,val image:Int) :Parcelable
