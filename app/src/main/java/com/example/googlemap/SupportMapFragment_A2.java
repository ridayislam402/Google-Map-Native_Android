package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SupportMapFragment_A2 extends AppCompatActivity implements OnMapReadyCallback {
    boolean isPermissionGranted;
    GoogleMap googleMapV;
    EditText search_edit;
    ImageView search_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_map_fragment_a2);
        search_edit=findViewById(R.id.search_edit_id);
        search_btn=findViewById(R.id.search_btn_id);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reverse Search
                Geocoder geocoder=new Geocoder(SupportMapFragment_A2.this,Locale.getDefault());
                try {
                    List<Address>listAddress = geocoder.getFromLocation(23.755057881889872, 90.41541873354035,1);
                    if (listAddress.size()>0){
                        Toast.makeText(getApplicationContext(), ""+listAddress.get(0).getCountryName(), Toast.LENGTH_SHORT).show();
                                                                                        //country name or anything ask change .getCountryName
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                // Search
          /*      String location = search_edit.getText().toString();
                if (location==null){
                    Toast.makeText(getApplicationContext(), "Type any location name", Toast.LENGTH_SHORT).show();
                }else{
                    Geocoder geocoder=new Geocoder(SupportMapFragment_A2.this, Locale.getDefault());
                    try {
                        List<Address>listAddress=geocoder.getFromLocationName(location,1); // 1 me how many address need
                        if (listAddress.size()>0){
                            LatLng latLng=new LatLng(listAddress.get(0).getLatitude(),listAddress.get(0).getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.title("My Search Location");
                            markerOptions.position(latLng);
                            googleMapV.addMarker(markerOptions);
                            //moveCamera
                            //CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLng(latLng); without Zoom
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10); //with Zoom
                            //googleMapV.moveCamera(cameraUpdate); move camera
                            googleMapV.animateCamera(cameraUpdate); //animate camera

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        });


        checkPerission();
        if (isPermissionGranted) {
            if (checkGooglePlayServices()) {
                //call back main work
                SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
                getSupportFragmentManager().beginTransaction().add(R.id.contaner_id, supportMapFragment).commit();
                supportMapFragment.getMapAsync(this);
            } else {
                Toast.makeText(getApplicationContext(), "Google Play Service Not Avaiable", Toast.LENGTH_SHORT).show();
            }

        }
    }

    ///checkGooglePlayServices
    private boolean checkGooglePlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {

            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Toast.makeText(getApplicationContext(), "User Canceled Dialoge", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();

        }
        return false;
    }

    private void checkPerission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                isPermissionGranted = true;
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        //marker
        googleMapV = googleMap;
        LatLng latLng = new LatLng(23.755057881889872, 90.41541873354035);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("My Position");
        markerOptions.position(latLng);
        googleMapV.addMarker(markerOptions);
        //moveCamera
        //CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLng(latLng); without Zoom
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15); //with Zoom
        //googleMapV.moveCamera(cameraUpdate); move camera
        googleMapV.animateCamera(cameraUpdate); //animate camera


       /* googleMapV.getUiSettings().setZoomControlsEnabled(true);//zoom control
        googleMapV.getUiSettings().setCompassEnabled(true); // Compass
        googleMapV.getUiSettings().setZoomGesturesEnabled(true);
        googleMapV.getUiSettings().setScrollGesturesEnabled(true);
        googleMapV.getUiSettings().setRotateGesturesEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMapV.setMyLocationEnabled(true);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.nonMap){
            googleMapV.setMapType(GoogleMap.MAP_TYPE_NONE);
        }

        if (item.getItemId()==R.id.normalMap){
            googleMapV.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

        if (item.getItemId()==R.id.satelliteMap){
            googleMapV.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }

        if (item.getItemId()==R.id.mapHybrid){
            googleMapV.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }

        if (item.getItemId()==R.id.mapterrain){
            googleMapV.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        return super.onOptionsItemSelected(item);
    }
}