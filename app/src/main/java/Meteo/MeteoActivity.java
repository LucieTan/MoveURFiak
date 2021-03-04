package Meteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moveurfiak.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeteoActivity extends AppCompatActivity {
    EditText et_ville;
    TextView tv_degre;
    String url="api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String apikey="6b1621f194da05fd51c8b57d1847c7c6";

    Button btn_connass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_meteo);
        et_ville=findViewById(R.id.et_ville);
        tv_degre=findViewById(R.id.tv_degre);


    }
    public void getweather(View v){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/").addConverterFactory(GsonConverterFactory.create()).build();

        weatherapi myapi = retrofit.create(weatherapi.class);
        Call<Example> examplecall = myapi.getweather(et_ville.getText().toString().trim(), apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code() == 404 ){
                    Toast.makeText(MeteoActivity.this, "Veuillez inserer une ville valide", Toast.LENGTH_LONG).show();
                }
                else if(!(response.isSuccessful())) {
                    Toast.makeText(MeteoActivity.this,response.code(), Toast.LENGTH_LONG).show();
                }
                Example mydata=response.body();
                Main main=mydata.getMain();
                Double temp=main.getTemp();
                Integer temperature=(int)(temp-273.15);
                tv_degre.setText(String.valueOf(temperature)+"°c");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MeteoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });



    }
}