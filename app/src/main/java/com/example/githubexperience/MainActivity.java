package com.example.githubexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    Bundle bundle = new Bundle();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);



        SharedPreferences sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("repositorio","error");

        if (user != "error") {
            //CargarSharedPreferences();
        }
    }

    public void Buscar (View view){
        String username = editText.getText().toString();
        Intent intent = new Intent(this, BusquedaActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("repositorio",username);
        editor.commit();

        bundle.putString("username",username);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public  void CargarSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("repositorio","error");

        Intent intent = new Intent(this, BusquedaActivity.class);

        bundle.putString("username", user);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}