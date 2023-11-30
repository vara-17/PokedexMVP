package vara17.pokedexmvp.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import vara17.pokedexmvp.R
import vara17.pokedexmvp.model.data.PokemonListInfo
import vara17.pokedexmvp.ui.interfaces.PokemonListOnClickListener

class PokedexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.name)
    val number: TextView = itemView.findViewById(R.id.number)
    val container: CardView = itemView.findViewById(R.id.container)

    fun setData(info: PokemonListInfo, listener: PokemonListOnClickListener) {
        this.name.text = info.name.uppercase()
        this.number.text = "#${info.id}"
        this.container.setOnClickListener {
            listener.pokemonSelected(info.id)
        }

    }
}
