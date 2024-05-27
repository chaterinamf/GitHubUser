package com.dicoding.githubuser.presentation.detail.follow

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.githubuser.presentation.detail.UserDetailFragment

class SectionsPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    var username: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = bundleOf(
            UserDetailFragment.USERNAME to username,
            UserDetailFragment.FOLLOW_POSITION to position
        )
        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}