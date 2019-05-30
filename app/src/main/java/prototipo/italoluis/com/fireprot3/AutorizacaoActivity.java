package prototipo.italoluis.com.fireprot3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.fireprot3.AdapterInvite.Adaptador;
import prototipo.italoluis.com.fireprot3.ModeloInvite.Candidatos;

public class AutorizacaoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    final int CONTADOR = 21;
    int total_item = 0, ultimo_item;
    Adaptador adaptador;
    boolean isLoading = false, isMaximo = false;

    String ultimo_card = "", ultimo_nome = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorizacao);

        recyclerView = findViewById(R.id.recyclerview);

        getLastNomeFromFirebase();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        adaptador = new Adaptador(this);

        getCandidatos();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                total_item = layoutManager.getItemCount();

            }
        });


    }

    private void getCandidatos() {

        if(!isMaximo){

            Query query;
            if(TextUtils.isEmpty(ultimo_card))
                query = FirebaseDatabase.getInstance().getReference()
                        .child("Indicados")
                        .limitToFirst(CONTADOR);
            else
                query = FirebaseDatabase.getInstance().getReference()
                        .child("Indicados")
                        .startAt(ultimo_nome)
                        .limitToFirst(CONTADOR);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChildren()){
                        List<Candidatos> newCandidato = new ArrayList<>();
                        for(DataSnapshot candidatoSnapShot:dataSnapshot.getChildren()){
                            newCandidato.add(candidatoSnapShot.getValue(Candidatos.class));
                        }

                        ultimo_card = newCandidato.get(newCandidato.size() -1).getNome_candidato();

                        if(!ultimo_card.equals(ultimo_nome))
                            newCandidato.remove(newCandidato.size()-1);
                        else
                            ultimo_card = "fim";

                        adaptador.addAll(newCandidato);
                        isLoading = false;

                    }

                    else
                    {
                        isLoading = false;
                        isMaximo = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    isLoading = false;

                }
            });
        }

    }

    private void getLastNomeFromFirebase() {

        Query getLastNome = FirebaseDatabase.getInstance().getReference()
                .child("Indicados")
                .limitToLast(1);

        getLastNome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ultimoNome : dataSnapshot.getChildren())
                    ultimo_nome = ultimoNome.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AutorizacaoActivity.this, "Não foi possível acessar o ultimo nome", Toast.LENGTH_SHORT).show();
                

            }
        });
    }
}
