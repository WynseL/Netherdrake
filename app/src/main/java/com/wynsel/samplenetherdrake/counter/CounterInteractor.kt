package com.wynsel.samplenetherdrake.counter

import com.wynsel.netherdrake.NetherInteractor

class CounterInteractor(presenter: CounterPresenter): NetherInteractor<CounterPresenter>(presenter) {
    override fun onInteractorReady() {

    }
}