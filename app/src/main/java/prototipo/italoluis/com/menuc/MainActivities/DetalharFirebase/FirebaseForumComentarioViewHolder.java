package prototipo.italoluis.com.menuc.MainActivities.DetalharFirebase;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import prototipo.italoluis.com.menuc.R;

public class FirebaseForumComentarioViewHolder extends RecyclerView.ViewHolder {

    public TextView detalhar_resposta_nome, detalhar_resposta_texto;

    public FirebaseForumComentarioViewHolder(@NonNull View itemView) {

        super(itemView);
        detalhar_resposta_nome = itemView.findViewById(R.id.forum_resposta_autor_resposta);
        detalhar_resposta_texto = itemView.findViewById(R.id.forum_resposta_texto);
    }
}
