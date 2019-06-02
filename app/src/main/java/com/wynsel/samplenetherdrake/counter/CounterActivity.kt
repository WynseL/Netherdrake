package com.wynsel.samplenetherdrake.counter

import com.wynsel.netherdrake.NetherActivity
import com.wynsel.samplenetherdrake.R
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity: NetherActivity<CounterPresenter>() {
    override val getLayout: Int = R.layout.activity_counter
    override val initializePresenter: CounterPresenter = CounterPresenter(this)

    override fun onViewReady() {
        button?.setOnClickListener { presenter?.onButtonClick() }
    }

    fun onCountChanged(counter: Int) {
        textView?.text = counter.toString()
    }
}