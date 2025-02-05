package com.example.loanmanagement.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

object FormatterUtil {
    fun formatToUSD(amount: Number): String {
        val localeUS = Locale("en", "US")
        val formatter = NumberFormat.getCurrencyInstance(localeUS)
        return formatter.format(amount)
    }

    fun formatPercentage(value: Double): String {
        return "%.${2}f%%".format(value * 100)
    }

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date ?: return inputDate)
    }
}