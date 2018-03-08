package net.tensory.ninedt.injection

import dagger.Module
import dagger.Provides
import net.tensory.ninedt.MainActivity
import net.tensory.ninedt.game.GamePresenter
import net.tensory.ninedt.game.GamePresenterImpl

/**
 * Please add a docstring!
 */
@Module
class PresentationModule {
    @Provides
    fun providesGamePresenter(mainActivity: MainActivity): GamePresenter {
        return GamePresenterImpl(mainActivity)
    }
}