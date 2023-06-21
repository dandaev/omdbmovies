package com.example.movies.common

import android.graphics.BlurMaskFilter
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.movies.presentation.ui.theme.LightBlue10

fun Modifier.shadow(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width + 20.dp.toPx()
            val bottomPixel = size.height

            canvas.drawRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                paint = paint,
            )
        }
    }
)

fun Modifier.drawLeftLine() = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val paint = Paint()
            val gradientColors = intArrayOf(LightBlue10.toArgb(),LightBlue10.toArgb(),Color.Transparent.toArgb())
            paint.shader = LinearGradient(0f,0f,size.height,0f,gradientColors,null, Shader.TileMode.CLAMP)
//            val frameworkPaint = paint.asFrameworkPaint()
//            frameworkPaint.color = Color.Transparent.toArgb()


            val center = Offset(x = center.x + 6, y = center.y)
            val radius = size.height / 2

            canvas.drawCircle(
                center = center,
                radius = radius,
                paint
            )
        }
    }
)
fun Modifier.vertical() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }
    }

