package prototipo.italoluis.com.fireprot3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AutorizacaoActivity extends AppCompatActivity {


    private SparseBooleanArray itemStateArray= new SparseBooleanArray();
    private RecyclerView recyclerView;
    private ArrayList<FirebaseDataAuth> arrayList;
    private FirebaseRecyclerOptions<FirebaseDataAuth> options;
    private FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder> adapter;
    private DatabaseReference dbRefIndicados;
    SharedPreferences pref;
    private Button send_author;
    String emailAuth;
    String provEmail;


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
        setContentView(R.layout.activity_autorizacao);
        pref = getSharedPreferences("Autorizados", MODE_PRIVATE);
        recyclerView = findViewById(R.id.lista_autorizacao);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<FirebaseDataAuth>();
        dbRefIndicados = FirebaseDatabase.getInstance().getReference().child("Indicados");
        dbRefIndicados.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<FirebaseDataAuth>().setQuery(dbRefIndicados, FirebaseDataAuth.class).build();
        send_author = findViewById(R.id.send_author);




        adapter = new FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseViewHolder holder, final int position, @NonNull final FirebaseDataAuth model) {
                holder.txt_nomeIndicado.setText(model.getNomeIndicado());
                holder.txt_emailIndicado.setText(model.getEmailIndicado());
                holder.txt_padrinho.setText(model.getNomePadrinho());
                provEmail = model.getEmailIndicado();
                emailAuth = provEmail + " " + emailAuth;

               send_authors(holder);

               accept(holder);

               deny(holder, model);




            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new FirebaseViewHolder(LayoutInflater.from(AutorizacaoActivity.this).inflate(R.layout.modelo, viewGroup,false));

            }
        };

        recyclerView.setAdapter(adapter);





    }

    public void showText( String email){
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
    }

    void send_authors(final FirebaseViewHolder holder){
        send_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemStateArray.get(holder.getAdapterPosition(), false)) {
                    pref.edit().clear().apply();
                } else {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Lista_email", emailAuth);
                    editor.apply();
                    showText(pref.getString("Lista_email", ""));
                }


            }
        });
    }

    void accept(@NonNull final FirebaseViewHolder holder){
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.accept.setEnabled(false);
                holder.accept.setVisibility(View.INVISIBLE);
                holder.deny.setEnabled(false);
                holder.deny.setVisibility(View.INVISIBLE);
                holder.onHold.setVisibility(View.VISIBLE);

                Query update =  dbRefIndicados.orderByChild("nomeIndicado");
                update.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            dataSnapshot1.getRef().child("autor").setValue("true");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Log.e(TAG, "onCancelled", databaseError.toException());

                    }
                });



            }
        });


    }

    void deny(@NonNull final FirebaseViewHolder holder, @NonNull final FirebaseDataAuth model){
        holder.deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query exclude =  dbRefIndicados.orderByChild("nomeIndicado").equalTo(model.nomeIndicado);
                exclude.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            dataSnapshot1.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Log.e(TAG, "onCancelled", databaseError.toException());

                    }
                });
            }
        });
    }





}
