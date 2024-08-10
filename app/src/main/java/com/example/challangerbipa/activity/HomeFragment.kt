package com.example.challangerbipa.activity

import NodesViewModel
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangerbipa.databinding.FragmentHomeBinding
import com.example.challangerbipa.presentantion.adapter.NodeAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var nodeAdapter: NodeAdapter
    private val viewModel: NodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTopNodes()
        return binding.root
    }

    private fun setupRecyclerView() {
        nodeAdapter = NodeAdapter(emptyList()) { publicKey ->
            copyPublicKeyToClipboard(publicKey)
        }
        binding.recyclerViewNodes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = nodeAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchTopNodes()
        }
    }

    private fun observeViewModel() {
        viewModel.nodes.observe(viewLifecycleOwner) { nodes ->
            nodeAdapter.updateNodes(nodes)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun copyPublicKeyToClipboard(publicKey: String) {
        val clipboard = requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Public Key", publicKey)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Public Key copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}

