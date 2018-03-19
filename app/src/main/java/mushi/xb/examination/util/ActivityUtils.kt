package mushi.xb.examination.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Some utils about activity
 * Created by Tan.Yangfan on 2018/3/19.
 */
object ActivityUtils {

    fun replaceFragmentInActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment,
                                  frameId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }

    fun addFragmentInActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment,
                                  tag: String) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(fragment, tag)
        transaction.commit()
    }
}