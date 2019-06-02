package com.wynsel.netherdrake

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

abstract class NetherDialog<P: NetherContracts.Presenter, VH: NetherViewHolder>: DialogFragment(), NetherContracts.View {

    protected var presenter: P? = null
    lateinit var viewHolder: VH

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(getLayout, null)

        viewHolder = onCreateViewHolder(view)

        val builder = AlertDialog.Builder(activity).apply {
            this.setPositiveButton(positiveButton.title, positiveButton.click)
            if (negativeButton != null) this.setNegativeButton(negativeButton?.title, negativeButton?.click)
            if (neutralButton != null) this.setNeutralButton(neutralButton?.title, neutralButton?.click)
        }

        return builder.create().apply {
            this.setView(view)
            this.setCanceledOnTouchOutside(false)
            setOnShowListener {
                this.setTextColor(context, -1, R.color.colorPrimary)
                this.setTextColor(context, -3, R.color.colorPrimary)
                this.setTextColor(context, -2, R.color.colorPrimaryClose)
            }
        }
    }

    private fun AlertDialog.setTextColor(con: Context?, which: Int, resColorId: Int) {
        val color = con?.let { ContextCompat.getColor(it, resColorId) }
        if (color != null)
            this.getButton(which).setTextColor(color)

        this.getButton(which).setOnClickListener {
            val dismiss = when (which) {
                -1 -> {
                    positiveButton.click?.invoke(this, -1)
                    positiveButton.isDismissAfterClick
                }
                -3 -> {
                    neutralButton?.click?.invoke(this, -3)
                    neutralButton?.isDismissAfterClick ?: true
                }
                -2 -> {
                    negativeButton?.click?.invoke(this, -2)
                    negativeButton?.isDismissAfterClick ?: true
                }
                else -> true
            }
            if(dismiss) dismiss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        this.presenter = initializePresenter

        onViewReady()
    }

    abstract val getLayout: Int
    abstract val initializePresenter: P

    abstract fun onCreateViewHolder(view: View): VH
    abstract val positiveButton: Buttons
    abstract val negativeButton: Buttons?
    abstract val neutralButton: Buttons?

    protected fun context(): Context? = this.activity
    protected fun appContext(): Context? = this.activity?.applicationContext
    protected fun resStr(strId: Int): String = resources.getString(strId)
    protected fun resStrArr(strArrId: Int): Array<String> = resources.getStringArray(strArrId)
    protected fun resColor(colorId: Int): Int = ContextCompat.getColor(this.activity as Context, colorId)

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

    data class Buttons(
        val title: String,
        val isDismissAfterClick: Boolean = true,
        val click: ((d: DialogInterface, w: Int) -> Unit)?
    )
}