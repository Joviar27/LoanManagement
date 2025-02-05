package com.example.loanmanagement.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.Loan
import com.example.loanmanagement.R
import com.example.loanmanagement.databinding.ItemLoanBinding
import com.example.loanmanagement.utils.FormatterUtil

class LoanAdapter() : ListAdapter<Loan, LoanAdapter.UserViewHolder>(DIFF_CALLBACK){

    lateinit var onItemClicked: ((Loan) -> Unit)

    inner class UserViewHolder(private val binding: ItemLoanBinding) : RecyclerView.ViewHolder (binding.root){
        fun bind(loan: Loan){
            binding.tvBorrowerName.text = loan.borrower.name
            binding.tvLoanPurpose.text = itemView.context.getString(R.string.loan_purpose, loan.purpose)
            binding.tvLoanInterest.text = itemView.context.getString(
                R.string.loan_interest,
                FormatterUtil.formatPercentage(loan.interestRate)
            )
            binding.tvLoanTerm.text = itemView.context.getString(R.string.loan_term, loan.term.toString())

            binding.tvLoanAmount.text = FormatterUtil.formatToRupiah(loan.amount)
            binding.tvLoanRisk.text = itemView.context.getString(R.string.loan_risk, loan.riskRating)

            itemView.setOnClickListener {
                onItemClicked.invoke(loan)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Loan> =
            object : DiffUtil.ItemCallback<Loan>() {
                override fun areItemsTheSame(oldItem: Loan, newItem: Loan): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Loan, newItem: Loan): Boolean {
                    return oldItem == newItem
                }
            }
    }
}