package com.example.moveurfiak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MeteoActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    String url="api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    String apikey="6b1621f194da05fd51c8b57d1847c7c6";

  //  Button btn_connass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv);


    }
    public void getweather(View v){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/").addConverterFactory(GsonConverterFactory.create()).build();

        weatherapi myapi = retrofit.create(weatherapi.class);
        Call<Example> examplecall = myapi.getweather(et.getText().toString().trim(), apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code() ==404){
                    Toast.makeText(MeteoActivity.this, "Entre une vré vill wsh", Toast.LENGTH_LONG).show();
                }
                else if(!(response.isSuccessful())) {
                    Toast.makeText(MeteoActivity.this,response.code(), Toast.LENGTH_LONG).show();
                }
                Example mydata=response.body();
                Main main=mydata.getMain();
                Double temp=main.getTemp();
                Integer temperature=(int)(temp-273.15);
                tv.setText(String.valueOf(temperature)+"C");
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MeteoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });



    }
}