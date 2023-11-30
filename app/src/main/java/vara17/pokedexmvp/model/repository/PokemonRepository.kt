package vara17.pokedexmvp.model.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vara17.pokedexmvp.model.data.PokedexModel
import vara17.pokedexmvp.model.data.PokedexResponse
import vara17.pokedexmvp.model.data.PokemonResponse
import vara17.pokedexmvp.model.network.api.PokemonApiService
import vara17.pokedexmvp.model.network.map.PokemonMapper

class PokemonRepository {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    suspend fun getPokemonList(): PokedexResponse? {
        return try {
            val service: PokemonApiService = retrofit.create(PokemonApiService::class.java)
            val response = service.getPokedex(151, 0)

            if (response.isSuccessful) {
                response.body()?.let { pokedexApiResponse ->
                    PokedexResponse(
                        model = PokedexModel(
                            PokemonMapper.mapPokemonList(
                                pokedexApiResponse
                            )
                        ), error = null
                    )
                }
            } else {
                PokedexResponse(error = Throwable("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            PokedexResponse(error = e)
        }
    }

    suspend fun getPokemonInfo(id: Int): PokemonResponse? {
        return try {
            val service: PokemonApiService = retrofit.create(PokemonApiService::class.java)
            val response = service.getPokemon(id)

            if (response.isSuccessful) {
                response.body()?.let { pokemonApiResponse ->
                    PokemonResponse(
                        model = PokemonMapper.mapPokemon(pokemonApiResponse),
                        error = null
                    )
                }
            } else {
                PokemonResponse(error = Throwable("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            PokemonResponse(error = e)
        }
    }
}