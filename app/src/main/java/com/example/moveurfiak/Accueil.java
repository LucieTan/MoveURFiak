package com.example.moveurfiak;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }

    public void deconnexion(View view){
        FirebaseAuth.getInstance().signOut(); // Déconnexion de l'utilisateur
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    public void inscription(View view){
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();
    }

    public void connexion(View view){
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}

/*import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Accueil extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_profil,container,false);

    }
}*/
