package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment: Fragment() {

    companion object {
        fun newInstance(text: String, index: Int): FirstFragment {
            val args = Bundle()
            args.putString("text", text)
            args.putInt("index", index)
            val fragment = FirstFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    fun onBegin() {
        Toast.makeText(activity, "onBegin", Toast.LENGTH_SHORT).show()
    }

    fun onBack() {
        if (activity!!.supportFragmentManager.backStackEntryCount > 0) {
            activity!!.supportFragmentManager.popBackStack()
            val fragment: Fragment? = activity!!.supportFragmentManager.findFragmentById(R.id.frame)
            fragment?.let {
                if (fragment is FirstFragment) {
                    activity!!.supportFragmentManager.beginTransaction().remove(fragment)
                }
            }
        } else {
            activity!!.supportFragmentManager.beginTransaction().remove(this)
        }
    }
}
