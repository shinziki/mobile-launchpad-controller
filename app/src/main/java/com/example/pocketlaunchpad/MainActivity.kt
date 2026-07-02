package com.example.pocketlaunchpad

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.pocketlaunchpad.ui.theme.PocketLaunchpadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(color = Color.Black) {
                    LaunchpadScreen()
                }
            }
        }
    }
}

@Composable
fun LaunchpadScreen() {
    Column {
        Row {
            for (col in 0..8) {
                if (col < 8) {
                    val cc = LaunchpadMapping.topRowCC[col]
                    LaunchpadButton(
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        idleColor = Color(0xFF3A3A3A),
                        pressedColor = Color(0xFFFF8A00),
                        shape = CircleShape,
                        label = "CC$cc",
                        onPress = { MidiLaunchpadService.instance?.ccOn(cc) },
                        onRelease = { MidiLaunchpadService.instance?.ccOff(cc) }
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        for (row in 0..7) {
            Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
                for (col in 0..8) {
                    if (col < 8) {
                        val note = LaunchpadMapping.gridNote(row, col)
                        LaunchpadButton(
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            idleColor = Color(0xFF232323),
                            pressedColor = Color(0xFF00C2FF),
                            shape = RoundedCornerShape(4.dp),
                            label = note.toString(),
                            onPress = { MidiLaunchpadService.instance?.padOn(note) },
                            onRelease = { MidiLaunchpadService.instance?.padOff(note) }
                        )
                    } else {
                        val note = LaunchpadMapping.sceneNote(row)
                        LaunchpadButton(
                            modifier = Modifier.weight(1f).fillMaxHeight(),
                            idleColor = Color(0xFF3A3A3A),
                            pressedColor = Color(0xFFFF3B3B),
                            shape = CircleShape,
                            label = note.toString(),
                            onPress = { MidiLaunchpadService.instance?.padOn(note) },
                            onRelease = { MidiLaunchpadService.instance?.padOff(note) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchpadButton(
    modifier: Modifier = Modifier,
    idleColor: Color,
    pressedColor: Color,
    shape: Shape,
    label: String,
    onPress: () -> Unit,
    onRelease: () -> Unit
) {

}