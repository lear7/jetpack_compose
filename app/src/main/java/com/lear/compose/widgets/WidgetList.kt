package com.lear.compose.widgets

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import coil.compose.AsyncImage
import com.lear.compose.R
import com.lear.compose.styles.Green700
import com.lear.compose.widgets.data.Message
import com.lear.compose.widgets.data.NationalDayNoticeItemPreviewData
import com.lear.compose.widgets.providers.StringProvider
import kotlinx.coroutines.launch
import kotlin.math.max

@Preview
@Composable
fun NationalDayNoticeItemPreview2(
    @PreviewParameter(
        StringProvider::class,
        1
    ) nationalDayNoticeItemPreviewData: NationalDayNoticeItemPreviewData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = nationalDayNoticeItemPreviewData.noticeName
        )
        IconButton(onClick = nationalDayNoticeItemPreviewData.onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Preview
@Composable
fun NationalDayNoticeItemPreview(
    @PreviewParameter(StringProvider::class, 1) noticeName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = noticeName
        )
        IconButton(onClick = {}) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Preview
@Composable
fun ButtonDemo() {
    Button(
        onClick = {
            println("button clicked")
        },
    ) {
        Text("我是按钮 button")
    }
}

@Composable
fun OutlinedButtonDemo() {
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            // showToast(context, "button clicked")
        },
        border = BorderStroke(1.dp, Color.Red),
        colors = ButtonDefaults.outlinedButtonColors(),
        shape = RoundedCornerShape(50)
    ) {
        Text("OutlinedButton", color = Color.Red)
    }
}


@Preview(showBackground = true)
@Composable
fun BoxDemo() {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .background(color = Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(color = Color.Blue)
                .align(Alignment.TopStart)
        )
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(color = Color.Green)
                .align(Alignment.TopEnd)
        )
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(color = Color.Yellow)
                .align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
fun CustomerTextField() {
    CustomTextField(
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = LocalContentColor.current.copy(alpha = 0.3f)
            )
        },
        trailingIcon = null,
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(
                color = Color.Red,
            )
            .width(200.dp)
            .height(40.dp),
        fontSize = 14.sp,
        placeholderText = "Search"
    )
}


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "",
    fontSize: TextUnit = TextUnit.Unspecified
) {
    var text by rememberSaveable { mutableStateOf("") }
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty())
                        Text(
                            placeholderText,
                            style = LocalTextStyle.current.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                fontSize = fontSize
                            )
                        )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun CircularProgressIndicatorDemo2() {
    CircularProgressIndicator()
}

@Preview(showBackground = true)
@Composable
fun CircularProgressIndicatorDemo() {
    var progress by remember {
        mutableStateOf(0.5f)
    }
    val animProgress by animateFloatAsState(targetValue = progress)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            progress = { animProgress },
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            color = Color.Red,
            strokeWidth = 5.dp,
            trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (progress < 1f) {
                progress += 0.1f
            }
        }) {
            Text("Add")
        }
    }
}

@Preview
@Composable
fun TabRowDemo() {
    val state = remember { mutableStateOf(0) }
    val titles = listOf<String>("推荐", "体育新闻", "Android软件工程师")
    Column {
        TabRow(selectedTabIndex = state.value) {
            titles.forEachIndexed { index, value ->
                Tab(
                    text = { Text(value) },
                    selected = state.value == index,
                    onClick = {
                        state.value = index
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "第${state.value}个Tab, ${titles[state.value]}",
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

@Preview
@Preview(showBackground = true)
@Composable
fun ScrollableTabRowDemo() {
    val state = remember { mutableStateOf(0) }
    val titles = listOf<String>("推荐", "Kotlin 入门到精通", "Android软件工程师", "Web前端工程师")
    Column {
        ScrollableTabRow(
            selectedTabIndex = state.value,
            modifier = Modifier
                .fillMaxWidth(),
            edgePadding = 6.dp
        ) {
            titles.forEachIndexed { index, value ->
                Tab(
                    text = {
                        Text(
                            value,
                            fontSize = if (state.value == index) 18.sp else 14.sp
                        )
                    },
                    selected = state.value == index,
                    onClick = {
                        state.value = index
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "第${state.value}个Tab, ${titles[state.value]}",
            style = TextStyle(fontSize = 20.sp)
        )
    }
}


@Composable
fun ShowAlertDialog(confirm: () -> Unit, dismiss: () -> Unit) {
    AlertDialog(
        shape = RoundedCornerShape(12.dp),
        onDismissRequest = dismiss, // Executes when the user tries to dismiss the Dialog by clicking outside or pressing the back button. This is not called when the dismiss button is clicked
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null
                )
                Text(text = "我是标题")
            }
        },
        text = {
            Text("我是内容")
        },
        confirmButton = {
            TextButton(onClick = confirm) {
                Text(text = "确定")
            }
        },
        dismissButton = {
            TextButton(onClick = confirm) {
                Text("取消")
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            securePolicy = SecureFlagPolicy.SecureOn
        )
    )
}


@Preview
@Composable
fun CardDemo() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Column {
            Image(
                modifier = Modifier.height(200.dp),
                painter = painterResource(id = R.mipmap.car),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                "New BMW 3",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )
        }
    }
}


@Preview
@Composable
fun SpacerDemo() {
    Column {
        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .size(width = 100.dp, height = 100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .background(color = Color.Blue)
                .size(width = 100.dp, height = 100.dp)
        )
    }
}


@Preview
@Composable
fun LazyColumnDemo() {
    val context = LocalContext.current
    val list = listOf("Android", "iOS", "HTML5", "Linux", "Kotlin")
    val onClick: (String) -> Unit = {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }
    LazyColumn {
        // 头部布局
        item {
            Image(
                painter = painterResource(id = R.mipmap.car),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
        }
        // 中间列表项布局
        // items(list.size) { index ->
        //     ContactsItem(list[index], onClick)
        // }
        // 尾部布局
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("加载更多")
            }
        }
    }
}


@Preview
@Composable
fun ColumnDemo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Red)
        )

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Green)
        )

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Blue)
        )

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Green)
        )

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Blue)
        )
    }
}

@Preview
@Composable
fun RowDemo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Red)
        )

        Box(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .background(Color.Green)
        )

        Box(
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .background(Color.Blue)
        )

        Box(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .background(Color.Yellow)
        )

        Box(
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .background(Color.Cyan)
        )
    }
}


@Preview
@Composable
fun DividerDemo() {
    Column {
        Text("Hello World，".repeat(50), maxLines = 2, modifier = Modifier.padding(15.dp))
        HorizontalDivider(thickness = 1.dp)
        Text("Hello World，".repeat(50), maxLines = 2, modifier = Modifier.padding(15.dp))
        HorizontalDivider(thickness = 1.dp)
        Text("Hello World，".repeat(50), maxLines = 2, modifier = Modifier.padding(15.dp))
    }
}


@Preview
@Composable
fun TextStringDemo() {
    Text(
        text = stringResource(id = R.string.text_content).repeat(50),   // String 类型
        style = TextStyle(
            fontSize = 18.sp,
            color = Color.Blue,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Left
        ),
        fontWeight = FontWeight.Medium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4,
        modifier = Modifier
            .background(Color.Red)
            .padding(30.dp)
    )
}

@Preview
@Composable
fun TextFieldDemo() {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        placeholder = {
            Text("input password")
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = null, modifier = Modifier
                    .width(12.dp)
                    .height(12.dp)
            )
        }
    )
}

@Preview
@Composable
fun ImageDemo() {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.mipmap.ic_launcher),
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .border(
                width = 1.5.dp,
                color = Color(0xFFDA8C1A),
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(shape = CircleShape)
            .clickable {
                Toast
                    .makeText(context, "image clicked", Toast.LENGTH_LONG)
                    .show()
            },
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Preview
@Composable
fun NetworkImageDemo() {
    AsyncImage(
        modifier = Modifier
            .size(40.dp),
        contentScale = ContentScale.Crop,
        model = "http://gips3.baidu.com/it/u=100751361,1567855012&fm=3028&app=3028&f=JPEG&fmt=auto?w=960&h=1280",
        contentDescription = "network image",
    )
}

@Preview
@Composable
fun RadioButtonDemo() {
    val labels = listOf("Android", "iOS", "Kotlin", "Java", "Jetpack Compose")
    val indexOfChecked = remember { mutableStateOf(-1) }
    Column {
        labels.forEachIndexed { index, value ->
            Row(
                modifier = Modifier
                    .selectableGroup()
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = indexOfChecked.value == index,
                    onClick = {
                        indexOfChecked.value = index
                    },
                )
                Text(value)
            }
        }
    }
}

@Preview
@Composable
fun TextAnnotatedStringDemo() {
    val builder = AnnotatedString.Builder("Hello").apply {
        pushStyle(
            SpanStyle(
                color = Color.Red,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal
            )
        )
        append("World, ")
        pop()
        append("The new Android UI")
    }
    val text = builder.toAnnotatedString()
    Text(
        text = text,  // AnnotatedString类型
        fontSize = 22.sp, modifier = Modifier.clickable {
        },
        maxLines = 5
    )
}

@Preview
@Composable
fun SwitchDemo() {
    val checkedState = remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
            }
        )
        Text(if (checkedState.value) "开启" else "关闭")
    }
}

@Preview(showBackground = true)
@Composable
fun LinearProgressIndicatorDemo() {
    LinearProgressIndicator()
}

@Preview(showBackground = true)
@Composable
fun LinearProgressIndicatorDemo2() {
    var progress by remember {
        mutableStateOf(0.5f)
    }
    val animProgress by animateFloatAsState(targetValue = progress)
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = { animProgress },
            modifier = Modifier
                .width(300.dp)
                .height(20.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            color = Color.Red,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (progress < 1.0f) {
                progress += 0.1f
            }
        }) {
            Text(text = "increase")
        }
    }
}

@Preview
@Composable
fun AnimatedVisibilityDemo() {
    val show = remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                show.value = !show.value
            },
        shape = RoundedCornerShape(6.dp),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = stringResource(R.string.text_content),
                style = TextStyle(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(visible = show.value) {
                Image(
                    painter = painterResource(id = R.mipmap.car),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertDialogDemo() {
    val dialogState: MutableState<Boolean> = remember { mutableStateOf(false) }
    Button(
        onClick = {
            dialogState.value = true
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = "Open dialog")
    }
    if (dialogState.value) {
        ShowAlertDialog({
            dialogState.value = false
        }, {
            dialogState.value = false
        })
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalPagerDemo2() {
    val pagerState = rememberPagerState(initialPage = 0) {
        10
    }
    val images = listOf(
        R.mipmap.zoom,
        R.mipmap.link_co,
        R.mipmap.honda,
        R.mipmap.bmw,
        R.mipmap.maserati
    )
    HorizontalPager(state = pagerState) { page ->
        Image(
            painter = painterResource(id = images[page % images.size]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    }
}

data class TabItem(val name: String, val resId: Int)

@Preview
@Composable
fun HorizontalPagerWithScrollableRow() {
    val datas = listOf(
        TabItem("马自达", R.mipmap.zoom),
        TabItem("领克", R.mipmap.link_co),
        TabItem("本田", R.mipmap.honda),
        TabItem("宝马", R.mipmap.bmw),
        TabItem("玛莎拉蒂", R.mipmap.maserati)
    )
    val pagerState = rememberPagerState(initialPage = 0) {
        datas.size
    }
    val coroutineScope = rememberCoroutineScope()
    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 5.dp,
            contentColor = Color.White,
            containerColor = Green700
        ) {
            datas.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .wrapContentWidth()
                ) {
                    Text(
                        item.name,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontStyle = FontStyle.Normal,
                            textAlign = TextAlign.Left
                        ),
                    )
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            Image(
                painter = painterResource(id = datas[page % datas.size].resId),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Preview
@Preview(showBackground = true)
@Composable
fun SliderDemo() {
    var slideValue by remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = slideValue.toString())
        Slider(
            value = slideValue,
            onValueChange = { slideValue = it },
            valueRange = 0f..5f,
            steps = 4   // 4步阶段，共 5 段
        )
    }
}


@Preview
@Composable
fun DropdownMenuDemo() {
    val expand = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    Box {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    expand.value = true
                },
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Dropdown Menu")
                }
            }
        }
        DropdownMenu(
            expanded = expand.value,
            onDismissRequest = { expand.value = false },
            modifier = Modifier
                .width(100.dp)
                .wrapContentHeight()
//                .shadow(
//                    elevation = 1.dp,
//                    clip = false
//                )
            ,
            offset = DpOffset(200.dp, 0.dp)
        ) {
            DropdownMenuItem(text = {
                Row {
                    Icon(Icons.Filled.Share, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("分享")
                }
            }, onClick = {})
            DropdownMenuItem(text = {
                Row {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("收藏")
                }
            }, onClick = {})
            DropdownMenuItem(text = {
                Row {
                    Icon(Icons.Filled.Done, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("导出")
                }
            }, onClick = {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ModalBottomSheetLayoutDemo() {
    val openBottomSheet = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

    Button(
        onClick = {
            openBottomSheet.value = !openBottomSheet.value
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(text = "Open BottomSheet")
    }
    if (openBottomSheet.value) {
        ModalBottomSheet(
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            onDismissRequest = { openBottomSheet.value = false },
            sheetState = bottomSheetState,
            containerColor = Color.White,
        ) {
            Text(text = "Why am I showing?", color = Color.Black)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CanvasDemo1() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            brush = Brush.linearGradient(colors = listOf(Color.Red, Color.Blue, Color.Green)),
            start = Offset.Zero,
            end = Offset(canvasWidth, canvasHeight),
            strokeWidth = 10f
        )
    }
}

@Composable
fun HorizontalStaggeredGrid(
    modifier: Modifier = Modifier,
    row: Int,
    horizontalSpace: Dp = 15.dp,
    verticalSpace: Dp = 12.dp,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->
        // 每行的宽度，初始都是 0
        val rowWidths = IntArray(row) { 0 }
        // 每行的高度,初始都是 0
        val rowHeights = IntArray(row) { 0 }
        // 遍历每个孩子，List<Measurable> 转换成 List<Placeable>
        val placeableList = measurables.mapIndexed { index, measurable ->
            // 测量每个孩子组件
            val placeable = measurable.measure(constraints)
            // 当前孩子所在行的索引
            val rowIndex = index % row
            // 当前行宽度累加
            rowWidths[rowIndex] += placeable.width + horizontalSpace.roundToPx()
            // 当前行高度取 当前高度和此孩子高度的最大值
            rowHeights[rowIndex] = max(rowHeights[rowIndex], placeable.height)
            // lambda 返回值
            placeable
        }
        // 此自定义组件的宽度
        val width = rowWidths.maxOrNull()
            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth))  // 限制在minWidth和maxWidth之间
            ?: constraints.minWidth  // 如果rowWidths 为null，则设置为minWidth
        // 此自定义组件的高度
        val height = rowHeights.sumOf { it + verticalSpace.roundToPx() }
            .minus(verticalSpace.roundToPx())  // 减去最后一行的 verticalSpace
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        // 记录每行顶部所在位置
        val rowY = IntArray(row) { 0 }
        // 第0行为 0， 从第一行开始，每行的顶部位置为 上一行顶部位置 + 上一行的高度
        for (rowIndex in 1 until row) {
            rowY[rowIndex] =
                rowY[rowIndex - 1] + rowHeights[rowIndex - 1] + verticalSpace.roundToPx()
        }
        layout(width, height) {
            // 记录每行当前横向位置x
            val rowX = IntArray(row) { 0 }
            placeableList.forEachIndexed { index, placeable ->
                // 所在行索引
                val rowIndex = index % row
                placeable.placeRelative(x = rowX[rowIndex], y = rowY[rowIndex])
                rowX[rowIndex] += placeable.width + horizontalSpace.roundToPx()
            }
        }
    }
}



