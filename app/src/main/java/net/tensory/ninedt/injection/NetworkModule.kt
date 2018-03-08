package net.tensory.ninedt.injection

import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import net.tensory.ninedt.data.GameService
import net.tensory.ninedt.data.RemotePlayerController
import net.tensory.ninedt.game.MatchState
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton


/**
 * Provide network-dependent services.
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun providesGameService(): GameService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(GameService.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create<GameService>(GameService::class.java)
    }

    @Provides
    @Singleton
    fun providesRemotePlayerController(): RemotePlayerController {
        return object : RemotePlayerController {
            override fun requestNextMove(moves: IntArray): Observable<MatchState> {
//                return providesGameService().getNextMove(moves = String.format("[%s]", moves.joinToString(","))).map { MatchState(it) }
                return Observable.just(MatchState(intArrayOf(0)))
            }
        }
    }
}