package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wia2007_zerohunger.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivityPart3 extends AppCompatActivity {

    Button connectionButtonPart3, reservationButtonPart3, subscriptionButtonPart3;
    Button prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        connectionButtonPart3 = findViewById(R.id.connectionButtonPart3);
        reservationButtonPart3 = findViewById(R.id.reservationButtonPart3);
        subscriptionButtonPart3 = findViewById(R.id.subscriptionButtonPart3);

        prevButton = connectionButtonPart3;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ConnectionFragmentPart3 connectionFragmentPart3 = new ConnectionFragmentPart3();
        fragmentTransaction.replace(R.id.viewPageMainPart3, connectionFragmentPart3);
        fragmentTransaction.commit();

        if (getIntent() != null) {
            int checkFarmerCode = getIntent().getIntExtra("checkFarmerCode", 1);

            if (checkFarmerCode == 1) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();

                ReservationBookingFragmentPart3 reservationBookingFragmentPart3 = new ReservationBookingFragmentPart3();
                fragmentTransaction1.replace(R.id.viewPageMainPart3, reservationBookingFragmentPart3);
                fragmentTransaction1.commit();

            } else {
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();

                SubBookingFragmentPart3F3 subBookingFragmentPart3F3 = new SubBookingFragmentPart3F3();
                fragmentTransaction2.replace(R.id.viewPageMainPart3, subBookingFragmentPart3F3);
                fragmentTransaction2.commit();

            }
        }

        connectionButtonPart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ConnectionFragmentPart3 connectionFragmentPart3 = new ConnectionFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, connectionFragmentPart3);
                fragmentTransaction.commit();

                prevButton.setBackgroundColor(getResources().getColor(R.color.light_grey));
                prevButton.setTextColor(getResources().getColor(R.color.black));

                connectionButtonPart3.setBackgroundColor(getResources().getColor(R.color.purple));
                connectionButtonPart3.setTextColor(getResources().getColor(R.color.white));

                prevButton = connectionButtonPart3;

            }
        });

        reservationButtonPart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ReservationFragmentPart3 reservationFragmentPart3 = new ReservationFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, reservationFragmentPart3);
                fragmentTransaction.commit();

                prevButton.setBackgroundColor(getResources().getColor(R.color.light_grey));
                prevButton.setTextColor(getResources().getColor(R.color.black));

                reservationButtonPart3.setBackgroundColor(getResources().getColor(R.color.purple));
                reservationButtonPart3.setTextColor(getResources().getColor(R.color.white));

                prevButton = reservationButtonPart3;

            }
        });

        subscriptionButtonPart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                SubscriptionFragmentPart3 subscriptionFragmentPart3 = new SubscriptionFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, subscriptionFragmentPart3);
                fragmentTransaction.commit();

                prevButton.setBackgroundColor(getResources().getColor(R.color.light_grey));
                prevButton.setTextColor(getResources().getColor(R.color.black));

                subscriptionButtonPart3.setBackgroundColor(getResources().getColor(R.color.purple));
                subscriptionButtonPart3.setTextColor(getResources().getColor(R.color.white));

                prevButton = subscriptionButtonPart3;
            }
        });

    }
}