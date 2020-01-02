package prototipo.italoluis.com.menuc.MainActivities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import prototipo.italoluis.com.menuc.FirebaseDataAuth;
import prototipo.italoluis.com.menuc.FirebaseViewHolder;
import prototipo.italoluis.com.menuc.R;

public class ListaAutoresActivity extends AppCompatActivity {

    private SparseBooleanArray itemStateArray= new SparseBooleanArray();
    private RecyclerView recyclerView;
    private ArrayList<FirebaseDataAuth> arrayList;
    private FirebaseRecyclerOptions<FirebaseDataAuth> options;
    private FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder> adapter;
    private Query dbRefAutores = FirebaseDatabase.getInstance().getReference().child("Autores");
    private SharedPreferences pref;


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
        setContentView(R.layout.activity_lista_autores);
        pref = getSharedPreferences("Autorizados", MODE_PRIVATE);
        recyclerView = findViewById(R.id.lista_autores);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<FirebaseDataAuth>();
        dbRefAutores.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<FirebaseDataAuth>().setQuery(dbRefAutores.orderByChild("autor").equalTo(true), FirebaseDataAuth.class).build();


        adapter = new FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseViewHolder holder, final int position, @NonNull final FirebaseDataAuth model) {
                holder.txt_nomeIndicado.setText(model.getNomeIndicado());
                holder.txt_emailIndicado.setText(model.getEmailIndicado());
                holder.txt_padrinho.setText(model.getEmailPadrinho());
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new FirebaseViewHolder(LayoutInflater.from(ListaAutoresActivity.this).inflate(R.layout.cardview_listagem, viewGroup,false));

            }
        };

        recyclerView.setAdapter(adapter);

    }





}
