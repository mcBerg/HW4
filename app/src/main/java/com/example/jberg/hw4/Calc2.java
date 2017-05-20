package com.example.jberg.hw4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calc2 extends Activity {

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

        clear.setOnClickListener(v -> {
                latp1.setText("");
                latp2.setText("");
                lonp1.setText("");
                lonp2.setText("");
                dist.setText("Distance:");
                bear.setText("Bearing:");

        });
    }
/*
    Location loc1 = new Location("");
                loc1.setLatitude(Double.parseDouble(lat1));
                loc1.setLongitude(Double.parseDouble(long1));

    Location loc2 = new Location("");
                loc2.setLatitude(Double.parseDouble(lat2));
                loc2.setLongitude(Double.parseDouble(long2));

    Float distance = loc1.distanceTo(loc2);
    distance *= Float.parseFloat("0.001");

    Float bearing = loc1.bearingTo(loc2);

               distanceValue.setText(distance.toString());
                bearingValue.setText(bearing.toString());
  */
}