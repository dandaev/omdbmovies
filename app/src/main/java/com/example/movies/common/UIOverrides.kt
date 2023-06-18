package com.example.movies.common

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
                frameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }
            frameworkPaint.color = color.toArgb()

            val leftPixel = offsetX.toPx()
            val topPixel = offsetY.toPx()
            val rightPixel = size.width
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

//fun Modifier.coloredShadow(
//    color: Color,
//    alpha: Float = 1f,
//    borderRadius: Dp = 0.dp,
//    shadowRadius: Dp = 20.dp,
//    offsetY: Dp = 0.dp,
//    offsetX: Dp = 0.dp
//) = composed {
//    val shadowColor = color.copy(alpha = alpha).toArgb()
//    val transparent = color.copy(alpha= 0f).toArgb()
//    this.drawBehind {
//        this.drawIntoCanvas {
//            val paint = Paint()
//            val frameworkPaint = paint.asFrameworkPaint()
//            frameworkPaint.color = color.toArgb()
//            frameworkPaint.setShadowLayer(
//                shadowRadius.toPx(),
//                offsetX.toPx(),
//                offsetY.toPx(),
//                color.toArgb()
//            )
//            it.drawRoundRect(
//                0f,
//                0f,
//                this.size.width,
//                this.size.height,
//                borderRadius.toPx(),
//                borderRadius.toPx(),
//                paint
//            )
//        }
//    }
//}