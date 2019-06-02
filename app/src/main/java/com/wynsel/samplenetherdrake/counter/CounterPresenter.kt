package com.wynsel.samplenetherdrake.counter

import com.wynsel.netherdrake.NetherPresenter
import com.wynsel.netherdrake.NetherRouter

class CounterPresenter(view: CounterActivity): NetherPresenter<CounterActivity, CounterInteractor>(view) {
    override val initializeInteractor: CounterInteractor = CounterInteractor(this)
    private val router = NetherRouter(baseActivity)

    private var counter = 0

    override fun onPresenterReady() {

    }

    override fun onPresenterEnd() {

    }

    override fun afterActivitySet() {

    }

    fun onButtonClick() {
        counter++
        view?.onCountChanged(counter)
    }
}