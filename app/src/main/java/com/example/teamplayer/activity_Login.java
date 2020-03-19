package com.example.teamplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class activity_Login extends AppCompatActivity {
    private TextView mStatusTextView;
    private FirebaseAuth mAuth;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
    private CheckBox checkbox;
    private EditText password;
    boolean isSucees = false;
    Intent intent;
    TextView failureMessage;
    private static final String TAG = "EmailPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        password= (EditText) findViewById(R.id.password);
        failureMessage = (TextView) findViewById(R.id.logInFailed);
        intent=new Intent(this,select_action.class);

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Button logInButton = findViewById(R.id.logInButton);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }



    public void logIn(View view) {
        EditText passwordData = (EditText) findViewById(R.id.password);
        EditText emailData = (EditText) findViewById(R.id.Email);
        String password = passwordData.getText().toString();
        String email = emailData.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            System.out.println("99999999999999999999999");
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            startActivity(intent);
                        } else {
                            String  message ="Wrong Email or password";
                            failureMessage.setText(message);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity_Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    private void updateUI(FirebaseUser user) {
    }
    public void resetPassword(View view){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        EditText emailData = (EditText) findViewById(R.id.Email);
        String emailAddress = emailData.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });

    }
    public void newUser(View view) {
        Intent intent=new Intent(this,registration.class);
        startActivity(intent);
    }

}
