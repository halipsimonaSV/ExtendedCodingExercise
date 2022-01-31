package com.example.extendedcodingexercise.features.album

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.example.extendedcodingexercise.databinding.ListItemAlbumBinding
import com.example.extendedcodingexercise.domain.Album
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class AlbumsAdapter @Inject constructor(@ApplicationContext private val context: Context, private val clickListener: (albumId: Int) -> Unit) :
    ListAdapter<Album, AlbumsAdapter.AlbumViewHolder>(AlbumDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album, context, clickListener)
    }

    class AlbumViewHolder private constructor(private val binding: ListItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Album, context: Context, clickListener: (albumId: Int) -> Unit) {
            binding.albumTitle.text = item.title
            val androidColors: IntArray =
                context.resources.getIntArray(com.example.extendedcodingexercise.R.array.avatarcolors)
            val avatarColor = androidColors[Random.nextInt(androidColors.size)]
            val drawable: TextDrawable = TextDrawable.Builder()
                .setColor(avatarColor)
                .setBold()
                .setShape(TextDrawable.SHAPE_RECT)
                .setText(item.userInitials).build()
            binding.ivUserInitials.setImageDrawable(drawable)
            binding.ivUserInitials.setOnClickListener {
                clickListener(item.userId)
            }

        }

        companion object {
            fun from(parent: ViewGroup): AlbumViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAlbumBinding.inflate(layoutInflater, parent, false)
                return AlbumViewHolder(binding)
            }
        }
    }
}

class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}
