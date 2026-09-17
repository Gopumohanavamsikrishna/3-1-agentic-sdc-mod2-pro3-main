package project.handson3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.handson3.model.PromptResponse

class PromptAdapter(
    private val list: MutableList<PromptResponse>
) : RecyclerView.Adapter<PromptAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Ensure these IDs match your item_response.xml layout
        val txtStatus: TextView = itemView.findViewById(R.id.txtPromptType)
        val txtReply: TextView = itemView.findViewById(R.id.txtResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_response, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        // Mapping status to the 'type' field and reply to the 'response' field
        holder.txtStatus.text = item.status
        holder.txtReply.text = item.reply
    }

    fun update(newList: List<PromptResponse>) {
        // Changed promptList to 'list' to match the constructor variable name
        this.list.clear()
        this.list.addAll(newList)
        notifyDataSetChanged()
    }
}