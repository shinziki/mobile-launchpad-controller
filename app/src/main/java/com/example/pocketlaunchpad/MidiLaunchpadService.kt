package com.example.pocketlaunchpad

import android.media.midi.MidiDeviceService
import android.media.midi.MidiReceiver
import androidx.compose.ui.geometry.Offset
import java.io.IOException

class MidiLaunchpadService : MidiDeviceService() {
    companion object {
        @Volatile
        var instance: MidiLaunchpadService? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onDestroy() {
        instance = null
        super.onDestroy()
    }

    override fun onGetInputPortReceivers(): Array<MidiReceiver> {
        return arrayOf(object : MidiReceiver() {
            override fun onSend(msg: ByteArray, offset: Int, count: Int, timestamp: Long) {

            }
        })
    }

    private fun sendRawMidi(bytes: ByteArray) {
        val outputReceivers = getOutputPortReceivers()
        if (outputReceivers.isEmpty()) return
        try {
            outputReceivers[0].send(bytes, 0, bytes.size)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun padOn(note: Int) = sendRawMidi(byteArrayOf(0x90.toByte(), note.toByte(), 127))

    fun padOff(note: Int) = sendRawMidi(byteArrayOf(0x90.toByte(), note.toByte(), 0))

    fun ccOn(cc: Int) = sendRawMidi(byteArrayOf(0xB0.toByte(), cc.toByte(), 127))

    fun ccOff(cc: Int) = sendRawMidi(byteArrayOf(0xB0.toByte(), cc.toByte(), 0))
}