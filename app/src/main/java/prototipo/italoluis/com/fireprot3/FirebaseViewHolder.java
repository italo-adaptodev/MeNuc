package prototipo.italoluis.com.fireprot3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_nomeIndicado, txt_emailIndicado, txt_padrinho;
    public CheckBox checkBox;



    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_nomeIndicado = itemView.findViewById(R.id.nome_auth);
        txt_emailIndicado = itemView.findViewById(R.id.email_auth);
        txt_padrinho = itemView.findViewById(R.id.padrinho_auth);
        checkBox = itemView.findViewById(R.id.checkBox);




    }




}
