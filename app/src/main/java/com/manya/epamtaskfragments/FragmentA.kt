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

    var listener : OnClickListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? OnClickListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_a, container, false)

        val clickButton = view.findViewById<Button>(R.id.clickButton)
        clickButton.setOnClickListener { listener?.onClick(clickButton) }
        return view
    }

}