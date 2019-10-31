package com.rafael.tinder.busca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafael.tinder.R;
import com.rafael.tinder.dao.AdicionadoDAO;
import com.rafael.tinder.model.Item;

import java.util.List;

public class BuscaAdapter extends RecyclerView.Adapter<BuscaAdapter.BuscaHolder> {

    Context context;
    List<Item> lista = AdicionadoDAO.cacheMemoria;
    LayoutInflater layoutInflater;

    public BuscaAdapter(Context context){
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public BuscaAdapter.BuscaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_busca, parent, false);
        return new BuscaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BuscaAdapter.BuscaHolder holder, final int position) {

        Glide.with(context)
                .load(lista.get(position).getImage())
                .into(holder.img);

        holder.endereco.setText(lista.get(position).getAddress());
        holder.nome.setText(lista.get(position).getOwner());
        holder.tipo.setText(lista.get(position).getBussinessType());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CURTIDO!", Toast.LENGTH_SHORT).show();
                AdicionadoDAO.matchs.add(lista.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public static class BuscaHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView endereco;
        TextView tipo;
        TextView nome;
        FloatingActionButton btn;

        public BuscaHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.busca_img);
            endereco = itemView.findViewById(R.id.busca_endereco);
            nome = itemView.findViewById(R.id.busca_nome);
            tipo = itemView.findViewById(R.id.busca_tipo);
            btn = itemView.findViewById(R.id.feed_btn);

        }
    }

}
