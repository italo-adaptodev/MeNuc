package adapto.com.menuc.Fragmentos.Forum.Firebase;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import adapto.com.menuc.R;

public class FirebaseForumPreviewViewHolder extends RecyclerView.ViewHolder {

    public TextView forum_preview_titulo, forum_preview_autor,
            forum_preview_data, forum_preview_qtdRespostas, forum_preview_cargo;
    public AppCompatImageButton btn_deletar_postagem;

    public FirebaseForumPreviewViewHolder(@NonNull View itemView) {
        super(itemView);
        forum_preview_titulo = itemView.findViewById(R.id.forum_preview_titulo);
        forum_preview_autor = itemView.findViewById(R.id.forum_preview_autor);
        forum_preview_data = itemView.findViewById(R.id.forum_preview_data);
        forum_preview_qtdRespostas = itemView.findViewById(R.id.forum_preview_qtdRespostas);
        forum_preview_cargo = itemView.findViewById(R.id.cargoAutor);
        btn_deletar_postagem = itemView.findViewById(R.id.btn_deletar_postagem_forum);

    }
}