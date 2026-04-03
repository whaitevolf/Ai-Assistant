package com.ai.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import com.ai.assistant.core.ai.AISettingsRepository
import com.ai.assistant.core.storage.UserPrefs
import com.ai.assistant.ui.ChatScreen
import com.ai.assistant.ui.SettingsScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = AppContainer(this)
        val repository = AISettingsRepository(UserPrefs(this))

        setContent {
            var showChat by remember { mutableStateOf(false) }
            var isChecking by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    val key = repository.getKey()
                    showChat = key.isNotBlank()
                    isChecking = false
                }
            }

            if (isChecking) {
                CircularProgressIndicator()
            } else if (showChat) {
                ChatScreen(container.orchestrator)
            } else {
                SettingsScreen(
                    repository = repository,
                    onKeySaved = { showChat = true }
                )
            }
        }
    }
}