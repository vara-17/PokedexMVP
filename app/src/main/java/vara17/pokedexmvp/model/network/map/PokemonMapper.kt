package vara17.pokedexmvp.model.network.map

import vara17.pokedexmvp.model.data.*
import vara17.pokedexmvp.model.network.api.PokedexApiResponse

object PokemonMapper {

    fun mapPokemonList(apiResponse: PokedexApiResponse): List<PokemonListInfo> {
        return apiResponse.pokedex.mapIndexed { index, pokemon ->
            PokemonListInfo(index + 1, pokemon.name)
        }
    }

    fun mapPokemon(apiResponse: Pokemon): PokemonModel {
        return PokemonModel(
            name = apiResponse.name.uppercase(),
            number = apiResponse.id,
            height = apiResponse.height,
            weight = apiResponse.weight,
            types = getTypes(apiResponse.types),
            imageUrl = getPokemonSprite(apiResponse.sprites)
        )
    }

    private fun getPokemonSprite(sprites: Sprites): String? =
        listOf(
            sprites.frontDefault, sprites.frontFemale, sprites.frontShiny, sprites.frontShinyFemale,
            sprites.backDefault, sprites.backFemale, sprites.backShiny, sprites.backShinyFemale
        ).find { it?.isNotEmpty() == true }

    fun getTypes(types: List<Type>): String? {
        return types.joinToString(",") { it.type.name }
    }

}