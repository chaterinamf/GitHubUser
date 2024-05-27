package com.dicoding.githubuser.presentation.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import com.dicoding.githubuser.core.ui.UserAdapter
import com.dicoding.githubuser.databinding.FragmentFollowBinding
import com.dicoding.githubuser.presentation.detail.UserDetailFragment.Companion.FOLLOW_POSITION
import com.dicoding.githubuser.presentation.detail.UserDetailFragment.Companion.USERNAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowFragment : Fragment(), UserAdapter.ItemUserListener {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FollowViewModel>()
    private val adapter by lazy {
        UserAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            getFollow(it.getInt(FOLLOW_POSITION), it.getString(USERNAME).orEmpty())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvUsers.adapter = null
        adapter.submitList(null)
        _binding = null
    }

    private fun getFollow(position: Int, username: String) {
        viewModel.getFollow(position, username).observe(viewLifecycleOwner) { result ->
            showLoading(result is Result.Loading)
            when (result) {
                is Result.Error -> Toast.makeText(
                    context,
                    "Terjadi kesalahan " + result.error,
                    Toast.LENGTH_SHORT
                ).show()

                is Result.Loading -> {}
                is Result.Success -> setListUserData(result.data)
            }
        }
    }

    private fun setListUserData(users: List<GitHubUser>) {
        adapter.submitList(users)
        binding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) = binding.apply {
        progressBar.isVisible = isLoading
        rvUsers.isVisible = !isLoading
    }

    override fun onUserClicked(username: String) {
        // Do nothing
    }
}