package com.github.pavlospt.refactoredumbrella.repo

import app.cash.turbine.test
import com.github.pavlospt.refactoredumbrella.db.github.GithubRepoEntity
import com.github.pavlospt.refactoredumbrella.test.MockGithubLocalRepo
import com.github.pavlospt.refactoredumbrella.test.UnitTest
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class, ExperimentalTime::class)
class ObserveGithubReposUseCaseTest : UnitTest() {

    @Test
    fun test_should_return_repos_from_local_repo() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val localRepos = listOf(
                GithubRepoEntity(
                    internalId = null,
                    remoteId = 1,
                    name = "foo",
                    stars = 123,
                    url = "",
                    ownerAvatarUrl = ""
                ),
                GithubRepoEntity(
                    internalId = null,
                    remoteId = 2,
                    name = "bar",
                    stars = 432,
                    url = "",
                    ownerAvatarUrl = ""
                )
            )
            val mockGithubLocalRepo = MockGithubLocalRepo(observedGithubRepos = localRepos)

            val useCase =
                com.github.pavlospt.refactoredumbrella.usecase.github.ObserveGithubReposUseCase(
                    appCoroutineDispatchers = testAppCoroutineDispatchers,
                    githubLocalRepo = mockGithubLocalRepo
                )

            useCase(Unit)

            launch {
                useCase.observe().test { assertEquals(localRepos, expectItem()) }
            }
        }
}
