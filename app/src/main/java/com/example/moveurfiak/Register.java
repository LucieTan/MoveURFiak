package com.example.moveurfiak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    EditText mEmail, mPseudo, mPassword;
    Button mRegisterBtn;
    TextView mConnexionBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail =findViewById(R.id.Email);
        mPseudo =findViewById(R.id.pseudo);
        mPassword =findViewById(R.id.mdp);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mConnexionBtn =findViewById(R.id.txt_creation);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //Déjà un compte ? Envoie sur l'activité connexion
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if(email.isEmpty()){
                    mEmail.setError("Veuillez saisir un e-mail");
                    return;
                }
                if(password.isEmpty()){
                    mPassword.setError("Veuillez saisir un mot de passe");
                    return;
                }

                if(password.length() < 8){
                    mPassword.setError("Password Must be >= 8 caractères");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //inscription de l'utilisateur dans firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "L'utilisateur a été crée", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), application.ProfilActivity.class));
                        }else{
                            Toast.makeText(Register.this, "Error !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        mConnexionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}