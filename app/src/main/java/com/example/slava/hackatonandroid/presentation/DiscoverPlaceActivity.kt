package com.example.slava.hackatonandroid.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.slava.hackatonandroid.R
import com.example.slava.hackatonandroid.domain.utils.TextAdder
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_dicover_place.*

class DiscoverPlaceActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicover_place)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)

        parentLayout.addView(TextAdder.makeHatBlock("Discover", "The place", this), 0)
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
        setupMap()
    }

    private fun setupMap() {
        mMap.addMarker(MarkerOptions().position(LatLng(59.146361, 47.267305))
                .title("Lolkek").icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)))
    }
}