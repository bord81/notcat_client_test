package com.notcat

class NotCatClient private constructor() {
        
    companion object {
        init {
            System.loadLibrary("notcat_lib_ffi")
        }

        @JvmStatic
        fun Init(): Int {
            return nativeInit(1)
        }
    @JvmStatic
    fun Log(priority: Priority, msg: String): Unit = nativeLog(priority.level, msg)
    @JvmStatic
    fun Close(): Boolean = nativeClose() == 0
    }

    enum class Priority(val level: Int) {
        VERBOSE(0),
        DEBUG(1),
        INFO(2),
        WARN(3),
        ERROR(4)
    }
}

private external fun nativeInit(sinkType: Int): Int
private external fun nativeLog(priority: Int, msg: String): Unit
private external fun nativeClose(): Int
