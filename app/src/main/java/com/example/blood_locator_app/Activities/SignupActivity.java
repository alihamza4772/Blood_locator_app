package com.example.blood_locator_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.blood_locator_app.JavaClasses.Users;
import com.example.blood_locator_app.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {
DatabaseReference databaseReference;
    Spinner spinner;
    String Number,Name,pas,bg,c;
    EditText name,phoneno,pass,confirmpass,city;
    CountryCodePicker countryCodePicker;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        spinner = findViewById(R.id.spinner);
        name = findViewById(R.id.edtnames);
        phoneno = findViewById(R.id.edtphoneno);
        pass = findViewById(R.id.edtpass);
        city = findViewById(R.id.edtcity);
        confirmpass = findViewById(R.id.edtconfirmpass);
        countryCodePicker = findViewById(R.id.ccp);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.type,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setFocusable(true);
        confirmpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(pass.getText().toString().equals(confirmpass.getText().toString()))
                {
                   return;
                }
                else
                    confirmpass.setError("Password didn't matched.");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    public void Signup_click(View view) {
       if(pass.getText().toString().equals(confirmpass.getText().toString()) && !name.getText().toString().isEmpty() && !pass.getText().toString().isEmpty() && !spinner.getSelectedItem().toString().isEmpty()) {
           Number = countryCodePicker.getSelectedCountryCodeWithPlus() + phoneno.getText().toString();
           Name = name.getText().toString();
           pas = pass.getText().toString();
           bg = spinner.getSelectedItem().toString();
           c=city.getText().toString();
           databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   if(dataSnapshot.child(Number).exists())
                   {
                       Toast.makeText(SignupActivity.this, "You have already have an account on this Phone Number.!", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
//                       final Users users = new Users(Name,bg,Number,pas,c);
//                       databaseReference.child(Number).setValue(users);
                       sendVerificationCode();
                       Toast.makeText(SignupActivity.this, "Sign up Successfully.!", Toast.LENGTH_LONG).show();
                       name.setText("");
                       phoneno.setText("");
                       pass.setText("");
                       confirmpass.setText("");
                       city.setText("");
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });
            }
       else
       {
           Toast.makeText(this, "Invalid Details.!", Toast.LENGTH_SHORT).show();
       }
       }

    public void loginactivity_click(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



void validation()
{
    if (name.getText().toString().isEmpty()) {
        name.setError("Phone number is required");
        name.requestFocus();
        return;
    }

    if (pass.getText().toString().isEmpty()) {
        pass.setError("Phone number is required");
        pass.requestFocus();
        return;
    }
    if (spinner.getSelectedItem().toString().isEmpty()) {
        Toast.makeText(this, "Choose Your Blood Group from the Blood Drop", Toast.LENGTH_LONG).show();
    }
}
    private void sendVerificationCode() {

        if (Number.isEmpty()) {
            phoneno.setError("Phone number is required");
            phoneno.requestFocus();
            return;
        }

        if (Number.length() < 10) {
            phoneno.setError("Please enter a valid phone");
            phoneno.requestFocus();
            return;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                Number,        // Phone number to verify
                60,                 // Timeout duration
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
            Toast.makeText(SignupActivity.this, "Code Sent.!", Toast.LENGTH_SHORT).show();
            next(s);
        }
    };
void next(String cod)
{
    Intent intent = new Intent(this,otpcodeActivity.class);
    intent.putExtra("no", Number);
    intent.putExtra("blood",bg);
    intent.putExtra("name",Name);
    intent.putExtra("pass",pas);
    intent.putExtra("code",cod);
    intent.putExtra("city",c);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
}
}
