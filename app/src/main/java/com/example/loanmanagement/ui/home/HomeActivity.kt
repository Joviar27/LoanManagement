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
import com.example.loanmanagement.R
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
    }
}