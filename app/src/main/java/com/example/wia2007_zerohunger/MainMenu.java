package com.example.wia2007_zerohunger;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wia2007_zerohunger.Part1.MainActivityP1;
import com.example.wia2007_zerohunger.Part1.view.MainWeatherActivity;
import com.example.wia2007_zerohunger.Part2.page16;
import com.example.wia2007_zerohunger.Part5.MainActivityPart5S1;
import com.example.wia2007_zerohunger.UserDatabase.UserAccount;
import com.example.wia2007_zerohunger.UserDatabase.UserAccountViewModel;
import com.google.firebase.auth.FirebaseAuth;


public class MainMenu extends AppCompatActivity {

    TextView nickName;
    Toolbar toolbar;
    Button agricultureSupportButton, povertyAssistanceButton;
    private String currentEmail;
    private UserAccountViewModel userAccountViewModel;
    private UserAccount currentUserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String nickNameString = intent.getStringExtra("nickName");
        String emailString = intent.getStringExtra("email");

        currentEmail = emailString;

        nickName = findViewById(R.id.textViewUserName);
        //nickName.setText("Welcome, " + nickNameString + "!");

        userAccountViewModel = new ViewModelProvider(MainMenu.this).get(UserAccountViewModel.class);

        /*userAccountViewModel.getAllUserAccount().observe(MainMenu.this, new Observer<List<UserAccount>>() {
                    @Override
                    public void onChanged(List<UserAccount> userAccounts) {
                        for (UserAccount userAccount : userAccounts) {
                            Log.d("onChanged: ", userAccount.getName());
                            Log.d("onChanged: ", userAccount.getEmail());
                        }
                    }
        });
         */

        userAccountViewModel.getUserAccountByEmail(currentEmail).observe(MainMenu.this, new Observer<UserAccount>() {

            @Override
            public void onChanged(UserAccount userAccount) {
                if (userAccount != null) {
                    Log.d("onChanged: ", userAccount.getName());
                    nickName.setText("Welcome, " + userAccount.getName() + "!");
                    currentUserAccount = userAccount;
                } else {
                    Log.d("onChanged: null", "null");
                }
            }
        });


        toolbar = (Toolbar) findViewById(R.id.mainMenuToolbar);
        setSupportActionBar(toolbar);

        agricultureSupportButton = findViewById(R.id.agriculturalSupportButton);
        povertyAssistanceButton = findViewById(R.id.povertyAssistanceButton);

        //Part 1.)
        agricultureSupportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MainActivityP1.class);
                intent.putExtra("nickName", nickNameString);
                intent.putExtra("email", currentEmail);
                startActivity(intent);
            }
        });

        // Part 2: Food Waste Management
        Button foodWasteManagementBtn = findViewById(R.id.foodWasteManagementBtn);

        foodWasteManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, page16.class);
                startActivity(intent);
            }
        });

        //Part 5.)
        povertyAssistanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MainActivityPart5S1.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.top_menu_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}