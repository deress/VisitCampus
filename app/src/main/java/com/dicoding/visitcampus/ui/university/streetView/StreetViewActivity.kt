package com.dicoding.visitcampus.ui.university.streetView

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.visitcampus.R
import com.dicoding.visitcampus.databinding.ActivityStreetViewBinding
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.StreetViewPanoramaCamera
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation
import com.google.android.material.appbar.MaterialToolbar

class StreetViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStreetViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStreetViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val latitude = intent.getDoubleExtra(LATITUDE, 0.0)
        val longitude = intent.getDoubleExtra(LONGITUDE, 0.0)
        Log.d("StreetViewActivity", "$latitude, $longitude")

        val streetViewFragment =
            supportFragmentManager.findFragmentById(R.id.fr_streetView) as SupportStreetViewPanoramaFragment?

        streetViewFragment?.getStreetViewPanoramaAsync{panorama ->
            savedInstanceState ?: panorama.setPosition(LatLng(latitude, longitude))

            panorama.isPanningGesturesEnabled
            panorama.isZoomGesturesEnabled
            panorama.isUserNavigationEnabled
            panorama.animateTo(
                StreetViewPanoramaCamera.Builder().orientation(StreetViewPanoramaOrientation(20f,20f))
                    .zoom(panorama.panoramaCamera.zoom)
                    .build(),2000
            )
        }

    }

    companion object {
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
    }


}