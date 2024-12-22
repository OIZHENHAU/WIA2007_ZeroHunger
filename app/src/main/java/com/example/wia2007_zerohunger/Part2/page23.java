package com.example.wia2007_zerohunger.Part2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

public class page23 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page23);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button page23_aboutUsBtnP2 = findViewById(R.id.page23_aboutUsBtnP2);
        Button page23_resourcesBtnP2 = findViewById(R.id.page23_resourcesBtnP2);
        Button page23_donationBtnP2 = findViewById(R.id.page23_donationBtnP2);
        Button page23_getHelpBtnP2 = findViewById(R.id.page23_getHelpBtnP2);
        Button page23_contactUsBtnP2 = findViewById(R.id.page23_contactUsBtnP2);

        page23_aboutUsBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page23.this, page16.class);
                startActivity(intent);
            }
        });

        page23_resourcesBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page23.this, page18.class);
                startActivity(intent);
            }
        });

        page23_donationBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page23.this, page19.class);
                startActivity(intent);
            }
        });

        /*page23_getHelpBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ();
                startActivity(intent);
            }
        });*/

        /*We use ACTION_DIAL instead of ACTION_CALL for user convenience and compliance with app store policies, as it does not require runtime permissions.*/
        /*This code enables the "Contact us" button to open the phone dialer with a specified phone number (+601116936613) pre-filled.
         It uses ACTION_DIAL for user convenience, as it doesn't require call permissions.
         If no app can handle the intent, it shows a Toast message to inform the user.*/
        page23_contactUsBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace with the phone number you want to call
                String phoneNumber = "tel:+601116936613";

                // Create an intent with the ACTION_DIAL action
                Intent callIntent = new Intent (Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phoneNumber));

                // Start the intent.
                if (callIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(callIntent);
                } else {
                    Toast.makeText(view.getContext(), "No app found to make calls", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}