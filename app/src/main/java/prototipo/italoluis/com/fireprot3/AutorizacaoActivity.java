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
import android.widget.CheckBox;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AutorizacaoActivity extends AppCompatActivity {


    private SparseBooleanArray itemStateArray= new SparseBooleanArray();
    private RecyclerView recyclerView;
    private ArrayList<FirebaseDataAuth> arrayList;
    private FirebaseRecyclerOptions<FirebaseDataAuth> options;
    private FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder> adapter;
    private DatabaseReference databaseReference;
    SharedPreferences pref;

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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Indicados");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<FirebaseDataAuth>().setQuery(databaseReference, FirebaseDataAuth.class).build();

        adapter = new FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseViewHolder holder, final int position, @NonNull FirebaseDataAuth model) {
                holder.txt_nomeIndicado.setText(model.getNomeIndicado());
                holder.txt_emailIndicado.setText(model.getEmailIndicado());
                holder.txt_padrinho.setText(model.getNomePadrinho());
                bind(holder.getAdapterPosition(), holder.checkBox);
                provEmail = model.getEmailIndicado();
                emailAuth = provEmail + " " + emailAuth;



                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemStateArray.get(holder.getAdapterPosition(), false)) {
                            holder.checkBox.setChecked(false);
                            itemStateArray.put(holder.getAdapterPosition(), false);
                            pref.edit().clear().apply();
                        } else {
                            holder.checkBox.setChecked(true);
                            itemStateArray.put(holder.getAdapterPosition(), true);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Lista_email", emailAuth);
                            editor.apply();

                            showText(emailAuth);
                        }


                    }
                });







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

    void bind(int position, CheckBox checkBox) {
        // use the sparse boolean array to check
        if (!itemStateArray.get(position, false)) {
            checkBox.setChecked(false);}
        else {
            checkBox.setChecked(true);
        }

    }



}
