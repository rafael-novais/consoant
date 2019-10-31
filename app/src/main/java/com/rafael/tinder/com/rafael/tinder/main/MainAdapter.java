package com.rafael.tinder.com.rafael.tinder.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafael.tinder.R;


import com.rafael.tinder.dao.AdicionadoDAO;
import com.rafael.tinder.listener.OnElementClickListener;
import com.rafael.tinder.model.Item;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemHolder> {

    Context context;
    List<Item> itens;
    LayoutInflater inflater;
    OnElementClickListener onElementClickListener;

    public MainAdapter(Context context, List<Item> itens){
        this.context = context;
        this.itens = itens;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnElementClickListener(OnElementClickListener onElementClickListener) {
        this.onElementClickListener = onElementClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adpater_main, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {

        Glide.with(context)
                .load(itens.get(position).getImage())
                .into(holder.img);

        holder.endereco.setText(itens.get(position).getAddress());

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AdicionadoDAO.matchs.add(itens.get(0));
                Toast.makeText(context, "CURTIDO!", Toast.LENGTH_SHORT).show();
                Log.i("Tamanho", String.valueOf(AdicionadoDAO.matchs.size()));
                //onElementClickListener.onElementClick();

            }
        });

    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView endereco;
        CardView card;
        FloatingActionButton fab;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.main_img);
            endereco = itemView.findViewById(R.id.main_endereco);
            card = itemView.findViewById(R.id.card_main);
            fab = itemView.findViewById(R.id.match_botao);

        }



    }

}
