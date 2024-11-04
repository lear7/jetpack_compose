package com.lear.compose.widgets.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

//简单实现如，values是一个列表数据，传入多个就会生成多个预览视图
class StringProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = listOf("你要上班啦","你要上学啦").asSequence()
}