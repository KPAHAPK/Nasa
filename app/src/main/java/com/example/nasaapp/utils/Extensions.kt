package com.example.nasaapp

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> Fragment.viewLifeCycle(): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, LifecycleObserver {
        private var binding: T? = null

        init {
            this@viewLifeCycle
                .viewLifecycleOwnerLiveData
                .observe(this@viewLifeCycle, { lifecycleOwner: LifecycleOwner? ->

                    lifecycleOwner?.lifecycle?.addObserver(this)
                })
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        private fun onDestroy() {
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            return this.binding ?: error("Binding вызван до onCreateView() или после onDestroy()")
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
            this.binding = value
        }
    }
