package com.aayar94.aquatick.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices.PIXEL_2
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Day", device = PIXEL_4_XL)
@Preview(name = "Night", device = PIXEL_4_XL, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Day Pixel 2", device = PIXEL_2)
@Preview(name = "Night Pixel 2", device = PIXEL_2,uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class DevicesPreview