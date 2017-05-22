package com.example.jberg.hw4;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Calc2 extends Activity {

    public static final int UNITS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc2);

        final EditText latp1 = (EditText) findViewById(R.id.LatP1);
        final EditText latp2 = (EditText) findViewById(R.id.LatP2);
        final EditText lonp1 = (EditText) findViewById(R.id.LonP1);
        final EditText lonp2 = (EditText) findViewById(R.id.LonP2);

        final TextView dist = (TextView) findViewById(R.id.dist);
        final TextView bear = (TextView) findViewById(R.id.bear);

        final Button calc = (Button) findViewById(R.id.calc);
        final Button clear = (Button) findViewById(R.id.clear);
        final Button settings = (Button) findViewById(R.id.settingsButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calc2.this, Settings.class);
                startActivityForResult(intent, UNITS);
            }
        });


        clear.setOnClickListener(v -> {
                latp1.setText("");
                latp2.setText("");
                lonp1.setText("");
                lonp2.setText("");
                dist.setText("Distance:");
                bear.setText("Bearing:");

        });

        calc.setOnClickListener(v -> {
            Location l1 = new Location("");
            l1.setLatitude(Double.parseDouble(latp1.getText().toString()));
            l1.setLongitude(Double.parseDouble(lonp1.getText().toString()));

            Location l2 = new Location("");
            l2.setLatitude(Double.parseDouble(latp2.getText().toString()));
            l2.setLongitude(Double.parseDouble(lonp2.getText().toString()));

            Float distance = l1.distanceTo(l2) * 0.001F;
            Float bearing = l1.bearingTo(l2);

            dist.setText("Distance: " + distance.toString());
            bear.setText("Bearing: " + bearing.toString());
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView dist = (TextView) findViewById(R.id.dist);
        final TextView bear = (TextView) findViewById(R.id.bear);
        final EditText latp1 = (EditText) findViewById(R.id.LatP1);
        final EditText latp2 = (EditText) findViewById(R.id.LatP2);
        final EditText lonp1 = (EditText) findViewById(R.id.LonP1);
        final EditText lonp2 = (EditText) findViewById(R.id.LonP2);

        if (resultCode == UNITS) {
            Button calc = (Button) findViewById(R.id.calc);
            calc.setOnClickListener(v -> {
                if (latp1.getText() == null) {
                    return;
                }

                Location l1 = new Location("");
                l1.setLatitude(Double.parseDouble(latp1.getText().toString()));
                l1.setLongitude(Double.parseDouble(lonp1.getText().toString()));

                Location l2 = new Location("");
                l2.setLatitude(Double.parseDouble(latp2.getText().toString()));
                l2.setLongitude(Double.parseDouble(lonp2.getText().toString()));

                Float distance;

                if (data.hasExtra("distanceUnits") && (data.getStringExtra("distanceUnits").equals("Kilometers"))) {
                    distance = l1.distanceTo(l2) * 0.001F;
                } else {
                    distance = l1.distanceTo(l2) * 0.001F * 0.621371192F;
                }
                distance = Float.valueOf(String.valueOf(Math.round(distance * 1000F) / 1000F));

                Float bearing;
                if (data.hasExtra("degreeUnits") && (data.getStringExtra("degreeUnits").equals("Degrees"))) {
                    bearing = l1.bearingTo(l2);
                } else {
                    bearing = l1.bearingTo(l2) * Float.valueOf(String.valueOf(Math.PI / 180.000));
                }
                bearing = Float.valueOf(String.valueOf(Math.round(bearing * 1000F) / 1000F));

                dist.setText("Distance: " + distance.toString());
                bear.setText("Bearing: " + bearing.toString());
            });
            calc.callOnClick();
        }
    }
}