package com.fw.vlad.android.data.store

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class ProjectsDataStoreFactoryTest {

    @Mock
    lateinit var cacheStore: ProjectsCacheDataStore
    @Mock
    lateinit var remoteStore: ProjectsRemoteDataStore
    lateinit var factory: ProjectsDataStoreFactory

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        factory = ProjectsDataStoreFactory(cacheStore, remoteStore)
    }

    @Test
    fun getDataStoreReturnsRemoteStoreCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCachedDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}