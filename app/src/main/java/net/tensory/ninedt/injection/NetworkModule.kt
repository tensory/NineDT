package net.tensory.ninedt.injection

import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import net.tensory.ninedt.data.GameService
import net.tensory.ninedt.data.RemotePlayerController
import net.tensory.ninedt.game.MatchState
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Provide network-dependent services.
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesGameService(): GameService {
        val retrofit = Retrofit.Builder()
                .baseUrl(GameService.BASE_URL)
                .build()

        return retrofit.create<GameService>(GameService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemotePlayerController(): RemotePlayerController {
        return object : RemotePlayerController {
            override fun requestNextMove(moves: IntArray): Observable<MatchState> {
                return providesGameService().getNextMove(moves = String.format("[%s]", moves.joinToString(","))).map { MatchState(it) }
            }
        }
    }
}