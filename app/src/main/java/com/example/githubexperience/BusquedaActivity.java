package com.example.githubexperience;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusquedaActivity extends AppCompatActivity {

    ImageView imagen;
    RecyclerView recyclerView;
    TextView username,followers,followings;
    MyAdapter myAdapter;
    ProgressBar progressBar;
    List<Repositorios> repositorios;
    Bundle bundle = new Bundle();


    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        Bundle bundle = getIntent().getExtras();
        String usuario = bundle.getString("username");


        username = findViewById(R.id.textView);
        username.setText(bundle.getString("username"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        imagen = findViewById(R.id.imageView);

        followers = findViewById(R.id.followers);
        followings = findViewById(R.id.followings);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        context = getApplicationContext();

        getUsers(usuario);
        getAllRepos(usuario);
        progressBar.setVisibility(View.INVISIBLE);
    }



    public void getAllRepos(String name) {
        Call<List<Repositorios>> call = ApiClient.getUserService().getLanguage(name);
        Intent intent = new Intent(this,MainActivity.class);
        call.enqueue(new Callback<List<Repositorios>>() {
            @Override
            public void onResponse(Call<List<Repositorios>> call, Response<List<Repositorios>> response) {

                List<Repositorios> repos = response.body();
                myAdapter = new MyAdapter();
                myAdapter.setData(repos);
                recyclerView.setAdapter(myAdapter);

            }

            @Override
            public void onFailure(Call<List<Repositorios>> call, Throwable t) {

            }
        });


    }

    /*
    private void getAllRepos(String usuario) {
        Call<List<Repositorios>> call = ApiClient.getUserService().getLanguage(usuario);
        call.enqueue(new Callback<List<Repositorios>>() {
            @Override
            public void onResponse(Call<List<Repositorios>> call, Response<List<Repositorios>> response) {
                List<Repositorios> repositoriosList = response.body();
                myAdapter = new MyAdapter();
                myAdapter.setData(repositoriosList);
                recyclerView.setAdapter(myAdapter);


            }


            @Override
            public void onFailure(Call<List<Repositorios>> call, Throwable t) {

            }
        });

    }

     */

    private void getUsers(String usuario) {
        progressBar.setVisibility(View.VISIBLE);

        Call<Users> call = ApiClient.getUserService().getInfo(usuario);
        Intent intent = new Intent(this,MainActivity.class);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.code()==200){
                    Users users = response.body();
                    followers.setText(String.valueOf(users.getFollowers()));
                    followings.setText(String.valueOf(users.getFollowing()));
                    String URL = users.getAvatar();
                    Glide.with(context).load(URL).into(imagen);
                }
                else if (response.code() == 404){

                    AlertDialog alertDialog = new AlertDialog.Builder (BusquedaActivity.this).create();

                    alertDialog.setMessage("No se ha encontrado al usuario");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    SharedPreferences.Editor editor = getSharedPreferences("Credenciales", MODE_PRIVATE).edit();
                                    editor.clear().apply();
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }
}