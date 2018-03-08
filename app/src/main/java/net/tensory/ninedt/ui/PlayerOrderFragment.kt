package net.tensory.ninedt.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_player_order.view.*

import net.tensory.ninedt.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlayerOrderFragment.OnChooseFirstPlayerListener] interface
 * to handle interaction events.
 * Use the [PlayerOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerOrderFragment : Fragment() {

    private var chooseFirstPlayerListener: OnChooseFirstPlayerListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_player_order, container, false)
        view.player_goes_first_button.setOnClickListener { chooseFirstPlayerListener?.onChooseFirstPlayer(true) }
        view.computer_goes_first_button.setOnClickListener { chooseFirstPlayerListener?.onChooseFirstPlayer(false) }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnChooseFirstPlayerListener) {
            chooseFirstPlayerListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnChooseFirstPlayerListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        chooseFirstPlayerListener = null
    }

    interface OnChooseFirstPlayerListener {
        fun onChooseFirstPlayer(userGoesFirst: Boolean)
    }
}
