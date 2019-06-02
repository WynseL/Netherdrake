package com.wynsel.netherdrake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction

class NetherRouter(private val activity: AppCompatActivity?) {
    fun replaceFragment(viewId: Int, fragment: Fragment, tag: String? = null) {
        activity?.supportFragmentManager?.transaction {
            replace(viewId, fragment, tag)
        }
    }

    fun addFragment(viewId: Int, fragment: Fragment, tag: String? = null) {
        activity?.supportFragmentManager?.transaction {
            add(viewId, fragment, tag)
        }
    }

    fun intentTo(clazz: Class<*>) {
        activity?.startActivity(Intent(activity, clazz))
    }

    fun intentTo(f: (Intent) -> Unit, clazz: Class<*>) {
        activity?.startActivity(Intent(activity, clazz).apply {
            f.invoke(this)
        })
    }
}