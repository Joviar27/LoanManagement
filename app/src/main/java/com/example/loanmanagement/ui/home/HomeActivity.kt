package com.example.loanmanagement.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.SortType
import com.example.loanmanagement.ViewModelFactory
import com.example.loanmanagement.databinding.ActivityHomeBinding
import com.example.loanmanagement.ui.LoanAdapter
import com.example.loanmanagement.ui.detail.DetailActivity
import com.example.loanmanagement.utils.showToast

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val adapter by lazy {
        LoanAdapter()
    }

    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        initObserver()
    }

    private fun initView(){
        adapter.onItemClicked = { selectedLoan ->
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.KEY_EXTRA_LOAN, selectedLoan)
            startActivity(intent)
        }
        binding.rvLoans.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            binding.rvLoans.adapter = this@HomeActivity.adapter
        }

        resetSortState()
        binding.btnSortPurpose.setOnClickListener{
            resetSortState()
            viewModel.updateSortType(SortType.PURPOSE)
        }
        binding.btnSortTerm.setOnClickListener{
            resetSortState()
            viewModel.updateSortType(SortType.TERM)
        }
        binding.btnSortAmount.setOnClickListener{
            resetSortState()
            viewModel.updateSortType(SortType.AMOUNT)
        }
        binding.btnSortRisk.setOnClickListener{
            resetSortState()
            viewModel.updateSortType(SortType.RISK)
        }
    }

    private fun initObserver(){
        viewModel.loanList.observe(this){
            adapter.submitList(it)
        }
        viewModel.isLoading.observe(this){ isLoading ->
            binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
        }
        viewModel.error.observe(this){
            it.getContentIfNotHandled()?.showToast(this)
        }
        viewModel.sortType.observe(this){
            when(it){
                SortType.RISK -> binding.btnSortRisk.setSelectedState(true)
                SortType.TERM -> binding.btnSortTerm.setSelectedState(true)
                SortType.AMOUNT -> binding.btnSortAmount.setSelectedState(true)
                SortType.PURPOSE -> binding.btnSortPurpose.setSelectedState(true)
                else -> Unit
            }
            if(it!=null) viewModel.getLoanList(it)
        }
    }

    private fun resetSortState(){
        binding.btnSortAmount.setSelectedState(false)
        binding.btnSortRisk.setSelectedState(false)
        binding.btnSortPurpose.setSelectedState(false)
        binding.btnSortTerm.setSelectedState(false)
    }
}