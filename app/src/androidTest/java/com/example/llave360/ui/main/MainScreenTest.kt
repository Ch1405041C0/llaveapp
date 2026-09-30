package com.example.llave360.ui.main

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.llave360.model.AppState
import com.example.llave360.theme.Llave360Theme
import com.example.llave360.ui.capital.CapitalScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CapitalScreenTest {

  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Before
  fun setup() {
    composeTestRule.setContent { Llave360Theme { CapitalScreen(AppState(), {}, {}, {}) } }
  }

  @Test
  fun title_exists() {
    composeTestRule.onNodeWithText("¿Con qué capital contás?").assertExists()
  }
}
