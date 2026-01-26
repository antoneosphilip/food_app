package com.example.foodproj.presentation.auth.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodproj.R;
import com.example.foodproj.presentation.auth.presenter.AuthPresenter;
import com.example.foodproj.presentation.auth.presenter.AuthPresenterImpl;
import com.example.foodproj.presentation.home.view.home.HomeLayout;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private AuthPresenter presenter;
    private ProgressBar progressBar;
    private Button btnGoogleLogin;
    private EditText etEmail ;
    private EditText etPassword ;
    private Button btnLogin;
    private TextView signUp;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        progressBar = findViewById(R.id.progressBar);
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin);
        btnLogin=findViewById(R.id.btnLoginSubmit);
        etPassword= findViewById(R.id.etPassword);
        etEmail=findViewById(R.id.etEmail);
        signUp=findViewById(R.id.tvSignUpRedirect);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_Id))
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this, gso);


        presenter = new AuthPresenterImpl( this,googleSignInClient,getApplicationContext());

        btnGoogleLogin.setOnClickListener(v -> presenter.onGoogleSignIn());
        btnLogin.setOnClickListener(v -> validateAndSignUp());
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }
    private void validateAndSignUp() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if ( email.isEmpty() || password.isEmpty() ) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        presenter.onEmailSignIn(email, password);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try {
                presenter.handleGoogleSignInResult(
                        com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(data)
                                .getResult(com.google.android.gms.common.api.ApiException.class)
                                .getIdToken()
                );
            } catch (ApiException e) {
                Toast.makeText(this, "Login Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btnGoogleLogin.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        btnGoogleLogin.setEnabled(true);
    }

    @Override
    public void onLoginSuccess(FirebaseUser user) {
        Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, HomeLayout.class);
        user.getIdToken(true)
                .addOnSuccessListener(result -> {
                    String token = result.getToken();
                    presenter.saveToken(token);
                });
        startActivity(intent);

    }

    @Override
    public void onLoginError(String error) {
        Toast.makeText(this, "Login Error: " + error, Toast.LENGTH_SHORT).show();
    }



}
