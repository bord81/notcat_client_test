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

    enum class Priority(val level: Int) {
        VERBOSE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4)
    }

    fun Log(priority: Priority, msg: String): Boolean = nativeLog(handle, priority.level, msg) == 0

    fun Close(): Boolean = nativeClose(handle) == 0
}

private external fun nativeConnect(adress: String): Long
private external fun nativeLog(handle: Long, priority: Int, msg: String): Int
private external fun nativeClose(handle: Long): Int
