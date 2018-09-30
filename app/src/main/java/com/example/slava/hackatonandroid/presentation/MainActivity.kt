package com.example.slava.hackatonandroid.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.data.PreferencesHelper
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import khttp.get
import khttp.post
import org.jetbrains.anko.onComplete

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private val REQUEST_CODE_PERMISSION = 2
    var mPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private var mMap: GoogleMap? = null
    protected var locationManager: LocationManager? = null
    var position = LatLng(55.759768, 37.627259)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        try {
            // Request location updates
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available");
        }

        //startActivity(Intent(this, ProcessingPhotoActivity::class.java))

        //if user hasn't been authorized move him to authorization
        if (!(PreferencesHelper.getSharedPreferenceBoolean(applicationContext, PreferencesHelper.KEY_IS_LOGINED, false))) {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //else{
          //  startActivity(Intent(this, DiscoverPlaceActivity::class.java))
        //}

        main_activity_main_block.addView(
                TextAdder.makeHatBlock("Discover new", "Places", this)
        )

        val context = this

        val params = mapOf(
                "token" to PreferencesHelper.getSharedPreferenceString(this, PreferencesHelper.KEY_TOKEN , "extratoken"),
                "dist" to 100000,
                "long" to position.longitude,
                "lat" to position.latitude
        )


        doAsync {
            //val respone
            // /buildings
            // token
            // let long
            // dist

            val responseBuildings = get("https://dookyheroky.herokuapp.com/api/buildings/" , data = params)

            onComplete {
                for (building in 0 until responseBuildings.jsonObject.getJSONArray("result").length()){
                    val block = TextAdder.makeItemPlaces(context ,responseBuildings.jsonObject.getJSONArray("result").getJSONObject(building).getString("name"),
                            responseBuildings.jsonObject.getJSONArray("result").getJSONObject(building).getString("info"))
                    block.setOnClickListener {
                        val inten = Intent(context, DiscoverPlaceActivity::class.java)
                        inten.putExtra("lat" , position.latitude)
                        inten.putExtra("long" , position.longitude)
                        inten.putExtra("name" , responseBuildings.jsonObject.getJSONArray("result").getJSONObject(building).getString("name"))
                        inten.putExtra("lat1" , responseBuildings.jsonObject.getJSONArray("result").getJSONObject(building).getString("lat"))
                        inten.putExtra("long1" , responseBuildings.jsonObject.getJSONArray("result").getJSONObject(building).getString("long"))
                        startActivity(inten)
                    }
                   // Log.e("keek" , responseBuildings.jsonObject.getJSONArray("result").getJSONObject(building).getString("lat"))
                    main_activity_main_block.addView(block)
                }
            }

        }

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(mPermission, Manifest.permission.READ_PHONE_STATE),
                        REQUEST_CODE_PERMISSION)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
             position = LatLng(location.latitude, location.longitude)
            Log.e("kke", position.toString())
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 20f))
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        setupMap()

    }

    private fun setupMap() {

        val cameraPosition = CameraPosition.Builder()
                .target(position)
                .zoom(10f)
                .build()

        mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(
                cameraPosition))


        mMap?.addMarker(MarkerOptions().position(LatLng(59.146361, 47.267305))
                .title("Lolkek").icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)))
    }
}
