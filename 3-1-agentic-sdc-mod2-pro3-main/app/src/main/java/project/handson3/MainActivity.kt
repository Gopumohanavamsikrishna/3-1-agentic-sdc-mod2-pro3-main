package project.handson3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import project.handson3.model.PromptRequest
import project.handson3.network.ApiClient

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PromptAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var etTask: EditText
    private lateinit var btnGenerate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        etTask = findViewById(R.id.etTask)
        btnGenerate = findViewById(R.id.btnGenerate)

        adapter = PromptAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnGenerate.setOnClickListener {
            generate()
        }
    }

    private fun generate() {
        val task = etTask.text.toString()
        if (task.isBlank()) return

        lifecycleScope.launch {
            try {
                // Call the Python Backend via Retrofit
                val response = ApiClient.api.generate(PromptRequest(task))

                // Wrap single response in a list for the adapter
                adapter.update(listOf(response))

                etTask.text.clear()
            } catch (e: Exception) {
                e.printStackTrace()
                // Helpful for debugging: print the error to see if connection failed
            }
        }
    }
}