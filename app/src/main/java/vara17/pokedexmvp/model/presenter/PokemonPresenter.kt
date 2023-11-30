package vara17.pokedexmvp.model.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vara17.pokedexmvp.model.data.PokemonModel
import vara17.pokedexmvp.model.repository.PokemonRepository
import vara17.pokedexmvp.ui.interfaces.PresenterActivity

class PokemonPresenter(private val view: PresenterActivity<PokemonModel>) {

    private var repository: PokemonRepository = PokemonRepository()

    fun getPokemon(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getPokemonInfo(id)
            withContext(Dispatchers.Main) {
                data?.let { data ->
                    data.error?.let {
                        view.showError(it.message.toString())
                    } ?: run {
                        view.setData(data.model)
                    }
                }
            }
        }
    }
}