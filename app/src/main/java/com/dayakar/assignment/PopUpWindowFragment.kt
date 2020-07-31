package com.dayakar.assignment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dayakar.assignment.databinding.FragmentPopUpWindowBinding
import com.dayakar.assignment.model.Car
import kotlin.math.abs


private const val LIST_PARAM="list"
private const val SELECTED_POSITION_PARAM="selectedPosition"
class PopUpWindowFragment : DialogFragment(){
    private var carsList:ArrayList<Car>?=null
    private var selectedPosition:Int?=null

    private lateinit var binding:FragmentPopUpWindowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           carsList=it.getParcelableArrayList<Car>(LIST_PARAM)
            selectedPosition=it.getInt(SELECTED_POSITION_PARAM)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentPopUpWindowBinding.inflate(inflater)
       binding.itemImage.setFactory {
            val imgView = ImageView(requireContext())
            imgView.scaleType = ImageView.ScaleType.FIT_XY
            imgView
        }
        selectedPosition?.let { setUi(it) }
        binding.closeIcon.setOnClickListener {
            dismiss()
        }

        binding.itemImage.setOnTouchListener { v, event -> gesture.onTouchEvent(event) }
         binding.bookButton.setOnClickListener {
             Toast.makeText(context, "Booking now...", Toast.LENGTH_SHORT).show()
         }

        return binding.root

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    private val gesture = GestureDetector(
        activity,
        object : SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(
                e1: MotionEvent, e2: MotionEvent, velocityX: Float,
                velocityY: Float
            ): Boolean {
                Log.i("Dialog", "onFling has been called!")
                val SWIPE_MIN_DISTANCE = 120
                val SWIPE_MAX_OFF_PATH = 250
                val SWIPE_THRESHOLD_VELOCITY = 200
                try {
                    if (abs(e1.y - e2.y) > SWIPE_MAX_OFF_PATH) return false
                    if (e1.x - e2.x > SWIPE_MIN_DISTANCE
                        && abs(velocityX) > SWIPE_THRESHOLD_VELOCITY
                    ) {
                        Log.i("Dialog", "Right to Left")
                        selectedPosition?.let {
                            selectedPosition= selectedPosition!! +1
                            setUi(selectedPosition!!)

                        }


                    } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE
                        && abs(velocityX) > SWIPE_THRESHOLD_VELOCITY
                    ) {
                        selectedPosition?.let {
                            selectedPosition= selectedPosition!! -1
                            setUi(selectedPosition!!)

                        }

                        Log.i("Dialog", "Left to Right")
                    }
                } catch (e: Exception) {
                    // nothing
                }
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        })


    private fun setUi(position:Int){

        position.let {
            val carModel=carsList?.get(it)
            val image=carsList?.get(it)?.image
            if (image != null) {
                binding.itemImage.setImageResource(image)
            }
            binding.carName.text=carModel?.title
            binding.rentText.text="$${carModel?.rentalCost} /day"
            binding.siitingCapacity.text="${carModel?.sittingCapacity} PERSONE"
            binding.milage.text="${carModel?.milage} KMPG"
            binding.lugguageCapacity.text="${carModel?.luggages} LUGGUAGES"



        }

    }

}