package com.example.loanmanagement.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Loan
import com.example.loanmanagement.R
import com.example.loanmanagement.databinding.ActivityDetailBinding
import com.example.loanmanagement.ui.bottomsheet.DocumentBottomSheetFragment
import com.example.loanmanagement.utils.FormatterUtil

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var repaymentAdapter: RepaymentAdapter

    private val loan by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(KEY_EXTRA_LOAN, Loan::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(KEY_EXTRA_LOAN)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
    }

    private fun initView(){
        binding.toolbar2.setNavigationOnClickListener {
            finish()
        }

        loan?.let {
            val documentBottomSheet = DocumentBottomSheetFragment.newInstance(it.documents?.get(0))

            binding.tvBorrowerName.text = it.borrower.name
            binding.tvBorrowerEmail.text = it.borrower.email

            binding.tvCreditScore.text = resources.getString(R.string.credit_score, String.format(it.borrower.creditScore.toString()))
            binding.tvCollateralType.text = resources.getString(R.string.collateral_type, it.collateral.type)
            binding.tvCollateralValue.text = resources.getString(R.string.collateral_value, FormatterUtil.formatToUSD(it.collateral.value))

            binding.btnLoanDocument.isEnabled = !it.documents.isNullOrEmpty()
            binding.btnLoanDocument.setOnClickListener { _ ->
                documentBottomSheet.show(supportFragmentManager, "document")
            }

            repaymentAdapter = RepaymentAdapter(it.repaymentSchedule.installments)
            binding.rvRepaymentSchedule.apply {
                layoutManager = LinearLayoutManager(this@DetailActivity)
                adapter = repaymentAdapter
            }
        } ?: run{
            finish()
        }
    }

    companion object{
        const val KEY_EXTRA_LOAN = "key-extra-loan"
    }
}