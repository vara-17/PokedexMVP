package vara17.pokedexmvp.model.data

sealed class State {
    object Loading : State()
    object Success : State()
    object Error : State()
}