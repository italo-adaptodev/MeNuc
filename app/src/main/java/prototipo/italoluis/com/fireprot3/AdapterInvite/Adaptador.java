package prototipo.italoluis.com.fireprot3.AdapterInvite;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.fireprot3.ModeloInvite.Candidatos;
import prototipo.italoluis.com.fireprot3.R;


public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder> {

    List<Candidatos> lista;
    Context context;

    public Adaptador(Context context) {
        this.lista = new ArrayList<>();
        this.context = context;
    }

    public void addAll (List<Candidatos> newCandidatos){

        int initSize = lista.size();
        lista.addAll( newCandidatos);
        notifyItemRangeChanged(initSize, newCandidatos.size());

    }

    public String getLastItemName(){
        return lista.get(lista.size()-1).getNome_candidato();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.convidado_padrinho_card,viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.txt_convidado_nome.setText(lista.get(i).getNome_candidato());
        myViewHolder.txt_convidado_email.setText(lista.get(i).getEmail_candidato());
        myViewHolder.txt_padrinho_nome.setText(lista.get(i).getNome_padrinho());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_convidado_nome, txt_convidado_email, txt_padrinho_nome;
        public MyViewHolder( View itemView) {
            super(itemView);

            txt_convidado_nome =  itemView.findViewById(R.id.convidado_nome);
            txt_convidado_email = itemView.findViewById(R.id.convidado_email);
            txt_padrinho_nome = itemView.findViewById(R.id.padrinho_nome);

        }
    }
}
