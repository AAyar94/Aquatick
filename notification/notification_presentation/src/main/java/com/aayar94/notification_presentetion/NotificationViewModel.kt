package com.aayar94.notification_presentetion

import androidx.lifecycle.ViewModel
import com.aayar94.core.util.UiEvent
import com.aayar94.notification_domain.model.NotificationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onFetchNotifications() {
        val list = listOf(
            NotificationItem(
                image = "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2023/12/5376-Dec_blog-header_New-Year-Message_A_V1.png",
                title = "Happy New Year",
                time = LocalDateTime.of(2023, 12, 28, 10, 0)
            ), NotificationItem(title = "New Year Sale", time = LocalDateTime.of(2024, 1, 1, 0, 0))
        )

        _uiState.update { it.copy(list = list) }
    }
}