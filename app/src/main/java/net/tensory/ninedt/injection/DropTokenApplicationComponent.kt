package net.tensory.ninedt.injection

import dagger.Component
import net.tensory.ninedt.NineDtApplication

/**
 * Please add a docstring!
 */

@Component(modules = [MainActivityModule::class])
interface DropTokenApplicationComponent {
    fun inject(nineDtApplication: NineDtApplication)
}