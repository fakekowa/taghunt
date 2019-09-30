package com.appzet.taghunt.activities

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.prey_in_game_activity.*


class PreyInGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.appzet.taghunt.R.layout.prey_in_game_activity)

        //Om man istället vill ha en textView som visar tiden, så kan man byta till text stiället för bara tid.
   //     val timer = object : CountDownTimer(20000, 1000) {
   //         override fun onTick(millisUntilFinished: Long) {
   //             mTextField.text = "Time remaining: " + millisUntilFinished / 1000
   //         }
//
   //         override fun onFinish() {
   //             mTextField.text = "Time's up!"
   //         }
   //     }.start()
        //Start chronometer countdown
        view_timer.isCountDown = true
        view_timer.base = SystemClock.elapsedRealtime() + 5000
        view_timer.onFinishTemporaryDetach()
        view_timer.start()
        //Stop chronometer at 00:00
        view_timer.setOnChronometerTickListener {
            if (view_timer.base < SystemClock.elapsedRealtime()){
                view_timer.stop()
            }
            else{}
        }
    }
}

