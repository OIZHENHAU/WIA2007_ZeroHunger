package com.example.wia2007_zerohunger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wia2007_zerohunger.UserDatabase.UserAccount;
import com.example.wia2007_zerohunger.UserDatabase.UserAccountViewModel;
import com.example.wia2007_zerohunger.databinding.ActivityEditProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity {

    private ActivityEditProfileBinding binding;
    private FirebaseAuth mAuth;
    private String currentUserEmail;
    private UserAccountViewModel userAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        currentUserEmail = mAuth.getCurrentUser().getEmail();
        Log.d("Edit Profile Email", currentUserEmail);

        userAccountViewModel = new ViewModelProvider(this).get(UserAccountViewModel.class);

        binding.mainEditProfileToolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        binding.buttonSaveProfileChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!binding.editTextNewPassword.getText().toString().isEmpty() &&
                                !binding.editTextConfirmPassword.getText().toString().isEmpty() &&
                                        !binding.editTextPreviousPassword.getText().toString().isEmpty()) {

                    String newPassword = binding.editTextNewPassword.getText().toString();
                    String confirmNewPassword = binding.editTextConfirmPassword.getText().toString();

                    if (isValidPassword(newPassword) && isValidPassword(confirmNewPassword)
                            && newPassword.equals(confirmNewPassword)) {


                        String previousPassword = binding.editTextPreviousPassword.getText().toString();
                        FirebaseUser currentUser = mAuth.getCurrentUser();

                        if (currentUser != null) {
                            AuthCredential credential = EmailAuthProvider.getCredential(currentUserEmail, previousPassword);

                            currentUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        currentUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(EditProfileActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();

                                                    if (!binding.editTextNewUserName.getText().toString().isEmpty()) {
                                                        String username = binding.editTextNewUserName.getText().toString();

                                                        userAccountViewModel.getUserAccountByEmail(currentUserEmail).observe(EditProfileActivity.this, new Observer<UserAccount>() {
                                                            @Override
                                                            public void onChanged(UserAccount userAccount) {
                                                                userAccount.setName(username);
                                                                userAccount.setPassword(newPassword);
                                                            }
                                                        });

                                                    } else {
                                                        userAccountViewModel.getUserAccountByEmail(currentUserEmail).observe(EditProfileActivity.this, new Observer<UserAccount>() {
                                                            @Override
                                                            public void onChanged(UserAccount userAccount) {
                                                                userAccount.setPassword(newPassword);
                                                            }
                                                        });

                                                    }

                                                } else {
                                                    Toast.makeText(EditProfileActivity.this, "Password update failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(EditProfileActivity.this, "Re-authentication failed", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(EditProfileActivity.this, "Password must be 8-12 characters long, contain a capital letter, and include @",
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(EditProfileActivity.this, "Please fill in the new passwords", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidPassword(String password) {
        // Regular expression to check password requirements
        String passwordPattern = "^(?=.*[A-Z])(?=.*[@]).{8,12}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}