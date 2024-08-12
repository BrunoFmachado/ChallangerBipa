package com.example.challangerbipa.presentantion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.challangerbipa.data.model.Node
import com.example.challangerbipa.databinding.ItemNodeBinding
import com.example.challangerbipa.domain.listener.NodeInteractionListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NodeAdapter(
    private var nodes: List<Node>,
    private val listener: NodeInteractionListener
) : RecyclerView.Adapter<NodeAdapter.NodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val binding = ItemNodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        holder.bind(nodes[position])
    }

    override fun getItemCount(): Int = nodes.size

    fun updateNodes(newNodes: List<Node>) {
        val diffCallback = NodeDiffCallback(nodes, newNodes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        nodes = newNodes
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NodeViewHolder(private val binding: ItemNodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(node: Node) {
            binding.apply {
                textViewAlias.text = node.alias
                textViewPublicKey.text = "Public Key: ${node.publicKey}"
                textViewChannels.text = "Channels: ${node.channels}"
                textViewCapacity.text = "Capacity: ${satsToBtc(node.capacity)}"
                textViewFirstSeen.text = "First Seen: ${unixTimeToDateTime(node.firstSeen)}"
                textViewUpdatedAt.text = "Updated At: ${unixTimeToDateTime(node.updatedAt)}"

                val city = node.location?.city ?: "Unknown City"
                val country = node.location?.country ?: "Unknown Country"
                textViewLocation.text = "Location: $city, $country"

                buttonCopyKey.setOnClickListener {
                    listener.onCopyPublicKey(node.publicKey)
                }
            }
        }

        private fun satsToBtc(sats: Long): String {
            val btc = sats / 100_000_000.0
            return "%.8f BTC".format(btc)
        }

        private fun unixTimeToDateTime(unixTime: Long): String {
            val date = Date(unixTime * 1000L)
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            return sdf.format(date)
        }
    }
}

class NodeDiffCallback(
    private val oldList: List<Node>,
    private val newList: List<Node>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].publicKey == newList[newItemPosition].publicKey
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
