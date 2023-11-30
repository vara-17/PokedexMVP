package vara17.pokedexmvp.ui.interfaces

interface PresenterActivity<T> {

    fun setData(data: T)

    fun showError(message: String)
}