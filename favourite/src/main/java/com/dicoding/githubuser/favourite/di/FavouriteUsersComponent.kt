package com.dicoding.githubuser.favourite.di

import android.content.Context
import com.dicoding.githubuser.di.DaggerDependencies
import com.dicoding.githubuser.favourite.presentation.FavouriteUsersFragment
import dagger.BindsInstance
import dagger.Component

/**
 * Since Hilt doesn't support Multi-Module,
 * we must use Dagger to perform dependency injection in your feature modules
 * Shout out to: https://pedrookawa.medium.com/android-clean-architecture-with-dynamic-features-and-hilt-dagger2-pt-3-ca40d2316fc8
 */
@Component(dependencies = [DaggerDependencies::class])
interface FavouriteUsersComponent {
    fun inject(fragment: FavouriteUsersFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favouriteModuleDependencies: DaggerDependencies): Builder
        fun build(): FavouriteUsersComponent
    }
}