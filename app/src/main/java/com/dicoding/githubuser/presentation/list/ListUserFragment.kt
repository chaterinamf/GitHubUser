package com.dicoding.githubuser.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dicoding.githubuser.R
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import com.dicoding.githubuser.core.ui.UserAdapter
import com.dicoding.githubuser.databinding.FragmentListUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUserFragment : Fragment(), UserAdapter.ItemUserListener {
    private val binding: FragmentListUserBinding by lazy {
        FragmentListUserBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<ListUserViewModel>()
    private val adapter by lazy {
        UserAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchBar()
        handleMenuAction()
        getListUser()
    }

    private fun handleMenuAction() = with(binding.searchBar) {
        setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.favourite_users -> {
                    findNavController().navigate(ListUserFragmentDirections.openFavouriteUsers())
                    true
                }

                else -> false
            }
        }
    }

    private fun getListUser() {
        viewModel.listUser.observe(viewLifecycleOwner) { result ->
            showLoading(result is Result.Loading)
            when (result) {
                is Result.Error -> Toast.makeText(
                    binding.root.context,
                    "Terjadi kesalahan " + result.error,
                    Toast.LENGTH_SHORT
                ).show()

                is Result.Loading -> {}
                is Result.Success -> setListUserData(result.data)
            }
        }
    }

    private fun setupSearchBar() = with(binding) {
        searchView.setupWithSearchBar(searchBar)
        searchView
            .editText
            .setOnEditorActionListener { textView, _, _ ->
                searchBar.setText(textView.text)
                viewModel.searchUser(textView.text.toString())
                searchView.hide()
                getSystemService(
                    root.context,
                    InputMethodManager::class.java
                )?.hideSoftInputFromWindow(root.windowToken, 0)
                false
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
        findNavController().navigate(ListUserFragmentDirections.openUserDetail(username))
    }
}