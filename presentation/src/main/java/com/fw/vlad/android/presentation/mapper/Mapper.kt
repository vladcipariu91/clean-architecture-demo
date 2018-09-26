package com.fw.vlad.android.presentation.mapper

interface Mapper<out V, in D> {

    fun mapToView(type: D): V
}