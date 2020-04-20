package com.zasa.comkot.timer

import android.os.CountDownTimer

class CommonTimer(
    countDownInterval: Long,
    millisInFuture: Long = Long.MAX_VALUE
) : CountDownTimer(millisInFuture, countDownInterval) {

    var isRunning = false
        private set

    private var tick: ((time: Long) -> Unit)? = null
    private var finish: (() -> Unit)? = null

    // Auto restart when timer is finished
    private var isLoop = false

    // TODO:  params
    private var beforeStart: (() -> Unit)? = null
    private var afterStart: (() -> Unit)? = null
    private var beforeStop: (() -> Unit)? = null
    private var afterStop: (() -> Unit)? = null

    // TODO: Init params
    fun tick(callback: (time: Long) -> Unit) = apply { tick = callback }

    fun finish(callback: () -> Unit) = apply { finish = callback }

    fun isLoop() = apply { isLoop = true }

    fun beforeStart(callback: () -> Unit) = apply { beforeStart = callback }
    fun afterStart(callback: () -> Unit) = apply { afterStart = callback }
    fun beforeStop(callback: () -> Unit) = apply { beforeStop = callback }
    fun afterStop(callback: () -> Unit) = apply { afterStop = callback }

    override fun onFinish() {
        finish?.invoke()

        if (isLoop) {
            stopTimer()
            startTimer()
        }
    }

    override fun onTick(time: Long) {
        tick?.invoke(time)
    }

    fun startTimer() {
        if (!isRunning) {
            isRunning = true

            beforeStart?.invoke()
            start()
            afterStart?.invoke()
        }
    }

    fun stopTimer() {
        if (isRunning) {
            isRunning = false

            beforeStop?.invoke()
            cancel()
            afterStop?.invoke()
        }
    }

}
