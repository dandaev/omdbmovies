package com.example.movies.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movies.R

val montserratRegular = FontFamily(Font(R.font.montserrat_regular))
val montserratExtraBold = FontFamily(Font(R.font.montserrat_extra_bold))
val montserratSemiBold = FontFamily(Font(R.font.montserrat_semi_bold))
val montserratBold = FontFamily(Font(R.font.montserrat_bold))
val montserratMedium = FontFamily(Font(R.font.montserrat_medium))

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = montserratRegular,
        fontSize = 16.sp,
        color = BlueGrey80
    ),
    //Title
    displayMedium = TextStyle(
        fontFamily = montserratExtraBold,
        fontSize = 32.sp,
        lineHeight = 40.0.sp,
    ),
    //Rating
    displaySmall = TextStyle(
        fontFamily = montserratMedium,
        fontSize = 26.sp
    ),
    //Year
    headlineLarge = TextStyle(
        fontFamily = montserratRegular,
        fontSize = 20.sp
    ),
    //Genre
    titleLarge = TextStyle(
        fontFamily = montserratMedium,
        fontSize = 14.sp
    ),
    //Director
    headlineMedium = TextStyle(
        fontFamily = montserratRegular,
        fontSize = 18.sp
    ),
    //Bold Title
    headlineSmall = TextStyle(
        fontFamily = montserratBold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = montserratSemiBold,
        fontSize = 16.sp
    )

)

