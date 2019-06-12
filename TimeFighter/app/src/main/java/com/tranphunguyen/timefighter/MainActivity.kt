package com.tranphunguyen.timefighter

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val secondsInFuture = 10L
    private val countDownInterval = 1000L

    private var isStarted = false
    private var score = 0
    private var timeLeft = secondsInFuture

    private lateinit var countDownTimer: CountDownTimer

    companion object {
        private const val SCORE_KEY: String = "score"
        private const val TIME_LEFT_KEY = "timeLeft"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeft = savedInstanceState.getLong(TIME_LEFT_KEY)

            Log.e("TesttimeOUt1","$timeLeft + $score")

            restoreGame()

        } else {

            resetGame()

        }

        tap.setOnClickListener { view ->

            val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimation)

            if (!isStarted) {
                startGame()
            } else {
                incrementScore()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.about) {
            showInfo()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(SCORE_KEY, score)
        outState?.putLong(TIME_LEFT_KEY, timeLeft)
        countDownTimer.cancel()
        Log.e("TesttimeOUt","$timeLeft + $score")

    }
//    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
//        super.onSaveInstanceState(outState, outPersistentState)
//
//
//    }

    private fun restoreGame() {
        scoreTextView.text = getString(R.string.your_score, score.toString())
        timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())

        createTimer()
        startGame()
    }

    private fun showInfo() {
        val dialogTitle = getString(R.string.about_title, BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.about_message)

        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
            .setMessage(dialogMessage)

        builder.create().show()

    }

    private fun showDialogEndGame() {
        val messageTitle = getString(R.string.your_score, score.toString())
        val strStart = getString(R.string.start)
        val builder = AlertDialog.Builder(this)
        builder.setMessage(messageTitle)
            .setPositiveButton(strStart) { _, _ ->
                resetGame()
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun startGame() {
        isStarted = true
        countDownTimer.start()
    }

    private fun incrementScore() {

        score++
        scoreTextView.text = getString(R.string.your_score, score.toString())

        val blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)
        scoreTextView.startAnimation(blinkAnimation)

    }

    private fun endGame() {
        tap.isClickable = false
        showDialogEndGame()

    }

    private fun resetGame() {
        tap.isClickable = true
        isStarted = false
        score = 0
        timeLeft = secondsInFuture
        resetText()

        createTimer()
    }

    private fun createTimer() {
        countDownTimer = object : CountDownTimer(timeLeft * 1000, countDownInterval) {

            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
            }

        }
    }

    private fun resetText() {
        scoreTextView.text = getString(R.string.your_score, score.toString())
        timeLeftTextView.text = getString(R.string.time_left, timeLeft.toString())
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
