package com.example.exconcatadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exconcatadapter.databinding.ItemTipBinding

class TipAdapter :
    ListAdapter<Tip, TipAdapter.TipViewHolder>(DIFF_CALLBACK) {

    /*Isso é para dar um retorno que o item foi clicado (o entendi)*/
    var gotItItemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        return TipViewHolder.create(parent, gotItItemClickListener)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tip>() {
            override fun areItemsTheSame(oldItem: Tip, newItem: Tip): Boolean {
                return oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: Tip, newItem: Tip): Boolean {
                return oldItem == newItem
            }
        }
    }

    class TipViewHolder(
        private val binding: ItemTipBinding,
        private val gotItItemClickListener: (() -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tip: Tip) {
            binding.run {
                textTip.text = tip.description
            }
            binding.textGotIt.setOnClickListener {
                /*quando o item for clicado ele retornará esse click*/
                gotItItemClickListener?.invoke()
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                gotItItemClickListener: (() -> Unit)?
            ): TipViewHolder {
                val itemBinding = ItemTipBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return TipViewHolder(itemBinding, gotItItemClickListener)
            }
        }
    }
}