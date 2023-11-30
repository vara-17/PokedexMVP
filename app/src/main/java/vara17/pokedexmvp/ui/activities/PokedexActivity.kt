package vara17.pokedexmvp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import vara17.pokedexmvp.R
import vara17.pokedexmvp.databinding.ActivityPokedexBinding
import vara17.pokedexmvp.model.data.PokedexModel
import vara17.pokedexmvp.model.data.State
import vara17.pokedexmvp.model.presenter.PokedexPresenter
import vara17.pokedexmvp.ui.adapter.PokedexAdapter
import vara17.pokedexmvp.ui.interfaces.PokemonListOnClickListener
import vara17.pokedexmvp.ui.interfaces.PresenterActivity

class PokedexActivity : AppCompatActivity(), PresenterActivity<PokedexModel>,
    PokemonListOnClickListener {

    private lateinit var podekexModel: PokedexModel
    private lateinit var binding: ActivityPokedexBinding
    private lateinit var presenter: PokedexPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showUI(State.Loading)
        presenter = PokedexPresenter(this)
        presenter.getPokedex()
    }


    override fun setData(data: PokedexModel) {
        podekexModel = data
        loadPokedex()
    }

    private fun loadPokedex() {
        binding.list.adapter = PokedexAdapter(podekexModel, this)
        binding.list.setHasFixedSize(true)
        binding.list.layoutManager = LinearLayoutManager(this)
        showUI(State.Success)
    }

    override fun showError(message: String) {
        showUI(State.Error)
    }

    override fun pokemonSelected(id: Int) {
        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun showUI(state: State) {
        val pokedexView = findViewById<View>(R.id.pokedexContainer)
        val loadingView = findViewById<View>(R.id.pokedexLoading)
        val errorView = findViewById<View>(R.id.pokedexError)
        when (state) {
            State.Loading -> {
                pokedexView.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
            }
            State.Success -> {
                pokedexView.visibility = View.VISIBLE
                loadingView.visibility = View.GONE
                errorView.visibility = View.GONE
            }
            State.Error -> {
                pokedexView.visibility = View.GONE
                loadingView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            }
        }
    }
}
