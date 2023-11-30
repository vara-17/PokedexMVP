package vara17.pokedexmvp.model.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vara17.pokedexmvp.model.data.Pokemon

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokedex(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokedexApiResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<Pokemon>
}