package com.example.loanmanagement.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Event
import com.example.core.Result
import com.example.core.data.LoanRepository
import com.example.core.domain.Loan
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: LoanRepository
): ViewModel() {

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> get() = _error

    private val _loanList = MutableLiveData<List<Loan>>()
    val loanList: LiveData<List<Loan>> get() = _loanList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getLoanList()
    }

    private fun getLoanList(){
        viewModelScope.launch {
            repository.getUserLoanList()
                .collect{
                    when(it){
                        is Result.Loading ->{
                            _isLoading.postValue(true)
                        }
                        is Result.Success ->{
                            _isLoading.postValue(false)
                            _loanList.postValue(it.data)
                        }
                        is Result.Error ->{
                            _isLoading.postValue(false)
                            _error.postValue(Event(it.message))
                        }
                    }
                }
        }
    }
}