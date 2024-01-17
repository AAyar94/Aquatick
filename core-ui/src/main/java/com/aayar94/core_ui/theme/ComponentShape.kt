package com.aayar94.core_ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ComponentShape(
    val defaultShape: RoundedCornerShape = RoundedCornerShape(0.dp),
    val smallCornerRadius: RoundedCornerShape = RoundedCornerShape(4.dp),
    val mediumCornerRadius: RoundedCornerShape = RoundedCornerShape(8.dp),
    val largeCornerRadius: RoundedCornerShape = RoundedCornerShape(12.dp),
    val extraLargeCornerRadius: RoundedCornerShape = RoundedCornerShape(20.dp)
)

val LocalShape = compositionLocalOf { ComponentShape() }