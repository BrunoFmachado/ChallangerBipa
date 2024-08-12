import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challangerbipa.data.model.Node
import com.example.challangerbipa.repository.NodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NodesViewModel @Inject constructor(
    private val nodeRepository: NodeRepository
) : ViewModel() {

    private val _nodes = MutableStateFlow<List<Node>>(emptyList())
    val nodes: StateFlow<List<Node>> = _nodes

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchTopNodes() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val nodes = nodeRepository.fetchTopNodes()
                _nodes.value = nodes
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load data"
            } finally {
                _loading.value = false
            }
        }
    }
}
