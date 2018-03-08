package net.tensory.ninedt.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Remote game API endpoints.
 */
interface GameService {
    companion object {
        const val BASE_URL = "https://w0ayb2ph1k.execute-api.us-west-2.amazonaws.com"
    }

    @GET("production?moves={moves}")
    fun getNextMove(@Path("moves") moves: String): Observable<IntArray>
}