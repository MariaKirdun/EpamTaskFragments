package com.manya.epamtaskfragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button

/**
 * Simple Fragment with button. The onClick method formed at the host-activity [FragmentActivity].
 *
 * @author Maria Kirdun
 *
 */

class FragmentA : Fragment() {

    private var listener : OnClickListener? = null
    private var clickButton : Button? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? OnClickListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButton = view.findViewById<Button>(R.id.clickButton)
    }

    override fun onStart() {
        super.onStart()
        clickButton?.setOnClickListener {
            listener?.onClick(clickButton)
        }
    }

}