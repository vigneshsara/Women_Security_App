package com.example.sasikumar.alerti;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper mydb1;
    private LocationManager locationManager;
    private LocationListener locationListener;
    Button bt;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb1=new DatabaseHelper(this);
        bt=(Button)findViewById(R.id.buttonpanic);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        panic();

    }
    public void panic()
    {

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                msg=("EMEREGNCY \n http://www.google.com/maps/place/"+location.getLatitude()+","+location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent it=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(it);

            }
        };
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                },10);
                return;
            }
            else{
                conf();
            }
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= mydb1.mobileno();
                //Toast.makeText(getApplicationContext(),"viki",Toast.LENGTH_LONG).show();
                conf();
               while(res.moveToNext())
               {
                  SmsManager sms = SmsManager.getDefault();
                   sms.sendTextMessage(res.getString(0), null,msg, null, null);
                    Toast.makeText(MainActivity.this,"msg send",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void conf()
    {
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout activity_main = (RelativeLayout) findViewById(R.id.activity_main);
        switch (item.getItemId()) {
            case R.id.Menu_add:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent id=new Intent(MainActivity.this,Add_contacts.class);
                startActivity(id);
                return true;
            case R.id.Menu_display:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent it=new Intent(getApplicationContext(),ListViewActivity.class);
                it.putExtra("getview","view");
                startActivity(it);
                return true;
            case R.id.Menu_delete:
                if(item.isChecked())
                    item.setChecked(false);
                else
                     item.setChecked(true);
                Intent it1=new Intent(getApplicationContext(),Listviewdelete.class);
                startActivity(it1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
