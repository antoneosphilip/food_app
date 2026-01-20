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
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignUp;
    private Button btnGoogleSignUp;
    private ProgressBar progressBar;
    private AuthPresenter presenter;
    private TextView btnLogin;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUpSubmit);
        btnGoogleSignUp = findViewById(R.id.btnGoogleSignUp);
        progressBar = findViewById(R.id.progressBar);
        btnLogin=findViewById(R.id.tvLoginRedirect);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_Id))
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient =
                com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this, gso);

        presenter = new AuthPresenterImpl(this, googleSignInClient);

        btnSignUp.setOnClickListener(v -> validateAndSignUp());
        btnGoogleSignUp.setOnClickListener(v -> presenter.onGoogleSignIn());
    }

    private void validateAndSignUp() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        presenter.onEmailSignUp(email, password);
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
                        com.google.android.gms.auth.api.signin.GoogleSignIn
                                .getSignedInAccountFromIntent(data)
                                .getResult(com.google.android.gms.common.api.ApiException.class)
                                .getIdToken()
                );
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btnSignUp.setEnabled(false);
        btnGoogleSignUp.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        btnSignUp.setEnabled(true);
        btnGoogleSignUp.setEnabled(true);
    }

    @Override
    public void onSignUpSuccess(FirebaseUser user) {
        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSignUpError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
