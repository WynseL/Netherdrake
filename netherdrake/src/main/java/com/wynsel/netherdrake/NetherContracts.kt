package com.wynsel.netherdrake

import androidx.appcompat.app.AppCompatActivity

interface NetherContracts {
    interface View {
        fun onViewReady()
    }

    interface Presenter {
        fun onPresenterReady()
        fun onPresenterEnd()
        fun afterActivitySet()
        fun setActivity(a: AppCompatActivity?)
        fun unregister()
    }

    interface Interactor {
        fun unregister()
        fun onInteractorReady()
    }
}