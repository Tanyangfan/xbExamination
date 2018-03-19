package mushi.xb.examination.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mushi.xb.examination.R
import mushi.xb.examination.viewmodel.QuestionViewModel
import java.util.*

/**
 * 开始答题的Fragment
 * Created by Tan.Yangfan on 2018/3/19.
 */
class StartFragment : Fragment() {

    companion object {
        fun newInstance(): StartFragment {
            return StartFragment()
        }
    }

    private var mQuestionViewModel: QuestionViewModel? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mQuestionViewModel = MainActivity.obtainViewModel(activity)
        return inflater?.inflate(R.layout.fragment_start, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mQuestionViewModel?.mIsDataLoading?.observe(this, Observer<Boolean> {

        })
    }

    override fun onResume() {
        super.onResume()
        mQuestionViewModel?.loadQuestion()
    }
}