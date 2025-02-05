package com.example.core.domain

data class Loan(
    val interestRate: Any,
    val amount: Int,
    val purpose: String,
    val documents: List<Document>,
    val borrower: Borrower,
    val term: Int,
    val id: String,
    val collateral: Collateral,
    val repaymentSchedule: RepaymentSchedule,
    val riskRating: String
)

data class RepaymentSchedule(
    val installments: List<InstallmentsItem>
)

data class Document(
    val type: String,
    val url: String
)

data class Collateral(
    val type: String,
    val value: Int
)

data class InstallmentsItem(
    val amountDue: Int,
    val dueDate: String
)

data class Borrower(
    val creditScore: Int,
    val name: String,
    val id: String,
    val email: String
)