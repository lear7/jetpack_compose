package com.lear.compose.widgets.providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.lear.compose.widgets.data.NationalDayNoticeItemPreviewData

//初始化
class DayProvider : PreviewParameterProvider<NationalDayNoticeItemPreviewData> {
    private val noticeName = "你要上班啦"
    private val onClose = {

    }

    private val nationalDayNoticeItemPreviewData =
        NationalDayNoticeItemPreviewData(noticeName, onClose)

    override val values: Sequence<NationalDayNoticeItemPreviewData>
        get() {
            return listOf(nationalDayNoticeItemPreviewData).asSequence()
        }

}