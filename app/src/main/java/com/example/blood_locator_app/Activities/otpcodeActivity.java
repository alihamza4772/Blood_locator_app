package com.example.blood_locator_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.broooapps.otpedittext2.OtpEditText;
import com.example.blood_locator_app.R;
import com.example.blood_locator_app.JavaClasses.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class otpcodeActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    OtpEditText editTextCode;
    String codeSent,phonenumber,name,pass,blood,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpcode);
        mAuth = FirebaseAuth.getInstance();
       phonenumber= getIntent().getStringExtra("no");
       codeSent = getIntent().getStringExtra("code");
       name = getIntent().getStringExtra("name");
        pass = getIntent().getStringExtra("pass");
        blood = getIntent().getStringExtra("blood");
        city = getIntent().getStringExtra("city");
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        editTextCode = findViewById(R.id.editTextCode);

    }

    public void back(View view) {
        Intent intent = new Intent(this,SignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    private void verifySignInCode() {

        String code = editTextCode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final Users users = new Users(name,blood,phonenumber,pass,city);
                                      databaseReference.child(phonenumber).setValue(users);
                                      Toast.makeText(getApplicationContext(), "User Registered.!", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor preferences= getSharedPreferences("users",MODE_PRIVATE).edit();
                            preferences.putString("login","done");
                            preferences.commit();
                                     next();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),
                                        "Incorrect Verification Code ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    public void verify_click(View view) {
        verifySignInCode();
    }
    void next()
    {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void resend(View view) {
        sendVerificationCode();
    }
    private void sendVerificationCode() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,        // Phone number to verify
                40,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(otpcodeActivity.this, "Code Sent.!", Toast.LENGTH_SHORT).show();
        }
    };
}
