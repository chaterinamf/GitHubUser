package com.dicoding.githubuser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dicoding.githubuser.core.domain.model.GitHubUser
import com.dicoding.githubuser.core.domain.model.Result
import com.dicoding.githubuser.core.domain.usecase.list.ListUserUseCase
import com.dicoding.githubuser.presentation.list.ListUserViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class ListUserViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var useCase: ListUserUseCase
    private lateinit var viewModel: ListUserViewModel
    private lateinit var observer: Observer<Result<List<GitHubUser>>>

    @Before
    fun setUp() {
        useCase = mockk()
        viewModel = ListUserViewModel(useCase)
        observer = mockk(relaxed = true)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchUser should update listUser LiveData`() {
        // Given
        val username = "testuser"
        val githubUser = GitHubUser("chaterinamf", "https://avatars.githubusercontent.com/u/81873379?s=60&v=4")
        val result = Result.Success(listOf(githubUser))
        val flow: Flow<Result<List<GitHubUser>>> = flowOf(result)

        coEvery { useCase.searchUser(username) } returns flow

        // When
        viewModel.listUser.observeForever(observer)
        viewModel.searchUser(username)

        // Then
        coVerify { useCase.searchUser(username) }
        assertEquals(result, viewModel.listUser.value)
        verify { observer.onChanged(result) }
    }
}