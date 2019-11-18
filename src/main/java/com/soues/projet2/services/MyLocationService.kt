package com.soues.projet2.services

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.soues.projet2.bus
import com.soues.projet2.log

class MyLocationService : Service(), LocationListener {

    private lateinit var locationMgr: LocationManager

    override fun onCreate() {
        super.onCreate()
        log("Service créé")

        locationMgr = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            stopSelf()
            return
        }

        if (locationMgr.allProviders.contains(LocationManager.NETWORK_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0f, this)

        if (locationMgr.allProviders.contains(LocationManager.GPS_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0f, this)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("Service lancé")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        log("Service détruit")
        super.onDestroy()
        locationMgr.removeUpdates(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    /**
     * Callback Location Listener
     */

    override fun onLocationChanged(location: Location?) {
        Toast.makeText(this, "$location", Toast.LENGTH_SHORT).show()
        log("$location")

        //Poster dans le bus
        bus.post(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

}
