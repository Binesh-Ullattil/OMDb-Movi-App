package com.example.imdbmoviapp.base

import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


    open fun onFragmentSelected() {}

}