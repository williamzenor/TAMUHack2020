package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    final static int LOC_PERM_CODE = 50;
    public double lon = 0;
    public double lat = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestManager rq = new RequestManager();

        Button button = findViewById(R.id.inputconfirm);
        final TextView txt = findViewById(R.id.textView2);
        final MainActivity a = this;
        checkLocPermission();
        getLocation();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceResult = "Error in connecting to service";
                //try{
                Log.d("Coords", lon+", "+lat);
                rq.get("http://10.228.118.215:5001/location", a, lon, lat);
                /*}catch(Exception e){
                    Log.e("Error: ", "Exception", e);
                }*/
                //txt.setText(serviceResult);
            }
        });
    }

    public void getLocation()
    {
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        final MainActivity a = this;
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //Log.d("Location", location.getLongitude()+", "+location.getLatitude());
                a.lon = location.getLongitude();
                a.lat = location.getLatitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

    public void checkLocPermission()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Yes:", "Yes location permissions");
        }
        else{
            Log.d("Err:","No location permissions");
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOC_PERM_CODE);
            }
        }
    }

}
