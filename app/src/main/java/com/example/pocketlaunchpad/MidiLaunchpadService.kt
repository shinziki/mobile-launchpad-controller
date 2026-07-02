package com.example.pocketlaunchpad

class MidiLaunchpadService {
    companion object {
        @Volatile
        var instance: MidiLaunchpadService? = null
            private set
    }

    private fun sendRawMidi(bytes: ByteArray) {

    }

    fun padOn(note: Int) = sendRawMidi(byteArrayOf(0x90.toByte(), note.toByte(), 127))

    fun padOff(note: Int) = sendRawMidi(byteArrayOf(0x90.toByte(), note.toByte(), 0))

    fun ccOn(cc: Int) = sendRawMidi(byteArrayOf(0xB0.toByte(), cc.toByte(), 127))

    fun ccOff(cc: Int) = sendRawMidi(byteArrayOf(0xB0.toByte(), cc.toByte(), 0))
}