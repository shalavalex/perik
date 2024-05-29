package com.gunschu.meet

import android.util.Log
import com.gunschu.meet.JitsiMeetPlugin.Companion.PLUGIN_TAG
import io.flutter.plugin.common.EventChannel
import java.io.Serializable

/**
 * StreamHandler to listen to conference events and broadcast it back to Flutter
 */
class JitsiMeetEventStreamHandler private constructor(): EventChannel.StreamHandler, Serializable {
    companion object {
        val instance = JitsiMeetEventStreamHandler()
    }

    private var eventSink: EventChannel.EventSink? = null

    override fun onListen(arguments: Any?, eventSink: EventChannel.EventSink?) {
        Log.d(PLUGIN_TAG, "JitsiMeetEventStreamHandler.onListen")
        this.eventSink = eventSink
    }

    override fun onCancel(arguments: Any?) {
        Log.d(PLUGIN_TAG, "JitsiMeetEventStreamHandler.onCancel")
        eventSink = null
    }

    fun onConferenceWillJoin(data: MutableMap<String, Any>?) {
        Log.d(PLUGIN_TAG, "JitsiMeetEventStreamHandler.onConferenceWillJoin")
        data?.put("event", "onConferenceWillJoin")
        eventSink?.success(data)
    }

    fun onConferenceJoined(data: MutableMap<String, Any>?) {
        Log.d(PLUGIN_TAG, "JitsiMeetEventStreamHandler.onConferenceJoined")
        data?.put("event", "onConferenceJoined")
        eventSink?.success(data)
    }

    fun onConferenceTerminated(data: MutableMap<String, Any>?) {
        Log.d(PLUGIN_TAG, "JitsiMeetEventStreamHandler.onConferenceTerminated")
        data?.put("event", "onConferenceTerminated")
        eventSink?.success(data)
    }

}