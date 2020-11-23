package com.hamidreza.newsapp.other

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@ActivityScoped
class A @Inject constructor(val b:B,val s2:String) {
    fun a()= println("Hi From A class")
    fun b() = b.b()
    fun c()= println(b.s)
}


class B @Inject constructor(@first val s:String) {
    fun b()= println("Hi From B class")
}

interface C {
    fun c()
}

@InstallIn(ApplicationComponent::class)
@Module
class Module{

    @Singleton
    @Provides

    fun provideBString() = "akbar"

    @Singleton
    @Provides
    @first
    fun provideB2String() = "hamid"

}
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class first