package khomsin.nui.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import khomsin.nui.todo.databinding.ItemTodoListBinding
import khomsin.nui.todo.model.TaskModel

class TodoListAdapter(var setOnItemClickListener : SetOnItemClickListener) : RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {

    interface SetOnItemClickListener{
        fun onItemDelete(data : TaskModel.Data)
        fun onItemUpdate(data : TaskModel.Data)
    }

    class TodoListViewHolder(itemBinding: ItemTodoListBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private var binding: ItemTodoListBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<TaskModel.Data>() {
        override fun areItemsTheSame(
            oldItem: TaskModel.Data,
            newItem: TaskModel.Data
        ): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: TaskModel.Data,
            newItem: TaskModel.Data
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        binding = ItemTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoListViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {

        holder.itemView.apply {
            binding?.tvDescription?.text = "description : ${differ.currentList.get(position).description}"
            binding?.tvCreatedAt?.text = "createdAt : ${differ.currentList.get(position).createdAt}"
            binding?.tvUpdatedAt?.text = "updatedAt ${differ.currentList.get(position).updatedAt}"
            binding?.tvComplete?.text = "complete : ${differ.currentList.get(position).completed}"
            binding?.flDelete?.setOnClickListener {
                setOnItemClickListener.onItemDelete(differ.currentList.get(position))
            }
        }.setOnClickListener {
            setOnItemClickListener.onItemUpdate(differ.currentList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}