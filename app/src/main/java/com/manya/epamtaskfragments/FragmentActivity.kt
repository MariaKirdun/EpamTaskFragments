package com.manya.epamtaskfragments

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View

/**
 * Simple Fragment App. Contains 2 fragments, and 2 different displays (portrait and landscape).
 * Display on [FragmentB] how many times Button on [FragmentA] was clicked.
 *
 * @author Maria Kirdun
 */

class FragmentActivity : AppCompatActivity(), View.OnClickListener {

    private var clickQuantity = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        if (savedInstanceState != null){
            clickQuantity = savedInstanceState.getSerializable(ARG_QUANTITY) as Int
        }

        val fragmentManager = supportFragmentManager

        if (clickQuantity == 0) {
            when (resources.configuration.orientation) {
                PORTRAIT -> createPortrait(fragmentManager)
                LANDSCAPE -> createLandscape(fragmentManager)
            }
        } else {
            replaceFragment(fragmentManager)
        }
    }


    private fun createPortrait(fragmentManager : FragmentManager){
        var fragment = fragmentManager.findFragmentById(FRAME_PORTRAIT_CONTAINER)

        if (fragment == null) {
            fragment = FragmentA()
            fragmentManager.beginTransaction()
                .add(FRAME_PORTRAIT_CONTAINER, fragment)
                .commit()
        }
    }

    private fun createLandscape(fragmentManager : FragmentManager){
        var fragmentA = fragmentManager.findFragmentById(FRAME_A_CONTAINER)
        var fragmentB = fragmentManager.findFragmentById(FRAME_B_CONTAINER)

        if (fragmentA == null || fragmentB == null) {
            fragmentA = FragmentA()
            fragmentB = createBFragment()
            fragmentManager.beginTransaction()
                .add(FRAME_A_CONTAINER, fragmentA)
                .add(FRAME_B_CONTAINER, fragmentB)
                .commit()
        }
    }

    private fun createBFragment() : FragmentB{
        return FragmentB.newInstance(clickQuantity)
    }

    override fun onClick(p0: View?) {
        clickQuantity++

        val fragmentManager = supportFragmentManager
        replaceFragment(fragmentManager)
    }

    private fun replaceFragment(fragmentManager: FragmentManager){
        when (resources.configuration.orientation) {
            PORTRAIT ->
                fragmentManager.beginTransaction()
                    .replace(FRAME_PORTRAIT_CONTAINER, createBFragment())
                    .addToBackStack(null)
                    .commit()
            LANDSCAPE ->
                fragmentManager.beginTransaction()
                    .replace(FRAME_B_CONTAINER, createBFragment())
                    .commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putSerializable(ARG_QUANTITY, clickQuantity)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        clickQuantity = savedInstanceState!!.getSerializable(ARG_QUANTITY) as Int

        val fragmentManager = supportFragmentManager

        when(resources.configuration.orientation){
            PORTRAIT -> createPortrait(fragmentManager)
            LANDSCAPE -> createLandscape(fragmentManager)
        }
    }


    companion object {
        private const val PORTRAIT = Configuration.ORIENTATION_PORTRAIT
        private const val LANDSCAPE = Configuration.ORIENTATION_LANDSCAPE
        private const val FRAME_PORTRAIT_CONTAINER = R.id.frameContainer
        private const val FRAME_A_CONTAINER = R.id.frameContainerA
        private const val FRAME_B_CONTAINER = R.id.frameContainerB
        private const val ARG_QUANTITY = "QUANTITY"
    }
}
