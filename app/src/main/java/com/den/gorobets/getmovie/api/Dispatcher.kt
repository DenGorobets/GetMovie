package com.den.gorobets.getmovie.api

import okhttp3.Dispatcher

class Dispatcher {
    fun addDispatcher(): Dispatcher {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 5
        return dispatcher
    }
}