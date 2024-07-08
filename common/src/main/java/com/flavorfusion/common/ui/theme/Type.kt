package com.flavorfusion.common.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.flavorfusion.common.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val NunitoFont = GoogleFont("Nunito")

val NunitoFontFontFamily = FontFamily(
    Font(googleFont = NunitoFont, fontProvider = provider)
)