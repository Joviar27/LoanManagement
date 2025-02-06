package com.example.loanmanagement.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Event
import com.example.core.Result
import com.example.core.domain.model.Loan
import com.example.core.domain.model.SortType
import com.example.core.domain.usecase.GetLoanUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLoanUseCase: GetLoanUseCase
): ViewModel() {

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> get() = _error

    private val _loanList = MutableLiveData<List<Loan>>()
    val loanList: LiveData<List<Loan>> get() = _loanList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _sortType = MutableLiveData<SortType>()
    val sortType: LiveData<SortType> get() = _sortType

    init {
        getLoanList(null)
    }

    fun getLoanList(sortType: SortType?){
        viewModelScope.launch {
            getLoanUseCase.getUserLoanList(sortType)
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

    fun updateSortType(sortType: SortType){
        _sortType.postValue(sortType)
    }
}