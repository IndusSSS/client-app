package com.smartsecurity.clientapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayerScreen(url: String) {
    val context = LocalContext.current
    val player = remember { SimpleExoPlayer.Builder(context).build() }
    DisposableEffect(Unit) {
        player.setMediaItem(MediaItem.fromUri(url))
        player.prepare()
        onDispose { player.release() }
    }
    AndroidView(factory = { PlayerView(context).apply { this.player = player } }, modifier = Modifier.fillMaxSize())
}
