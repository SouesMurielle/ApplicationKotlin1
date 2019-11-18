package com.soues.projet2

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.soues.projet2.services.MyLocationService
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_service_ex.*

class ServiceExActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_ex)

        bus.register(this)

    }

    override fun onDestroy() {
        super.onDestroy()

        bus.unregister(this)
    }


    /**
     * Callback Bus
     */

    @Subscribe
    fun toto(location: Location) {
        tv.text = location.toString()
    }


    /**
     * Button
     */

    fun onBtStart(view: View) = startService(Intent(this, MyLocationService::class.java))

    fun onBtStop(view: View) = stopService(Intent(this, MyLocationService::class.java))


}
