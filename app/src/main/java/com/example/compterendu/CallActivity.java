package com.example.compterendu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CallActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        if (intent.hasExtra("number") && intent.hasExtra("name")) {
            String retrievedName = intent.getStringExtra("name");
            String retrievedNumber = intent.getStringExtra("number");
            TextView setName = findViewById(R.id.setName);
            TextView setNumber = findViewById(R.id.setNumber);
            Button callButton = findViewById(R.id.callButton);
            setName.setText(retrievedName);
            setNumber.setText(retrievedNumber);
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + retrievedNumber));
                    startActivity(callIntent);
                }

            });


        }
    }
}
