package com.example.core.data.remote.response

import com.example.core.domain.Borrower
import com.example.core.domain.Collateral
import com.example.core.domain.Document
import com.example.core.domain.InstallmentsItem
import com.example.core.domain.Loan
import com.example.core.domain.RepaymentSchedule
import com.google.gson.annotations.SerializedName

data class RepaymentScheduleResponse(

	@field:SerializedName("installments")
	val installments: List<InstallmentsItemResponse>
)

data class LoanListResponseItem(

	@field:SerializedName("interestRate")
	val interestRate: Any,

	@field:SerializedName("amount")
	val amount: Int,

	@field:SerializedName("purpose")
	val purpose: String,

	@field:SerializedName("documents")
	val documents: List<DocumentsResponse>,

	@field:SerializedName("borrower")
	val borrower: BorrowerResponse,

	@field:SerializedName("term")
	val term: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("collateral")
	val collateral: CollateralResponse,

	@field:SerializedName("repaymentSchedule")
	val repaymentSchedule: RepaymentScheduleResponse,

	@field:SerializedName("riskRating")
	val riskRating: String
)

data class DocumentsResponse(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("url")
	val url: String
)

data class CollateralResponse(

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("value")
	val value: Int
)

data class InstallmentsItemResponse(

	@field:SerializedName("amountDue")
	val amountDue: Int,

	@field:SerializedName("dueDate")
	val dueDate: String
)

data class BorrowerResponse(

	@field:SerializedName("creditScore")
	val creditScore: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String
)

fun List<LoanListResponseItem>.mapToModel(): List<Loan>{
	return this.map {
		Loan(
            interestRate = it.interestRate,
            amount = it.amount,
            purpose = it.purpose,
            documents = it.documents.map { documentResponse ->
				Document(
					type = documentResponse.type,
					url = documentResponse.url
				)
			},
            borrower = Borrower(
                creditScore = it.borrower.creditScore,
                name = it.borrower.name,
                id = it.borrower.id,
                email = it.borrower.email,
            ),
            term = it.term,
            id = it.id,
            collateral = Collateral(
				type = it.collateral.type,
				value = it.collateral.value
			),
            repaymentSchedule = RepaymentSchedule(
				installments = it.repaymentSchedule.installments.map { schedule ->
					InstallmentsItem(
						dueDate = schedule.dueDate,
						amountDue = schedule.amountDue
					)
				}
			),
            riskRating = it.riskRating
        )
	}
}
