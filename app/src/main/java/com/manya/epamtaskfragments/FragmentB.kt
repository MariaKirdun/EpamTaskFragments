package com.manya.epamtaskfragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Simple Fragment with TextView, which display how many clicks was clicked on Button from [FragmentA].
 * This parameter transmitted by arguments.
 *
 * @author Maria Kirdun
 *
 */

class FragmentB : Fragment() {

    private var clickQuantityTV : TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickQuantityTV = view.findViewById(R.id.clickQuantityTextView)
        arguments?.getInt(ARG_QUANTITY).let {
        clickQuantityTV?.text = it.toString()
        }
    }


    companion object {
        fun newInstance(quantity : Int) = FragmentB().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_QUANTITY, quantity)
            }
        }
        private const val ARG_QUANTITY = "quantity"
    }

}