package com.notcat

class NotCatClient private constructor(val handle: Long) {
        
    companion object {
        init {
            System.loadLibrary("notcat_lib_ffi")
        }

        @JvmStatic
        fun Init(): NotCatClient? {
            val h = nativeConnect("/dev/socket/notcat_socket")
            return if (h != 0L) NotCatClient(h) else null
        }
    }

    fun Log(msg: String): Boolean = nativeLog(handle, msg) == 0

    fun Close(): Boolean = nativeClose(handle) == 0
}

private external fun nativeConnect(adress: String): Long
private external fun nativeLog(handle: Long, msg: String): Int
private external fun nativeClose(handle: Long): Int
