package com.example.core.remote.response

import com.google.gson.annotations.SerializedName

data class LoanListResponse(

	@field:SerializedName("LoanListResponse")
	val loanListResponse: List<LoanListResponseItem>
)

data class RepaymentSchedule(

	@field:SerializedName("installments")
	val installments: List<InstallmentsItem>
)

data class LoanListResponseItem(

	@field:SerializedName("interestRate")
	val interestRate: Any,

	@field:SerializedName("amount")
	val amount: Int,

	@field:SerializedName("purpose")
	val purpose: String,

	@field:SerializedName("documents")
	val documents: List<DocumentsItem>,

	@field:SerializedName("borrower")
	val borrower: Borrower,

	@field:SerializedName("term")
	val term: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("collateral")
	val collateral: Collateral,

	@field:SerializedName("repaymentSchedule")
	val repaymentSchedule: RepaymentSchedule,

	@field:SerializedName("riskRating")
	val riskRating: String
)

data class DocumentsItem(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String
)

data class Collateral(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("value")
	val value: Int
)

data class InstallmentsItem(

	@field:SerializedName("amountDue")
	val amountDue: Int,

	@field:SerializedName("dueDate")
	val dueDate: String
)

data class Borrower(

	@field:SerializedName("creditScore")
	val creditScore: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String
)
