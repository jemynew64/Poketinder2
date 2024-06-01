package com.leon.jemal.poketinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leon.jemal.poketinder.databinding.ItemPokemonBinding

//para instarcialo siempre le tengo que pasar un array list
class PokemonAdapter(
    var list: List<PokemonResponse>
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    //instancio la vista reutilizable/ expone los componentes visuales /se declara 1 veces
    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        //inyecto informacion
        val binding = ItemPokemonBinding.bind(view)

        // para bind nesecito inyectar uno por uno  / depende del tamaño del arraylist
        fun bind(pokemon : PokemonResponse){
            binding.tvName.text = pokemon.name
            Glide
                .with(itemView)
                .load(pokemon.getPokemonImage())
                .into(binding.ivPokemon)
        }
    }

    //implementando 3 metodos clave si o si me dice

    //configuro la vista reutilizable se invoka 1 vez
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val view = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    // aca recorro o invoco x veces basicamente un for each del arraylist /la veces que sea tu arraylist
    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        val itemPokemon = list[position]
        holder.bind(itemPokemon)
    }
    //1 vez para obtener lo largo de tu lista
    override fun getItemCount(): Int = list.size




}
