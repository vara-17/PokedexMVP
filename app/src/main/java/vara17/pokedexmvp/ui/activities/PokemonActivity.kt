package vara17.pokedexmvp.ui.activities

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vara17.pokedexmvp.R
import vara17.pokedexmvp.databinding.ActivityPokemonBinding
import vara17.pokedexmvp.model.data.PokemonModel
import vara17.pokedexmvp.model.data.State
import vara17.pokedexmvp.model.presenter.PokemonPresenter
import vara17.pokedexmvp.ui.interfaces.PresenterActivity

class PokemonActivity : AppCompatActivity(), PresenterActivity<PokemonModel> {

    private lateinit var binding: ActivityPokemonBinding
    private lateinit var presenter: PokemonPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showUI(State.Loading)
        presenter = PokemonPresenter(this)
        val id = intent.getIntExtra("id", 0)
        if (id != 0) presenter.getPokemon(id) else showError("id is 0")
    }


    override fun setData(data: PokemonModel) {
        binding.name.text = data.name
        binding.number.text = "Pokedex #${data.number}"
        data.imageUrl?.let {
            CoroutineScope(Dispatchers.Main).launch {
                val bitmap = loadImage(it)
                updateUI(bitmap)
            }
        }
        binding.height.text = "Height: ${data.height}"
        binding.weight.text = "Weight: ${data.weight}"
        binding.types.text = "Types: ${data.types}"
        showUI(State.Success)
    }

    private suspend fun loadImage(imageUrl: String): Bitmap {
        return withContext(Dispatchers.IO) {
            Glide.with(this@PokemonActivity)
                .asBitmap()
                .load(imageUrl)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .submit()
                .get()
        }
    }

    private fun updateUI(bitmap: Bitmap) {
        binding.image.setImageBitmap(bitmap)
    }

    override fun showError(message: String) {
        showUI(State.Error)
    }

    private fun showUI(state: State) {
        val pokemonView = findViewById<View>(R.id.pokemonContainer)
        val loadingView = findViewById<View>(R.id.pokemonLoading)
        val errorView = findViewById<View>(R.id.pokemonError)
        when (state) {
            State.Loading -> {
                pokemonView.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
                errorView.visibility = View.GONE
            }
            State.Success -> {
                pokemonView.visibility = View.VISIBLE
                loadingView.visibility = View.GONE
                errorView.visibility = View.GONE
            }
            State.Error -> {
                pokemonView.visibility = View.GONE
                loadingView.visibility = View.GONE
                errorView.visibility = View.VISIBLE
            }
        }
    }
}