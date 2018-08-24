package com.fw.vlad.android.domain.executor

import io.reactivex.Scheduler

// Creating the abstraction for out RxJava Observation Thread
interface PostExecutionThread {
    val scheduler: Scheduler
}