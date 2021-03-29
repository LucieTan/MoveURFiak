package application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moveurfiak.Login;
import com.example.moveurfiak.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Locale;

public class ProfilActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(); // language
        setContentView(R.layout.activity_profil);

        //Language
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        Button changeLang = findViewById(R.id.changeMyLang);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // L'AlertDialog qui affiche la liste des langues. Seulement une peut etre sélectionnée.
                showChangeLanguageDialog();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.profil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.reveil:
                        startActivity(new Intent(getApplicationContext(), reveil.AlarmActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profil: return true;
                    case R.id.meteo:
                        startActivity(new Intent(getApplicationContext(), Meteo.MeteoActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
// Language SUITE
    private void showChangeLanguageDialog() {
        // Tableau des langues à afficher dans la boîte de dialogue d'alerte
        final String[] listItems = {"French","English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ProfilActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    //Français
                    setLocale("fr");
                    recreate();
                }
                if (i == 1) {
                    //Anglais
                    setLocale("en");
                    recreate();
                }
                // Ferme la boîte de dialogue lorsque la langue est sélectionnée
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // Enregistre les données dans les préférences partagées
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    // Charge la langue enregistrée dans les préférences partagées
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
    public void deconnexion(View view){
        FirebaseAuth.getInstance().signOut(); // Déconnexion de l'utilisateur
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
