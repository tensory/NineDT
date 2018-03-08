package net.tensory.ninedt.injection

import dagger.Subcomponent
import dagger.android.AndroidInjector
import net.tensory.ninedt.MainActivity
import javax.inject.Singleton

/**
 * Please add a docstring!
 */
@Singleton
@Subcomponent(modules = [NetworkModule::class, PresentationModule::class])
interface MainActivitySubcomponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}