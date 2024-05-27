package com.dicoding.githubuser.favourite.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dicoding.githubuser.core.ui.UserAdapter
import com.dicoding.githubuser.favourite.databinding.FragmentFavouriteUsersBinding
import com.dicoding.githubuser.favourite.utils.ViewModelFactory
import com.dicoding.githubuser.favourite.utils.inject
import javax.inject.Inject

class FavouriteUsersFragment : Fragment(), UserAdapter.ItemUserListener {
    private val binding: FragmentFavouriteUsersBinding by lazy {
        FragmentFavouriteUsersBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<FavouriteUsersViewModel>()
    {
        factory
    }

    private val adapter by lazy {
        UserAdapter(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(context.applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavouriteUsers.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.rvUsers.adapter = adapter
        }
    }

    override fun onUserClicked(username: String) {
        findNavController().navigate(FavouriteUsersFragmentDirections.openUserDetail(username))
    }
}