package com.rafael.tinder.adicionados;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rafael.tinder.R;
import com.rafael.tinder.dao.AdicionadoDAO;
import com.rafael.tinder.helper.AdicionadosTouchHelper;
import com.rafael.tinder.model.Item;
import com.rafael.tinder.service.ItemService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdicionadosActivity extends AppCompatActivity {

    RecyclerView rvAdicionados;
    AdicionadosAdapter adicionadosAdapter;
    List<Item> adicionados;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Minhas curtidas");
        setContentView(R.layout.activity_adicionados);

        rvAdicionados = findViewById(R.id.rv_adicionados);
        adicionadosAdapter = new AdicionadosAdapter(this, adicionados);
        adicionados = new ArrayList<>();

        rvAdicionados.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAdicionados.setAdapter(adicionadosAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new AdicionadosTouchHelper(this, adicionadosAdapter));
        itemTouchHelper.attachToRecyclerView(rvAdicionados);

        load();
    }

    public void load(){


       adicionados = AdicionadoDAO.matchs;

       Log.i("ADICIONADOS TAMANHO", String.valueOf(adicionados.size()));

       adicionadosAdapter.notifyDataSetChanged();

    }

}
