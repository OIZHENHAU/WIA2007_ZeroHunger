package com.example.wia2007_zerohunger;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.wia2007_zerohunger.UserDatabase.UserAccount;
import com.example.wia2007_zerohunger.UserDatabase.UserAccountViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import java.io.File;
import java.time.LocalDate;
import java.util.Base64;


import java.io.IOException;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {

    EditText username, email, password;
    Button enter;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    //double amount;
    private UserAccountViewModel userAccountViewModel;
    private UserAccount currentUserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.editTextSignUpName);
        email = findViewById(R.id.editTextSignUpEmail);
        password = findViewById(R.id.editTextSignUpPassword);
        enter = findViewById(R.id.buttonEnter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                if (isValidPassword(userPassword)) {
                    userAccountViewModel = new ViewModelProvider(SignUp.this).get(UserAccountViewModel.class);

                    UserAccount newUserAccount = new UserAccount(userName, userEmail, userPassword, 2000);
                    currentUserAccount = newUserAccount;
                    userAccountViewModel.insert(newUserAccount);

                    signUpFirebase(userName, userEmail, userPassword);

                } else {
                    Toast.makeText(SignUp.this, "Password must be 8-12 characters long, contain at least one capital letter, and a special character",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signUpFirebase(String userName, String userEmail, String userPassword) {
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            try {
                                sendEmail(userEmail, "Welcome to Zero Hunger", "Please verify your account");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            Intent intent = new Intent(SignUp.this, MainMenu.class);
                            intent.putExtra("nickName", userName);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUp.this, "You're Account Was Created Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public boolean isValidPassword(String password) {
        // Regular expression to check password requirements
        String passwordPattern = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,12}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void sendEmail(String email, String title, String content)  throws IOException {
        String json = "{\"recipient\": \""+email+"\",\"subject\": \""+title+"\",\"message\": \""+content+"\"}";

        // OkHttp client
        OkHttpClient client = new OkHttpClient();

        // Request body with JSON data
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        // Build request
        Request request = new Request.Builder()
                .url("https://nodemaillin.netlify.app/.netlify/functions/sendEmail")
                .post(body)
                .build();

        // Make the asynchronous call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Email sent successfully! Please check your Email", Toast.LENGTH_SHORT).show();
                    System.out.println("Email sent successfully!");

                } else {
                    System.out.println("Failed to send email. Response code: " + response.code());
                }


            }
        });

    }


}