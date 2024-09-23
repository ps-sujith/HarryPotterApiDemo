package com.sujith.ui.utils

import com.sujith.harrypotterapi.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()
}