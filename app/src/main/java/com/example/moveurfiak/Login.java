package com.example.moveurfiak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn, forgotTextLink;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail =findViewById(R.id.Email);
        mPassword =findViewById(R.id.mdp);
        mLoginBtn = findViewById(R.id.login);
        mCreateBtn =findViewById(R.id.txt_creation);
        forgotTextLink = findViewById(R.id.oublie_mdp);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar1);

        //Bouton qui permet de valider les informations entrées par l'utilisateur
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Veuillez saisir un e-mail");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Veuillez saisir un mot de passe");
                    return;
                }

                if(password.length() < 8){
                    mPassword.setError("Password Must be >= 8 caractères");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //Connexion de l'utilisateur
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Vous êtes bien connecté", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Accueil.class));
                        }else{
                            Toast.makeText(Login.this, "Une erreur est survenue"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Lien cliquable lorsque nous n'avons pas de compte
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        //Lien qui permet de récupérer son compte lorsque nous oublions notre mdp
        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail= new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Réinitialiser le mot de passe ?");
                passwordResetDialog.setMessage("Veuillez rentrer votre adresse mail pour recevoir un mail pour réinitialiser votre mot de passe");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // récupération du mail pour envoyer le mail de rénitialisation
                        String mail =resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Le lien a été envoyé", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Attention ! le lien n'a pas été envoyé", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
}