package vara17.pokedexmvp.model.data


data class PokemonResponse(
    var model: PokemonModel = PokemonModel("", 0, 0, 0),
    var error: Throwable?
    )

data class PokemonModel(val name: String,
                        val number: Int,
                        val height: Int,
                        val weight: Int,
                        val types: String? = null,
                        val imageUrl: String? = null)