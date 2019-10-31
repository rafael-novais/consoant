package com.rafael.tinder.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rafael.tinder.adicionados.AdicionadosActivity;
import com.rafael.tinder.adicionados.AdicionadosAdapter;

public class AdicionadosTouchHelper extends ItemTouchHelper.Callback {

    private AdicionadosActivity adicionadosActivity;
    private AdicionadosAdapter adicionadosAdapter;

    public AdicionadosTouchHelper(AdicionadosActivity activity, AdicionadosAdapter adapter){
        this.adicionadosActivity = activity;
        this.adicionadosAdapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(0, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int posicaoItem = viewHolder.getAdapterPosition();
        adicionadosAdapter.remove(posicaoItem);
    }
}
