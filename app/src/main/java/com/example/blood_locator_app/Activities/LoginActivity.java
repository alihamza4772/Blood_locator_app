package com.example.blood_locator_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blood_locator_app.R;
import com.example.blood_locator_app.JavaClasses.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
private EditText phoneno,pass;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
   phoneno = findViewById(R.id.edtloginphoneno);
   pass = findViewById(R.id.edtloginpass);
    //    databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
    }

    public void login_click(View view) {
        if(phoneno.getText().toString().isEmpty() || pass.getText().toString().isEmpty())
        {
            phoneno.setError("Enter Valid Phone Number.!");
            pass.setError("Enter Correct Password.!");
        } else
       signin(phoneno.getText().toString(),pass.getText().toString());
    }

    public void forgot_click(View view) {
        Toast.makeText(this, "Make another Account.!", Toast.LENGTH_SHORT).show();
    }
   public void signin(final String no,final String pas)
    {
        final String Number = "+92"+no;
        databaseReference = FirebaseDatabase.getInstance().getReference("users/"+Number);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String s = Number;
                        if (!phoneno.getText().toString().isEmpty()) {
                            Users login = new Users();
                               login = dataSnapshot.getValue(Users.class);
                               String string = login.getPass();
                            if (login.getPass().equals(pas)) {
                                SharedPreferences.Editor preferences= getSharedPreferences("users",MODE_PRIVATE).edit();
                                preferences.putString("login","done");
                                preferences.commit();

                                next();
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect Password.!", Toast.LENGTH_SHORT).show();
                                pass.setText("");
                            }
                        }
                    }

               @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
}
void next()
{
    Intent intent = new Intent(this,HomeActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
}
}
