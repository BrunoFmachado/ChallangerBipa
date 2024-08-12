package com.example.challangerbipa.ui

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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challangerbipa.databinding.FragmentHomeBinding
import com.example.challangerbipa.domain.listener.NodeInteractionListener
import com.example.challangerbipa.presentantion.adapter.NodeAdapter
import com.example.challangerbipa.presentantion.viewModel.NodesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), NodeInteractionListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var nodeAdapter: NodeAdapter
    private val viewModel: NodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTopNodes()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        nodeAdapter = NodeAdapter(emptyList(), this)
        binding.recyclerViewNodes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = nodeAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchTopNodes()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.nodes.collect { nodes ->
                nodeAdapter.updateNodes(nodes)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loading.collect { isLoading ->
                binding.swipeRefreshLayout.isRefreshing = isLoading
            }
        }
    }

    override fun onCopyPublicKey(publicKey: String) {
        val clipboard = requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Public Key", publicKey)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Public Key copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}
