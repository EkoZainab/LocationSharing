package com.example.android.locationsharing;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FloatingActionButton fabFullscreen, fabGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setTitle("Settings");
        ActionBar actionBar = this.getSupportActionBar();
        // Set the action bar back button to look like an up button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        fabFullscreen = findViewById(R.id.fab_fullscreen);
        fabGPS = findViewById(R.id.fab_gps);
    }

    public void setFullscreen (View view) {
        boolean fullScreen = (getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
        if (!fullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            Objects.requireNonNull(getSupportActionBar()).hide();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fabFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_exit_black_24dp, this.getTheme()));
            } else {
                fabFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_exit_black_24dp));
            }
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            Objects.requireNonNull(getSupportActionBar()).show();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fabFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen, this.getTheme()));
            } else {
                fabFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            //Makes the back button effective
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_refresh:
                break;
            case R.id.action_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady (GoogleMap googleMap){
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.maps, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
}
