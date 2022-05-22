package com.feiyatsu.rickandmortyapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.feiyatsu.rickandmortyapp.R
import com.feiyatsu.rickandmortyapp.databinding.RowCharacterBinding
import com.feiyatsu.rickandmortyapp.network.model.Character

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    //    private var characters: MutableList<Character> = mutableListOf()
    private var characters: MutableList<Character> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            RowCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
//        CharacterViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.row_character, parent, false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bindCharacterToView(characters[position])

    override fun getItemCount(): Int = characters.size

    fun addCharacters(fetchedCharacters: List<Character>) {
        val oldList = characters
        characters = fetchedCharacters.toMutableList()

        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            CharactersDiffUtil(
                oldList, fetchedCharacters
            )
        )
        diffResult.dispatchUpdatesTo(this)
//        characters.clear()
//        characters.addAll(fetchedCharacters)
//        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val characterBinding: RowCharacterBinding) :
        RecyclerView.ViewHolder(characterBinding.root) {

        fun bindCharacterToView(character: Character) {
            characterBinding.apply {
                tvCharacterName.text = character.name
                tvCharacterSpecies.text = character.species
                tvCharacterStatus.text = character.status
                setImage(character)
            }
        }

        private fun setImage(character: Character) {
            if (character.image.isNotEmpty()) {
                Glide.with(itemView)
                    .load(character.image)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .error(R.drawable.ic_empty)
                    .fallback(R.drawable.ic_empty)
                    .into(characterBinding.ivCharacter)
            }
        }
    }

//    inner class CharacterViewHolder(private val view:View):RecyclerView.ViewHolder(view){
//        private val image: ImageView by lazy {
//            view.findViewById<ImageView>(R.id.iv_character)
//        }
//        private val name: TextView by lazy {
//            view.findViewById<TextView>(R.id.tv_character_name)
//        }
//        private val status: TextView by lazy {
//            view.findViewById<TextView>(R.id.tv_character_status)
//        }
//        private val species: TextView by lazy {
//            view.findViewById<TextView>(R.id.tv_character_species)
//        }
//
//        fun bindCharacterToView(character: Character) {
//            name.text = character.name
//            status.text = character.status
//            species.text = character.species
//            setImage(character)
//        }
//
//        fun setImage(character: Character) {
//            if (character.image.isNotEmpty()) {
//                Glide.with(itemView)
//                    .load(character.image)
//                    .centerCrop()
//                    .diskCacheStrategy(DiskCacheStrategy.DATA)
//                    .error(R.drawable.ic_empty)
//                    .fallback(R.drawable.ic_empty)
//                    .into(image)
//            }
//        }
//    }
}