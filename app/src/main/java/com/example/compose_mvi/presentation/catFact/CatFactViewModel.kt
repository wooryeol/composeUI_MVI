package com.example.compose_mvi.presentation.catFact

import androidx.lifecycle.viewModelScope
import com.example.compose_mvi.core.base.MviViewModel
import com.example.compose_mvi.data.catfact.repository.CatFactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactViewModel @Inject constructor(
    private val repository: CatFactRepository
): MviViewModel<
        CatFactContract.Intent,
        CatFactContract.State,
        CatFactContract.Effect,
        >() {
    override fun createInitialState(): CatFactContract.State {
        return CatFactContract.State()
    }

    override fun handleIntent(intent: CatFactContract.Intent) {
        when (intent) {
            CatFactContract.Intent.Load -> fetch()
            CatFactContract.Intent.Refresh -> fetch()
        }
    }

    private fun fetch() {
        viewModelScope.launch {
            // loading
            setState { copy(isLoading = true, error = null) }

            try {
                val fact = repository.getFactCatData()

                // success
                setState {
                    copy(
                        isLoading = false,
                        fact = fact
                    )
                }

                setEffect {
                    CatFactContract.Effect.ShowToast("성공!!")
                }

            } catch (e: Exception) {
                // error
                setState {
                    copy(isLoading = false, error = e.message)
                }

                setEffect {
                    CatFactContract.Effect.ShowToast("에러 발생!!")
                }
            }
        }
    }
}