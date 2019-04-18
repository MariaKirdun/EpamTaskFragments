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
        val view = inflater.inflate(R.layout.fragment_b, container, false)

        clickQuantityTV = view.findViewById(R.id.clickQuantityTextView)
        setQuantity(arguments)

        return view
    }


    private fun setQuantity(arguments : Bundle?) {
        val quantity = arguments?.getInt(ARG_QUANTITY)
        clickQuantityTV?.text = "$quantity"
    }

    companion object {
        fun newInstance(quantity : Int) : FragmentB{
            val args = Bundle()
            args.putInt(ARG_QUANTITY, quantity)

            val fragment = FragmentB()
            fragment.arguments = args
            return fragment
        }
        private const val ARG_QUANTITY = "quantity"
    }

}