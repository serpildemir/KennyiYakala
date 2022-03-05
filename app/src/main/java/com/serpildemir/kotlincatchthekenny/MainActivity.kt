package com.serpildemir.kotlincatchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    var score = 0
    var ImageArray =ArrayList<ImageView>()
    var handler = Handler()
    var runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       //ImageArray
        ImageArray.add(imageView1)
        ImageArray.add(imageView2)
        ImageArray.add(imageView3)
        ImageArray.add(imageView4)
        ImageArray.add(imageView5)
        ImageArray.add(imageView6)
        ImageArray.add(imageView7)
        ImageArray.add(imageView8)
        ImageArray.add(imageView9)

        hideImages()
        //CountDown Time
        object : CountDownTimer(15000,1000){

            override fun onFinish() {
                timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)
                for( image in ImageArray)
                    image.visibility = View.INVISIBLE

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Restart the game?")
                alert.setPositiveButton("Yes") {dialog, which ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                }
                alert.show()

            }

            override fun onTick(mills: Long) {
                timeText.text = "Time : "+mills/1000
            }

        }.start()



    }

    fun hideImages(){

        runnable = object : Runnable {
            override fun run() {
                for (image in ImageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(8)
                ImageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }

        }
     handler.post(runnable)
    }

    fun incraseScore(View: View){
        score = score + 1
        scoreText.text ="Score : $score"
    }
}

