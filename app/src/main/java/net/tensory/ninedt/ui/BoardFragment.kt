package net.tensory.ninedt.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_board.*
import net.tensory.ninedt.R

class BoardFragment : Fragment(), MoveDelegate {

    val viewModel = BoardViewModel()
    private val userMove: BehaviorSubject<Int> = BehaviorSubject.create()

    override fun getMove(): Observable<Int> = userMove

    inner class ColumnOnClickListener(val column: Int) : View.OnClickListener {
        override fun onClick(p0: View?) {
            userMove.onNext(column)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_board, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in 0 until columns.childCount) {
            val column = columns.getChildAt(i) as ViewGroup
            column.setOnClickListener(ColumnOnClickListener(i))
        }
    }
}
