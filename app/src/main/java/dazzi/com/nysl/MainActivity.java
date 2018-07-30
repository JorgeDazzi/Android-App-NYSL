package dazzi.com.nysl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Login-in";

    //sign in field
    private EditText username;
    private EditText password;
    private Button signIn;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);



        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = username.getText().toString();
                String psw = password.getText().toString();

                Log.i(TAG, "onClick: "+ email);
                if (email.isEmpty() || psw.isEmpty()){

                    Toast.makeText(getApplicationContext(), "user and psw cannot be empty!",
                            Toast.LENGTH_SHORT).show();

                }else{
                    login(email, psw);
                }

            }

        });

    }

        @Override
        protected void onStart () {
            super.onStart();

            FirebaseUser currentUser = mAuth.getCurrentUser();

        }

        private void login (String username, String password){

            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                startActivity(new Intent(MainActivity.this, HomeActivity.class));

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());

                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
        }
    }
