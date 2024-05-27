package com.dicoding.githubuser.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dicoding.githubuser.R
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import com.dicoding.githubuser.databinding.FragmentUserDetailBinding
import com.dicoding.githubuser.presentation.detail.follow.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UserDetailViewModel>()
    private val sectionsPagerAdapter by lazy {
        SectionsPagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
    }
    private var tabLayoutMediator: TabLayoutMediator? = null
    private val args: UserDetailFragmentArgs by navArgs()
    private lateinit var user: GitHubUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserDetail()
        setViewPager()
        setFavouriteView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            vpUser.adapter = null
            tlFollow.clearOnTabSelectedListeners()
        }
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        _binding = null
    }

    private fun getUserDetail() =
        viewModel.getUserDetail(args.username).observe(viewLifecycleOwner) { result ->
            showLoading(result is Result.Loading)
            when (result) {
                is Result.Error -> Toast.makeText(
                    binding.root.context,
                    "Terjadi kesalahan " + result.error,
                    Toast.LENGTH_SHORT
                ).show()

                is Result.Loading -> {}
                is Result.Success -> binding.apply {
                    Glide.with(this@UserDetailFragment)
                        .load(result.data.avatarUrl)
                        .into(sivAvatar)
                    tvName.text = result.data.name
                    tvUsername.text = result.data.username
                    tvFollow.text =
                        getString(
                            R.string.user_follow,
                            result.data.followers,
                            result.data.following
                        )
                    user = GitHubUser(result.data.username, result.data.avatarUrl)
                }
            }
        }

    private fun showLoading(isLoading: Boolean) = binding.apply {
        progressBar.isVisible = isLoading
        layoutContent.isVisible = !isLoading
        fabFavourite.isEnabled = !isLoading
    }

    private fun setViewPager() = with(binding) {
        sectionsPagerAdapter.username = args.username
        vpUser.adapter = sectionsPagerAdapter
        val tabName = resources.getStringArray(R.array.follow_tab_tittles)
        tabLayoutMediator = TabLayoutMediator(tlFollow, vpUser) { tab, position ->
            tab.text = tabName[position]
        }
        tabLayoutMediator?.attach()
    }

    private fun setFavouriteView() = with(binding.fabFavourite) {
        viewModel.isUserFavourite(args.username).observe(viewLifecycleOwner) {
            if (it) {
                setImageResource(R.drawable.ic_favorite_fill)
                setOnClickListener { viewModel.removeFromFavourite(user) }
            } else {
                setImageResource(R.drawable.ic_favorite_border)
                setOnClickListener { viewModel.addToFavourite(user) }
            }
        }
    }

    companion object {
        const val USERNAME = "username"
        const val FOLLOW_POSITION = "follow_position"
    }
}