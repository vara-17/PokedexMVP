package vara17.pokedexmvp.model.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vara17.pokedexmvp.model.data.PokedexModel
import vara17.pokedexmvp.model.repository.PokemonRepository
import vara17.pokedexmvp.ui.interfaces.PresenterActivity

class PokedexPresenter(private val view: PresenterActivity<PokedexModel>) {

    private var repository: PokemonRepository = PokemonRepository()

    fun getPokedex() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getPokemonList()
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