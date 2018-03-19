package mushi.xb.examination.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import mushi.xb.examination.R
import mushi.xb.examination.util.ActivityUtils
import mushi.xb.examination.viewmodel.QuestionViewModel
import mushi.xb.examination.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    companion object {
        fun obtainViewModel(activity: FragmentActivity): QuestionViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(QuestionViewModel::class.java)
        }
    }

    private var mViewModel: QuestionViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewFragment()

        mViewModel = obtainViewModel(this)
    }

    private fun setupViewFragment() {
        var startFragment: StartFragment? =
                supportFragmentManager.findFragmentById(R.id.contentFrame) as StartFragment
        startFragment?.let {
            startFragment = StartFragment.newInstance()
            ActivityUtils.replaceFragmentInActivity(
                    supportFragmentManager, startFragment!!, R.id.contentFrame)
        }
    }
}
