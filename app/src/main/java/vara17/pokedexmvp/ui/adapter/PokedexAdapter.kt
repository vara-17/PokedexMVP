package vara17.pokedexmvp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vara17.pokedexmvp.R
import vara17.pokedexmvp.model.data.PokedexModel
import vara17.pokedexmvp.ui.adapter.viewholder.PokedexViewHolder
import vara17.pokedexmvp.ui.interfaces.PokemonListOnClickListener

class PokedexAdapter(private val data: PokedexModel, private val listener: PokemonListOnClickListener) : RecyclerView.Adapter<PokedexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false)
        return PokedexViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        holder.setData(data.pokedex.get(position), listener)
    }

    override fun getItemCount(): Int {
        return data.pokedex.size
    }
}
