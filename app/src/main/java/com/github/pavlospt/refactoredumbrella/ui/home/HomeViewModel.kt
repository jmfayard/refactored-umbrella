package com.github.pavlospt.refactoredumbrella.ui.home

import androidx.lifecycle.*
import com.github.pavlospt.refactoredumbrella.repo.AddRepoUseCase
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val addRepoUseCase: AddRepoUseCase
) : ViewModel() {

    private val _intentChannel = ConflatedBroadcastChannel<HomeViewIntent>()

    private val _uiEvents: MutableLiveData<HomeUIEvent> = MutableLiveData()
    val uiEvents: LiveData<HomeUIEvent>
        get() = _uiEvents

    init {
        _intentChannel
            .asFlow()
            .onEach { viewIntent ->
                when (viewIntent) {
                    is HomeViewIntent.AddGithubRepo -> addGithubRepo(githubRepo = viewIntent)
                }
            }
            .launchIn(viewModelScope)
    }

    suspend fun processIntent(intentHome: HomeViewIntent) = _intentChannel.send(intentHome)

    private suspend fun addGithubRepo(githubRepo: HomeViewIntent.AddGithubRepo) {
        addRepoUseCase(
            AddRepoUseCase.Params(
                repoName = githubRepo.repoName,
                repoStars = githubRepo.repoStars
            )
        )

        _uiEvents.value = HomeUIEvent.RepoAdded
    }
}
