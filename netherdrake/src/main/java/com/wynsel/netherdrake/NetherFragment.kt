package com.wynsel.netherdrake

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

abstract class NetherFragment<P: NetherContracts.Presenter>: Fragment(),
    NetherContracts.View {

    protected var preseter: P? = null
    protected var activity: AppCompatActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as AppCompatActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeCreateView()
        return inflater.inflate(getLayout(), null, false)
    }

    protected abstract fun beforeCreateView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.preseter = initializePresenter
        this.preseter?.setActivity(activity)
        onViewReady()
    }

    abstract fun getLayout(): Int

    abstract val initializePresenter: P

    @Suppress("UNCHECKED_CAST")
    protected fun <T> getViewById(id: Int): T = view?.findViewById<View>(id) as T

    protected fun context(): Context? = this.activity
    protected fun appContext(): Context? = this.activity?.applicationContext
    protected fun resStr(strId: Int): String = resources.getString(strId)
    protected fun resStrArr(strArrId: Int): Array<String> = resources.getStringArray(strArrId)
    protected fun resColor(colorId: Int): Int = ContextCompat.getColor(this.activity as Context, colorId)
}