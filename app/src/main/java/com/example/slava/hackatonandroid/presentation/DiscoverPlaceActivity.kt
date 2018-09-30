package com.example.slava.hackatonandroid.presentation

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.model.Position
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_dicover_place.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class DiscoverPlaceActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null

    var position = LatLng(55.759768, 37.627259)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicover_place)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)

        parentLayout.addView(TextAdder.makeHatBlock("Discover", "The place", this), 0)

        takePhoto.setOnClickListener {
            startActivity(Intent(this, ProcessingPhotoActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe
    fun onPositionChanged(user_position: Position) {
        // Сюда прилетает текущая позиция
        position = LatLng(user_position.lat, user_position.long)
        Log.e("ee", position.toString())
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10f))
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