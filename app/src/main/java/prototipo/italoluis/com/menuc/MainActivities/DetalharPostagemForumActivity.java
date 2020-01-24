package prototipo.italoluis.com.menuc.MainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import prototipo.italoluis.com.menuc.FirebaseDataAuth;
import prototipo.italoluis.com.menuc.FirebaseViewHolder;
import prototipo.italoluis.com.menuc.Fragmentos.Forum.Model.PostagemForum;
import prototipo.italoluis.com.menuc.MainActivities.DetalharFirebase.FirebaseForumComentarioDataAuth;
import prototipo.italoluis.com.menuc.MainActivities.DetalharFirebase.FirebaseForumComentarioViewHolder;
import prototipo.italoluis.com.menuc.R;

public class DetalharPostagemForumActivity extends AppCompatActivity {

    private ArrayList<FirebaseForumComentarioDataAuth> arrayList;
    private FirebaseRecyclerOptions<FirebaseForumComentarioDataAuth> options;
    private FirebaseRecyclerAdapter<FirebaseForumComentarioDataAuth, FirebaseForumComentarioViewHolder> adapter;
    private DatabaseReference dbRespostas = FirebaseDatabase.getInstance().getReference().child("Respostas");
    private Query queryRespostas = dbRespostas;
    private TextView postagemDetalhadaAutor, postagemDetalhadaData, postagemDetalhadaTitulo, postagemDetalhadaTexto;
    private RecyclerView detalharRespostas;
    private DatabaseReference dadosPostagem = FirebaseDatabase.getInstance().getReference().child("Forum");
    private DatabaseReference dbAutor = FirebaseDatabase.getInstance().getReference().child("Autores");
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private EditText comentario;
    private ImageButton btnEnviarComentario;
    private int qtdComentarios;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_postagem_forum);
        postagemDetalhadaAutor = findViewById(R.id.detalhar_forum_autor);
        postagemDetalhadaData = findViewById(R.id.detalhar_forum_data);
        postagemDetalhadaTitulo = findViewById(R.id.detalhar_forum_titulo);
        detalharRespostas = findViewById(R.id.recyclerview_detalhar_respostas_forum);
        postagemDetalhadaTexto = findViewById(R.id.detalhar_forum_texto);
        comentario = findViewById(R.id.forum_detalhar_comentario);
        btnEnviarComentario = findViewById(R.id.forum_btn_enviar_comentario);
        Intent intent = getIntent();
        final String postagemKey = intent.getStringExtra("postagemKey");

        //region RECUPERANDO DADOS DA POSTAGEM ESCOLHIDA
        final Query getDadosPostagemForum = dadosPostagem.orderByChild("key").equalTo(postagemKey);
        getDadosPostagemForum.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dS1 : dataSnapshot.getChildren()) {
                    postagemDetalhadaAutor.setText(dS1.child("fAutor").getValue().toString());
                    postagemDetalhadaData.setText(dS1.child("fDataPostagem").getValue().toString());
                    postagemDetalhadaTitulo.setText(dS1.child("fTitulo").getValue().toString());
                    postagemDetalhadaTexto.setText(dS1.child("fCorpoDaPostagem").getValue().toString());
                    qtdComentarios = Integer.parseInt(dS1.child("fQtdComentarios").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetalharPostagemForumActivity.this, databaseError.getCode() + databaseError.getDetails() + databaseError.getMessage(), Toast.LENGTH_LONG + 5).show();
            }
        });
        //endregion

        //region PREENCHENDO RECYCLERVIEW COM RESPOSTAS
        detalharRespostas.setHasFixedSize(true);
        detalharRespostas.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        queryRespostas.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<FirebaseForumComentarioDataAuth>()
                .setQuery(queryRespostas.orderByChild("postagemKey").equalTo(postagemKey), FirebaseForumComentarioDataAuth.class).build();
        createAdapter();
        detalharRespostas.setAdapter(adapter);
        //endregion

        //region ENVIO DE COMENTARIO
        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(comentario.getText())) {
                    comentario.requestFocus();
                } else {
                    enviarComentario(new FirebaseForumComentarioDataAuth(user.getDisplayName(), comentario.getText().toString(), postagemKey));
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        //endregion


    }

    private void createAdapter() {
        adapter = new FirebaseRecyclerAdapter<FirebaseForumComentarioDataAuth, FirebaseForumComentarioViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseForumComentarioViewHolder holder, int i, @NonNull FirebaseForumComentarioDataAuth model) {
                holder.detalhar_resposta_nome.setText(model.getComentarioNomeUsuario());
                holder.detalhar_resposta_texto.setText(model.getComentarioTexto());
            }

            @NonNull
            @Override
            public FirebaseForumComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseForumComentarioViewHolder(LayoutInflater.from(DetalharPostagemForumActivity.this).inflate(R.layout.cardview_resposta_forum, parent, false));
            }
        };
    }

    private void enviarComentario(FirebaseForumComentarioDataAuth comentarioModel) {
        updateQtdComentario(comentarioModel.postagemKey);
        dbRespostas.push().setValue(comentarioModel);
        Toast.makeText(getApplicationContext(), "Comentario enviado!", Toast.LENGTH_LONG).show();
    }

    private void updateQtdComentario(String postagemKey){
        Query update =  dadosPostagem.orderByKey().equalTo(postagemKey);
        update.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dS: dataSnapshot.getChildren()){
                    dS.getRef().child("fQtdComentarios").setValue(qtdComentarios+1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }
}
