package com.fw.vlad.android.test.factory

import com.fw.vlad.android.cache.model.Config

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }
}