package prototipo.adapto.com.menuc;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_nomeIndicado, txt_emailIndicado, txt_padrinho, onHold;
    public ImageButton accept, deny;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_nomeIndicado = itemView.findViewById(R.id.nome_auth);
        txt_emailIndicado = itemView.findViewById(R.id.email_auth);
        txt_padrinho = itemView.findViewById(R.id.email_padrinho_auth);
        accept = itemView.findViewById(R.id.accept_btn);
        deny = itemView.findViewById(R.id.denied_btn);
        onHold = itemView.findViewById(R.id.onhold);

    }
}
