package kh.edu.rupp.ite.madproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.madproject.api.ApiManager
import kh.edu.rupp.ite.madproject.model.ApiState
import kh.edu.rupp.ite.madproject.model.Profile
import kh.edu.rupp.ite.madproject.model.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _profileDataState = MutableStateFlow(ApiState<Profile>(State.loading, null))
    val profileDataState get() = _profileDataState.asStateFlow()

    fun loadProfileData() {

        viewModelScope.launch {
            val response = ApiManager.getApiService().loadProfileData()
            try {
                if (response.isSuccess()){
                    _profileDataState.update {
                        ApiState(State.success, response.data)
                    }
                } else {
                    _profileDataState.update {
                        ApiState(State.error, null)
                    }
                }
            } catch (ex: Exception) {
                _profileDataState.update {
                    ApiState(State.error, null)
                }
            }
        }

    }

}
