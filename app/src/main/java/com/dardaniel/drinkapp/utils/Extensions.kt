package com.dardaniel.drinkapp.utils

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Double.round(decimals: Int = 2) = "%.${decimals}f".format(this).toDouble()


fun Fragment.toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), text, length).show()
}

