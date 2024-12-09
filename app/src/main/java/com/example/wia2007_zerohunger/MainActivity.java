package com.example.wia2007_zerohunger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wia2007_zerohunger.UserDatabase.UserAccount;
import com.example.wia2007_zerohunger.UserDatabase.UserAccountViewModel;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button signIn, signUp, forgotPassword, signInGoogle;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private UserAccountViewModel userAccountViewModel;
    private UserAccount currentUserAccount;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Register Using Google
        //registerActivityForGoogleSignIn();

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        signIn = findViewById(R.id.buttonSignIn);
        signUp = findViewById(R.id.buttonSignUp);
        forgotPassword = findViewById(R.id.buttonForgot);
        signInGoogle = findViewById(R.id.buttonLoginGoogleSignIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                userAccountViewModel = new ViewModelProvider(MainActivity.this).get(UserAccountViewModel.class);

                userAccountViewModel.getUserAccountByEmail(userEmail).observe(MainActivity.this, new Observer<UserAccount>() {

                    @Override
                    public void onChanged(UserAccount userAccount) {
                        if (userAccount != null) {
                            Log.d("onChanged: ", userAccount.getName());
                            currentUserAccount = userAccount;
                        } else {
                            Log.d("onChanged: null", "null");
                        }
                    }
                });

                signInFirebase(userEmail, userPassword);

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signInGoogle();
            }
        });

    }

    /*
    public void signInGoogle() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("482056799986-phbc0u9t92sp9nlfkj5r5hi3skk4vboh.apps.googleusercontent.com")
                .requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signIn();

    }

    public void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activityResultLauncher.launch(signInIntent);
    }

    public void registerActivityForGoogleSignIn() {

        activityResultLauncher
                = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if (resultCode == RESULT_OK && data != null) {
                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                            firebaseSignInWithGoogle(task);
                        }
                    }
                });
    }


    private void firebaseSignInWithGoogle(Task<GoogleSignInAccount> task) {

        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(this, "Successfully Sign In with Google", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainMenu.class);
            startActivity(intent);
            finish();
            firebaseGoogleAccount(account);

        } catch (ApiException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }

    private void firebaseGoogleAccount(GoogleSignInAccount account) {

        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(authCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            //FirebaseUser user = auth.getCurrentUser();
                            //user.getEmail();
                        } else {

                        }
                    }
                });

    }
    */

    public void signInFirebase(String userEmail, String userPassword) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(MainActivity.this, MainMenu.class);
                            intent.putExtra("nickName", currentUserAccount.getName());
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "The email or password you enter is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            Log.d("onStart: ", "currentUser: " + currentUser.getEmail());
            Intent intent = new Intent(MainActivity.this, MainMenu.class);
            startActivity(intent);
            finish();

        }
    }
}