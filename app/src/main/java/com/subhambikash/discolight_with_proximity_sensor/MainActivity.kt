package com.subhambikash.discolight_with_proximity_sensor

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(),SensorEventListener {

    private var sensorManager:SensorManager?=null
    private var proximitySensor:Sensor?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager
            proximitySensor= sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)





    }




    override fun onResume() {
        super.onResume()
        proximitySensor?.also { proximity ->
            sensorManager?.registerListener(this, proximity, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }

    override fun onPause() {
        super.onPause()
       sensorManager?.unregisterListener(this)
    }


    override fun onSensorChanged(p0: SensorEvent?) {
        val sensorValue= p0!!.values[0]
        Log.d("sensorValue", "onSensorChanged: $sensorValue")
        disco.setBackgroundColor(Color.rgb((0..255).random().toFloat(),sensorValue,(0..255).random().toFloat()))
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}