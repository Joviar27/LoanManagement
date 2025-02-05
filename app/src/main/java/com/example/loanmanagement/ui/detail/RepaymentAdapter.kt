package com.example.loanmanagement.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.InstallmentsItem
import com.example.loanmanagement.databinding.ItemRepaymentBinding
import com.example.loanmanagement.utils.FormatterUtil

class RepaymentAdapter(
    private var items: List<InstallmentsItem>
) : RecyclerView.Adapter<RepaymentAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemRepaymentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InstallmentsItem) {
            binding.tvDueAmount.text = FormatterUtil.formatToUSD(item.amountDue)
            binding.tvDueDate.text = FormatterUtil.formatDate(item.dueDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRepaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}