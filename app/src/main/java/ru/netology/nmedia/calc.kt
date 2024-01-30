package ru.netology.nmedia

import android.widget.TextView
import kotlin.math.floor

fun calculate(value: Int, calculateTextView: TextView): String {

    if (value in 0 until 1000) {
        calculateTextView.text = value.toString()
    } else if (value in 1000 until 10000 && (value - (value.toString()[(value.toString()).length - 4]).digitToInt() * 1000) / 100 == 0) {
        calculateTextView.text = "${value / 1000}K"
    } else if (value in 1000 until 10000) {
        calculateTextView.text = "${floor(value * 0.01) / 10.0}K"
    } else if (value in 10000 until 1000000) {
        calculateTextView.text  = "${value / 1000}K"
    } else if (value in 1000000 until 10000000 && (value - (value.toString()[(value.toString()).length - 7]).digitToInt() * 1000000) / 100000 == 0) {
        calculateTextView.text = "${value / 1000000}M"
    } else {
        calculateTextView.text = "${floor(value * 0.00001) / 10.0}M"
    }
    return calculateTextView.toString()
}