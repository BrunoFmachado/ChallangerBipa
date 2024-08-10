import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challangerbipa.api.LightningApiService
import com.example.challangerbipa.data.model.Node
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NodesViewModel : ViewModel() {
    private val _nodes = MutableLiveData<List<Node>>()
    val nodes: LiveData<List<Node>> = _nodes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val apiService = Retrofit.Builder()
        .baseUrl("https://mempool.space/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LightningApiService::class.java)

    fun fetchTopNodes() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val nodes = apiService.getTopNodes()
                _nodes.postValue(nodes)
                _error.postValue(null)
            } catch (e: Exception) {
                _error.postValue("Failed to load data")
            } finally {
                _loading.postValue(false)
            }
        }
    }
}
