package vara17.pokedexmvp.model.network.api

import com.google.gson.annotations.SerializedName

data class PokedexApiResponse(
    @SerializedName("results") val pokedex: List<PokedexResult>
)

data class PokedexResult(
    @SerializedName("name") val name: String
)