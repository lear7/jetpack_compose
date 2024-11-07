package com.lear.compose.widgets

import android.view.Choreographer
import androidx.collection.MutableLongList
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.packFloats
import androidx.compose.ui.util.unpackFloat1
import androidx.compose.ui.util.unpackFloat2
import kotlin.math.roundToInt

private const val UPDATE_FPS_EVERY_MS = 60
private const val GRAPH_Y_AXIS_FPS_LIMIT = 144

@Composable
fun FpsCounterGraph(modifier: Modifier = Modifier) {
    var fps by remember { mutableIntStateOf(0) }
    var updateCount by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .padding(4.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(4.dp),
            text = "FPS: $fps",
            fontStyle = FontStyle.Italic
        )
        FrameRateGraph(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(4.dp))
                .background(Color.DarkGray.copy(alpha = 0.25f)),
            fps = fps,
            updateCount = updateCount
        )
    }

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
                    updateCount += 1
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

@Composable
private fun FrameRateGraph(fps: Int, updateCount: Int, modifier: Modifier = Modifier) {
    val graphPoints = remember { MutableLongList(initialCapacity = 0) }

    Canvas(modifier) {
        val canvasWidth = drawContext.size.width
        if (graphPoints.capacity == 0)
            graphPoints.ensureCapacity(canvasWidth.toInt())

        var firstPointX = 0f
        if (graphPoints.size == graphPoints.capacity)
            firstPointX = unpackFloat1(graphPoints.removeAt(0))

        val x = updateCount.toFloat()
        val y = size.height - (fps / GRAPH_Y_AXIS_FPS_LIMIT.toFloat() * size.height)
        graphPoints.add(packFloats(x, y))

        translate(left = if (x > canvasWidth) -firstPointX else 0f) {
            graphPoints.forEach {
                drawCircle(Color.Red, 1f, Offset(unpackFloat1(it), unpackFloat2(it)))
            }
        }
    }
}