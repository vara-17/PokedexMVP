package vara17.pokedexmvp.model.data

data class PokedexResponse(
    var model: PokedexModel = PokedexModel(emptyList()),
    var error: Throwable?
)

data class PokedexModel(var pokedex: List<PokemonListInfo>)

data class PokemonListInfo(val id: Int, val name: String)
