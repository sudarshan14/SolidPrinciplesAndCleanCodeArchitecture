package com.amlavati.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Shimmer loading animation
 *
 * @param isLoading   parameter for start/stop.
 * @param isLightMode  parameter for display modes.
 * @param durationMillis
 */
fun Modifier.shimmer(
    isLoading: Boolean = true,
    isLightMode: Boolean = true,
    widthOfShadowBrush: Int = 500,
    angleOfAxisY: Float = 270f,
    durationMillis: Int = 1000,
): Modifier {

    if (!isLoading) {
        return this@shimmer.alpha(1f)
    } else {

        return composed {
            val shimmerColors = ShimmerAnimationData(isLightMode = isLightMode).getColours()
            val transition = rememberInfiniteTransition(label = "")
            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = (durationMillis + widthOfShadowBrush).toFloat(),
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = durationMillis,
                        easing = LinearEasing,
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "",
            )

            this.background(
                brush = Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(x = translateAnimation.value - widthOfShadowBrush, y = 0.0f),
                    end = Offset(x = translateAnimation.value, y = angleOfAxisY),
                ),
                shape = RoundedCornerShape(4.dp)
            )
        }
            .alpha(0f)
    }
}

data class ShimmerAnimationData(
    private val isLightMode: Boolean
) {
    fun getColours(): List<Color> {
        return if (isLightMode) {
            val color = Color(color = 0xFFF4F4F4)

            listOf(
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 1.0f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 0.3f),
//                color.copy(alpha = 0.9f),
//                color.copy(alpha = 0.2f),
//                color.copy(alpha = 0.9f),
            )
        } else {
            val color = Color.Black

            listOf(
                color.copy(alpha = 0.0f),
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.5f),
                color.copy(alpha = 0.3f),
                color.copy(alpha = 0.0f),
//                color.copy(alpha = 0.9f),
//                color.copy(alpha = 0.2f),
//                color.copy(alpha = 0.9f),
            )
        }
    }
}
