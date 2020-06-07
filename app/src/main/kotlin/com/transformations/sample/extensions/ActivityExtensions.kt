package com.transformations.sample.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Helper method used in activity to replace a Fragment
 */
fun AppCompatActivity.addFragment(
    fragment: Fragment, frameId: Int, tag: String?,
    isAddToBackStack: Boolean = false) {
    supportFragmentManager.transact {
        add(frameId, fragment, tag)
        if (isAddToBackStack) addToBackStack(tag)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply { action() }.commit()
}

