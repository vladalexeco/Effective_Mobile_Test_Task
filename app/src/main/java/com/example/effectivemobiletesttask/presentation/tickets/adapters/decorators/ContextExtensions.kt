package com.example.effectivemobiletesttask.presentation.tickets.adapters.decorators

import android.content.Context

fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}