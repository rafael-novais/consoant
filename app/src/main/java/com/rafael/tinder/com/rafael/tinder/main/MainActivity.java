package com.rafael.tinder.com.rafael.tinder.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rafael.tinder.R;
import com.rafael.tinder.adicionados.AdicionadosActivity;
import com.rafael.tinder.busca.BuscaActivity;
import com.rafael.tinder.dao.AdicionadoDAO;
import com.rafael.tinder.helper.MainTouchHelmperCallBack;

import com.rafael.tinder.model.Item;
import com.rafael.tinder.service.ItemService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMain;
    List<Item> itens;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sugestões");

        rvMain = findViewById(R.id.rv_main);
        itens = new ArrayList<Item>();
        mainAdapter = new MainAdapter(this, itens);

        rvMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMain.setAdapter(mainAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MainTouchHelmperCallBack(this));
        itemTouchHelper.attachToRecyclerView(rvMain);

        loadItens("buscarItemAleatorio");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    public void loadItens(final String filtro){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iteris.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(ItemService .class)
                .getProperties()
                .enqueue(new Callback<List<Item>>() {

                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                        itens.clear();

                        switch (filtro){
                            case "buscarTodos":
                                itens.addAll(response.body());
                                mainAdapter.notifyDataSetChanged();
                                break;

                            case "buscarItensAVenda":
                                for(int i = 0; i < response.body().size(); i++){

                                    if(response.body().get(i).getBussinessType().equals("Venda")){
                                        itens.add(response.body().get(i));
                                        mainAdapter.notifyDataSetChanged();
                                    }
                                }
                                break;

                            case "buscarItensAluguel":
                                for(int i = 0; i < response.body().size(); i++){

                                    if(response.body().get(i).getBussinessType().equals("Aluguel")){
                                        itens.add(response.body().get(i));
                                    }
                                }
                                break;

                            case "buscarItemAleatorio":

                                itens.add(response.body().get((int) Math.round(Math.random()*8)));
                                mainAdapter.notifyDataSetChanged();
                                break;
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Log.e("Erro ao usar Retrofit", t.getMessage());
                    }
                });

    }

    public boolean loadAdicionados(MenuItem item){

        Intent intent = new Intent(this, AdicionadosActivity.class);
        startActivity(intent);

        return true;
    }
    public boolean loadBusca(MenuItem item){

        Intent intent = new Intent(this, BuscaActivity.class);
        startActivity(intent);

        return true;
    }




}
