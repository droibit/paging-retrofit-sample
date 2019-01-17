package com.star_zero.pagingretrofitsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.star_zero.pagingretrofitsample.R
import com.star_zero.pagingretrofitsample.data.Repo
import com.star_zero.pagingretrofitsample.databinding.ItemRepoBinding

class RepoAdapter : PagedListAdapter<Repo, RepoAdapter.ViewHolder>(DIFF_CALLBACK) {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val binding: ItemRepoBinding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.item_repo, parent, false
    )
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    holder.bindTo(getItem(position))
  }

  class ViewHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(
      binding.root
  ) {

    fun bindTo(repo: Repo?) {
      binding.repo = repo
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
      override fun areItemsTheSame(
        oldItem: Repo,
        newItem: Repo
      ): Boolean {
        return oldItem.id == newItem.id
      }

      override fun areContentsTheSame(
        oldItem: Repo,
        newItem: Repo
      ): Boolean {
        return oldItem == newItem
      }
    }
  }
}

