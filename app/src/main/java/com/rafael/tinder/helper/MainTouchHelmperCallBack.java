package com.rafael.tinder.helper;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.rafael.tinder.com.rafael.tinder.main.MainActivity;
import com.rafael.tinder.com.rafael.tinder.main.MainAdapter;

public class MainTouchHelmperCallBack extends ItemTouchHelper.Callback {

    private MainActivity mainActivity;

    public MainTouchHelmperCallBack(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.LEFT;
        return makeMovementFlags(0, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mainActivity.loadItens("buscarItemAleatorio");
    }
}
