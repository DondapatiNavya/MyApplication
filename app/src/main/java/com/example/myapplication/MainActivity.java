package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart(){
        super.onStart();
        submit();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    public String username(View view)
    {
        EditText name = (EditText)findViewById(R.id.username);
        String username = name.getText().toString();
        return username;
    }
    public String password(View view)
    {
        EditText pass = (EditText)findViewById(R.id.password);
        String password = pass.getText().toString();
        return password;
    }
    public void submit()
    {
        Button b = (Button) findViewById(R.id.submit);
        Log.i("log","test: ");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String emailId = username(view);
                String password = password(view);
                System.out.println("emailId: "+emailId);
                System.out.println("password: "+password);
                mAuth.signInWithEmailAndPassword(emailId, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this, "voila!!",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                    {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}