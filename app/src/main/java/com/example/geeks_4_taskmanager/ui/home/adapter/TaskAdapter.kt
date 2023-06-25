package com.example.geeks_4_taskmanager.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geeks_4_taskmanager.databinding.ItemTaskBinding
import com.example.geeks_4_taskmanager.model.Task

class TaskAdapter(val onLongClick: (Task) -> Unit, val onClick: (Task) -> Unit) :
    Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun onBind(task: Task) = with(binding) {
            tvTitle.text = task.title
            tvDesc.text = task.desc
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
            itemView.setOnClickListener {
                onClick(task)
            }
        }
    }

}