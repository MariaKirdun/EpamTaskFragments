package com.manya.epamtaskfragments

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
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

        clickQuantity = savedInstanceState?.getSerializable(ARG_QUANTITY) as Int? ?: 0

        when (resources.configuration.orientation) {
            PORTRAIT -> createPortrait()
            LANDSCAPE -> createLandscape()
        }
    }


    private fun createPortrait(){
        val fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_B_TAG) as? FragmentB
        supportFragmentManager.popBackStack()
        if (fragment != null && clickQuantity != 0){
            fragment.arguments?.putSerializable(ARG_QUANTITY, clickQuantity)
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(FRAME_PORTRAIT_CONTAINER, createBFragment(), FRAGMENT_B_TAG)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(FRAME_PORTRAIT_CONTAINER, FragmentA())
                .commit()
        }

    }

    private fun createLandscape(){
       supportFragmentManager.beginTransaction()
           .add(FRAME_A_CONTAINER, FragmentA())
           .commit()
        supportFragmentManager.beginTransaction()
            .replace(FRAME_B_CONTAINER, createBFragment(), FRAGMENT_B_TAG)
            .addToBackStack(null)
            .commit()
    }


    override fun onClick(p0: View?) {
        clickQuantity++
        replaceFragment()
    }

    private fun replaceFragment(){
        val fragmentHolder = if (resources.configuration.orientation == PORTRAIT) FRAME_PORTRAIT_CONTAINER
        else  FRAME_B_CONTAINER
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(fragmentHolder,createBFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun createBFragment() : FragmentB{
        return FragmentB.newInstance(clickQuantity)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable(ARG_QUANTITY, clickQuantity)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (resources.configuration.orientation == LANDSCAPE){
            finish()
        }
    }

    companion object {
        private const val PORTRAIT = Configuration.ORIENTATION_PORTRAIT
        private const val LANDSCAPE = Configuration.ORIENTATION_LANDSCAPE
        private const val FRAME_PORTRAIT_CONTAINER = R.id.frameContainer
        private const val FRAME_A_CONTAINER = R.id.frameContainerA
        private const val FRAME_B_CONTAINER = R.id.frameContainerB
        private const val FRAGMENT_B_TAG = "FragmentB"
        private const val ARG_QUANTITY = "quantity"
    }
}
