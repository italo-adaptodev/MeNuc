package prototipo.italoluis.com.menuc.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

import prototipo.italoluis.com.menuc.Fragmentos.Forum.ForumFragment;
import prototipo.italoluis.com.menuc.Fragmentos.Forum.Model.PostagemForum;
import prototipo.italoluis.com.menuc.R;

public class CriarPostagemForumActivity extends AppCompatActivity {

    Button cancelar, enviar;
    EditText titulo, conteudo;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dbRefForum = database.getReference().child("Forum");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_postagem_forum);
        cancelar = findViewById(R.id.add_forum_cancelar);
        enviar = findViewById(R.id.add_forum_enviar);
        titulo = findViewById(R.id.add_forum_titulo);
        conteudo = findViewById(R.id.add_forum_texto);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarPostagemForum(titulo.getText().toString(), conteudo.getText().toString());
            }

            private void criarPostagemForum(String strTitulo, String strConteudo) {
                if(TextUtils.isEmpty(strTitulo) && TextUtils.isEmpty(strConteudo)) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                    titulo.requestFocus();
                }else{
                    PostagemForum postagemForum = new PostagemForum(user.getDisplayName(), strTitulo, strConteudo, 0, DateFormat.getDateInstance().format(new Date()));
                    postagemForum.setKey(dbRefForum.push().getKey());
                    dbRefForum.child(postagemForum.getKey()).setValue(postagemForum);
                    Toast.makeText(getApplicationContext(), "Postagem Enviada!", Toast.LENGTH_LONG).show();
                    finish();
                    onBackPressed();
                }


            }
        });
    }


}
