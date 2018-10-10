package com.fw.vlad.android.mobile_ui.mapper

interface ViewMapper<P, V> {

    fun mapToView(presentation: P) : V
}