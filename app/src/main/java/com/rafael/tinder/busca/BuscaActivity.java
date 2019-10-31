package com.rafael.tinder.busca;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rafael.tinder.R;
import com.rafael.tinder.dao.AdicionadoDAO;
import com.rafael.tinder.model.Item;
import com.rafael.tinder.service.ItemService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscaActivity extends AppCompatActivity {

    BuscaAdapter buscaAdapter;
    RecyclerView rvBusca;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Feed");

        setContentView(R.layout.activity_busca);

        rvBusca = findViewById(R.id.rv_busca);
        buscaAdapter = new BuscaAdapter(this);

        rvBusca.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvBusca.setAdapter(buscaAdapter);

        load("buscarTodos");
    }

    public void load(final String filtro){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://iteris.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(ItemService.class)
                .getProperties()
                .enqueue(new Callback<List<Item>>() {

                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                        AdicionadoDAO.cacheMemoria.clear();
                        Log.i("++++++++++++", response.body().get(2).getBussinessType());


                        switch (filtro){
                            case "buscarTodos":
                                Log.i("++++++++++++buscatodos", response.body().get(2).getBussinessType());
                                AdicionadoDAO.cacheMemoria.addAll(response.body());
                                buscaAdapter.notifyDataSetChanged();
                                break;

                            case "buscarItensAVenda":
                                for(int i = 0; i < response.body().size(); i++){

                                    if(response.body().get(i).getBussinessType().equals("Venda")){
                                        AdicionadoDAO.cacheMemoria.add(response.body().get(i));
                                        buscaAdapter.notifyDataSetChanged();
                                    }
                                }
                                break;

                            case "buscarItensAluguel":
                                for(int i = 0; i < response.body().size(); i++){

                                    if(response.body().get(i).getBussinessType().equals("Aluguel")){
                                        AdicionadoDAO.cacheMemoria.add(response.body().get(i));
                                        buscaAdapter.notifyDataSetChanged();
                                    }
                                }
                                break;
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                        Log.e("Erro ao usar Retrofit", t.getMessage());
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.busca_menu, menu);
        return true;
    }

    public boolean load(MenuItem item){

        String s = (String) item.getTitle();

        switch (s){
            case "Todos os imoveis":
                load("buscarTodos");
                break;
            case "Aluguel":
                load("buscarItensAluguel");
                break;
            case "A venda":
                load("buscarItensAVenda");
                break;

        }

        return true;
    }

}
