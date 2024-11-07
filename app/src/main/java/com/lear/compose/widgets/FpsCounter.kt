package com.lear.compose.widgets

import android.view.Choreographer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlin.math.roundToInt

private const val UPDATE_FPS_EVERY_MS = 60

@Composable
fun FpsCounter(modifier: Modifier = Modifier) {
    var fps by remember { mutableIntStateOf(0) }

    Text("FPS: $fps", modifier = modifier)

    DisposableEffect(Unit) {
        val everyFrameCallback = object : Choreographer.FrameCallback {
            var acc = 0.0
            var latestFrameTime = 0.0

            override fun doFrame(frameTimeNanos: Long) {
                val frameTimeMillis: Double = frameTimeNanos / 1_000_000.0
                val deltaMillis = frameTimeMillis - latestFrameTime
                acc += deltaMillis
                if (acc >= UPDATE_FPS_EVERY_MS) {
                    fps = (1000.0 / deltaMillis).roundToInt()
                    acc = 0.0
                }
                latestFrameTime = frameTimeMillis
                Choreographer.getInstance().postFrameCallback(this) // Enqueue again
            }
        }

        Choreographer.getInstance().postFrameCallback(everyFrameCallback)

        onDispose {
            Choreographer.getInstance().removeFrameCallback(everyFrameCallback)
        }
    }
}