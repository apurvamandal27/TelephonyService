package com.example.apurva.telephonyservice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.PatternMatcher;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button call,dialer;
    TextView msg;
    TelephonyManager tm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call=findViewById(R.id.call);
        dialer=findViewById(R.id.dialer);
        msg=findViewById(R.id.msg);

        tm= (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        msg.setText(tm.getNetworkOperatorName());

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:8126114464"));

                //runtime permission
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(intent);
            }
        });

        dialer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8126114464"));
                startActivity(intent);
            }
        });

    }
}
