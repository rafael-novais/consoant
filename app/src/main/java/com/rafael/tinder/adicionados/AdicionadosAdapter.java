package com.rafael.tinder.adicionados;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rafael.tinder.R;
import com.rafael.tinder.dao.AdicionadoDAO;
import com.rafael.tinder.model.Item;

import java.util.List;

public class AdicionadosAdapter extends RecyclerView.Adapter<AdicionadosAdapter.AdicionadosHolder> {

    Context context;
    List<Item> adicionados = AdicionadoDAO.matchs;
    LayoutInflater layoutInflater;

    public AdicionadosAdapter(Context context, List<Item> adicionados){
        this.context = context;
        //this.adicionados = adicionados;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdicionadosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_adicionados, parent, false);
        return new AdicionadosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdicionadosHolder holder, int position) {

        Glide.with(context)
                .load(adicionados.get(position).getImage())
                .into(holder.img);

        holder.endereco.setText(adicionados.get(position).getAddress());
        holder.nome.setText(adicionados.get(position).getOwner());



    }

    @Override
    public int getItemCount() {
        return adicionados.size();
    }

    public void remove(int posicaoItem) {
        adicionados.remove(posicaoItem);
        notifyDataSetChanged();
    }

    public static class AdicionadosHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView nome;
        TextView endereco;

        public AdicionadosHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.adicionados_img);
            nome = itemView.findViewById(R.id.adicionados_nome);
            endereco = itemView.findViewById(R.id.adicionados_endereco);

        }
    }

}
