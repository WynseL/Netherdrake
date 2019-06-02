package com.wynsel.netherdrake

import androidx.appcompat.app.AppCompatActivity

abstract class NetherPresenter<V: NetherContracts.View, I: NetherContracts.Interactor>(view: V) :
    NetherContracts.Presenter {

    protected var view: V? = view
    protected var interactor: I? = null
    var baseActivity: AppCompatActivity? = null
        private set

    init {
        @Suppress("LeakingThis")
        this.interactor = initializeInteractor
        this.onPresenterReady()
    }

    abstract val initializeInteractor: I

    override fun setActivity(a: AppCompatActivity?) {
        this.baseActivity = a
        afterActivitySet()
    }

    override fun unregister() {
        this.onPresenterEnd()
        this.view = null
        this.interactor?.unregister()
        this.interactor = null
    }
}