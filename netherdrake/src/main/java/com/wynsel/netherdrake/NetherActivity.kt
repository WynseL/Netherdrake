package com.wynsel.netherdrake

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout

@SuppressLint("Registered")
abstract class NetherActivity<P: NetherContracts.Presenter>: AppCompatActivity(),
    NetherContracts.View {

    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout)

        this.presenter = initializePresenter
        this.presenter?.setActivity(this)

        onViewReady()
    }

    abstract val getLayout: Int
    abstract val initializePresenter: P

    override fun onDestroy() {
        super.onDestroy()
        presenter?.unregister()
        endReceiver()
    }

    protected fun context(): Context = this
    protected fun activity(): Activity = this
    protected fun appContext(): Context = this.applicationContext
    protected fun resStr(strId: Int): String = resources.getString(strId)
    protected fun resStrArr(strArrId: Int): Array<String> = resources.getStringArray(strArrId)
    protected fun resColor(colorId: Int): Int = ContextCompat.getColor(this, colorId)

    protected fun View.closeKeyboard() {
        if (currentFocus != null) {
            Handler().postDelayed({
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(windowToken, 0)
            }, 100)
        }
    }

    protected fun registerService(clazz: Class<*>) {
        startService(Intent(this.applicationContext, clazz))
    }

    private var receiver: BroadcastReceiver? = null
    protected fun startReceiver(receiver: BroadcastReceiver, action: String? = null) {
        this.receiver = receiver
        registerReceiver(
            receiver,
            IntentFilter().apply {
                addAction(action ?: "")
            }
        )
    }

    protected fun endReceiver() {
        try {
            unregisterReceiver(this.receiver)
            this.receiver = null
        } catch (e: Exception) {}
    }

    protected fun setupHomeToolbar(toolbar: Toolbar, drawer: DrawerLayout? = null, init: ((DrawerLayout) -> Unit)?) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (drawer != null) {
            val drawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name)
            drawer.addDrawerListener(drawerToggle)
            drawerToggle.syncState()
            init?.invoke(drawer)
        }
    }

    protected fun setupChildToolbar(toolbar: Toolbar, title: String? = null) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = title ?: ""
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

}