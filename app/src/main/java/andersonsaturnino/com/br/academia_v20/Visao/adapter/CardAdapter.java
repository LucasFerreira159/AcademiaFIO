package andersonsaturnino.com.br.academia_v20.Visao.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import andersonsaturnino.com.br.academia_v20.Modelo.Semanas;
import andersonsaturnino.com.br.academia_v20.R;

/**
 * Created by Lucas Ferreira on 21/03/2016.
 * Classe responsalvel por gerenciar adapter do recyclerview
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {


    List<Semanas> semanas;

    public CardAdapter(){
        super();
        semanas = new ArrayList<Semanas>();

        Semanas semana = new Semanas();

        semana.setDescricao("Segunda-Feira");
        this.semanas.add(semana);

        semana = new Semanas();
        semana.setDescricao("Terça-Feira");
        this.semanas.add(semana);

        semana = new Semanas();
        semana.setDescricao("Quarta-Feira");
        this.semanas.add(semana);

        semana = new Semanas();
        semana.setDescricao("Quinta-Feira");
        this.semanas.add(semana);

        semana = new Semanas();
        semana.setDescricao("Sexta-Feira");
        this.semanas.add(semana);

        semana = new Semanas();
        semana.setDescricao("Sabádo");
        this.semanas.add(semana);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_cardview_semana, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {

        Semanas semana = semanas.get(position);
        holder.campoNomeSemana.setText(semana.getDescricao());

    }

    @Override
    public int getItemCount() {
        return semanas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView campoNomeSemana;

        public ViewHolder(View itemView) {
            super(itemView);
            campoNomeSemana = (TextView) itemView.findViewById(R.id.diaSemana);
        }
    }
}
