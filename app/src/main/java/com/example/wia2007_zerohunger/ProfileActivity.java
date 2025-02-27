package com.example.wia2007_zerohunger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wia2007_zerohunger.Part3.MainActivityPart3;
import com.example.wia2007_zerohunger.UserDatabase.UserAccount;
import com.example.wia2007_zerohunger.UserDatabase.UserAccountViewModel;
import com.example.wia2007_zerohunger.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseAuth mAuth;
    private String currentUserEmail;
    private UserAccountViewModel userAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mAuth = FirebaseAuth.getInstance();
        currentUserEmail = mAuth.getCurrentUser().getEmail();

        userAccountViewModel = new ViewModelProvider(this).get(UserAccountViewModel.class);

        userAccountViewModel.getUserAccountByEmail(currentUserEmail).observe(this, new Observer<UserAccount>() {
            @Override
            public void onChanged(UserAccount userAccount) {
                String intro = "Welcome " + userAccount.getName() + "!";
                binding.textViewProfileUserName.setText(intro);
                binding.textViewProfileUserEmail.setText(userAccount.getEmail());
                binding.textViewProfileUserAmount.setText(String.valueOf(userAccount.getAmount()));
            }
        });

        binding.mainProfileToolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainMenu.class);
            startActivity(intent);

        });

        binding.buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonMyBookingProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivityPart3.class);
                intent.putExtra("checkFarmerCode", 1);
                startActivity(intent);
            }
        });

        binding.buttonMySubscriptionProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivityPart3.class);
                intent.putExtra("checkFarmerCode", 2);
                startActivity(intent);
            }
        });

        binding.buttonSignOutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}