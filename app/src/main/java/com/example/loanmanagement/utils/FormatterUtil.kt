package com.example.loanmanagement.utils

import java.text.NumberFormat
import java.util.Locale

object FormatterUtil {
    fun formatToRupiah(amount: Number): String {
        val localeID = Locale("id", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localeID)
        return formatter.format(amount).replace("Rp", "Rp ")
    }

    fun formatPercentage(value: Double): String {
        return "%.${2}f%%".format(value * 100)
    }
}