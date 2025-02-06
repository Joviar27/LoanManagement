package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Loan(
    val interestRate: Double,
    val amount: Int,
    val purpose: String,
    val documents: List<Document>?,
    val borrower: Borrower,
    val term: Int,
    val id: String,
    val collateral: Collateral,
    val repaymentSchedule: RepaymentSchedule,
    val riskRating: String
): Parcelable

@Parcelize
data class RepaymentSchedule(
    val installments: List<InstallmentsItem>
): Parcelable

@Parcelize
data class Document(
    val type: String,
    val url: String
): Parcelable

@Parcelize
data class Collateral(
    val type: String,
    val value: Int
): Parcelable

@Parcelize
data class InstallmentsItem(
    val amountDue: Int,
    val dueDate: String
): Parcelable

@Parcelize
data class Borrower(
    val creditScore: Int,
    val name: String,
    val id: String,
    val email: String
): Parcelable