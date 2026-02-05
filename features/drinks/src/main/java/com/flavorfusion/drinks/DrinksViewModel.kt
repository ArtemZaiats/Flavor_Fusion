package com.flavorfusion.drinks

import com.flavorfusion.common_domain.interactors.DrinksInteractor
import com.flavorfusion.common_ui.Executor
import com.flavorfusion.core_ui.mvi.MviViewModel
import javax.inject.Inject

class DrinksViewModel @Inject constructor(
    private val drinksInteractor: DrinksInteractor,
    private val executor: Executor,
    config: DrinksContract.Config
) : MviViewModel<DrinksContract.State, DrinksContract.Event>(config), Executor by executor {

    override fun handleEvent(event: DrinksContract.Event) {
        when (event) {
            is DrinksContract.Event.OnDrinkClicked -> {}
            is DrinksContract.Event.OnSearchValueChanged -> {}
            DrinksContract.Event.OnSearchClicked -> {}
            DrinksContract.Event.OnSearchCloseClicked -> {}
            DrinksContract.Event.OnRefresh -> {}
        }
    }
}