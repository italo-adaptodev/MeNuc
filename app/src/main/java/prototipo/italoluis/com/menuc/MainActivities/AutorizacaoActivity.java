package prototipo.italoluis.com.menuc.MainActivities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import prototipo.italoluis.com.menuc.FirebaseDataAuth;
import prototipo.italoluis.com.menuc.FirebaseViewHolder;
import prototipo.italoluis.com.menuc.R;
import prototipo.italoluis.com.menuc.WebViewConfig;

public class AutorizacaoActivity extends AppCompatActivity {

    private SparseBooleanArray itemStateArray= new SparseBooleanArray();
    private ArrayList<FirebaseDataAuth> arrayList;
    private FirebaseRecyclerOptions<FirebaseDataAuth> options;
    private FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder> adapter;
    private Query dbRefIndicados = FirebaseDatabase.getInstance().getReference().child("Indicados");
    private DatabaseReference dbRefAutores = FirebaseDatabase.getInstance().getReference().child("Autores");
    public SharedPreferences pref;
    private Button send_author;
    private String emailAuth, provEmail;
    private RecyclerView recyclerView;

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
        recyclerView = findViewById(R.id.lista_autorizacao1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        dbRefIndicados.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<FirebaseDataAuth>().
                setQuery(dbRefIndicados.orderByChild("nomeIndicado"), FirebaseDataAuth.class).build();
        send_author = findViewById(R.id.send_author);
        Object clipboardService = getSystemService(CLIPBOARD_SERVICE);
        final ClipboardManager clipboardManager = (ClipboardManager)clipboardService;

        createAdapter(clipboardManager);

        recyclerView.setAdapter(adapter);

    }

    private void createAdapter(final ClipboardManager clipboardManager) {
        adapter = new FirebaseRecyclerAdapter<FirebaseDataAuth, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseViewHolder holder, final int position, @NonNull final FirebaseDataAuth model) {
                holder.txt_nomeIndicado.setText(model.getNomeIndicado());
                holder.txt_emailIndicado.setText(model.getEmailIndicado());
                holder.txt_padrinho.setText(model.getEmailPadrinho());
                provEmail = model.getEmailIndicado();

                if(emailAuth == null){
                    emailAuth = " " + provEmail;
                }else {
                    emailAuth = provEmail + " " + emailAuth;
                }

               sendAuthors(holder, clipboardManager);

               accept(holder, model);

               deny(holder, model);

            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new FirebaseViewHolder(LayoutInflater.from(AutorizacaoActivity.this).inflate(R.layout.cardview_solicitacao, viewGroup,false));
            }
        };
    }

    public void showText( String email){
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
    }

    void sendAuthors(final FirebaseViewHolder holder, final  ClipboardManager clipboardManager){
        send_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AutorizacaoActivity.this);
                builder.setTitle("ATENÇÃO!");
                builder.setMessage("Ao clicar em prosseguir, você será redirecionado para enviar convite para as autorizações aceitas. Os emails " +
                        "das solicitações já foram copiados. Cole-os no local indicado na próxima tela. Deseja continuar?");
                // add a button
                builder.setPositiveButton("Sim",new DialogInterface
                        .OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which)
                    {
                        if (itemStateArray.get((holder.getAdapterPosition()-1) , false)) {
                            pref.edit().clear().apply();
                        } else {
                            if(holder.accept.getVisibility() == View.INVISIBLE) {
                                pref.edit().putString("Lista_email", emailAuth).apply();
                                showText(pref.getString("Lista_email", ""));
                            }
                        }

                        cleanIndicados();
                        setAutoresTrue();

                        ClipData clipData = ClipData.newPlainText("Source Text", pref.getString("Lista_email", ""));
                        clipboardManager.setPrimaryClip(clipData);

                        Intent intent_webview = new Intent(AutorizacaoActivity.this, WebViewConfig.class);
                        String url = "https://www.blogger.com/blogger.g?blogID=537701014572510680#basicsettings";
                        intent_webview.putExtra("url", url);
                        startActivity(intent_webview);
                    }

                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    void accept(@NonNull final FirebaseViewHolder holder, @NonNull final FirebaseDataAuth model){
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogAccept(v, holder, model);

            }
        });
    }

    void deny(@NonNull final FirebaseViewHolder holder, @NonNull final FirebaseDataAuth model){
        holder.deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogDeny(model, v, holder);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        removeIfAuthorFalse();

        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("", "");
        clipboardManager.setPrimaryClip(data);
    }

    public void showAlertDialogAccept(View view, final FirebaseViewHolder holder,@NonNull final FirebaseDataAuth model) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ATENÇÃO!");
        builder.setMessage("Você tem certeza que deseja aceitar esta solicitação?");
        // add a button
        builder.setPositiveButton("Prosseguir",new DialogInterface
                .OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which)
            {
                Query update =  dbRefIndicados.orderByChild("nomeIndicado").equalTo(model.nomeIndicado);
                update.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dS: dataSnapshot.getChildren()){
                            holder.accept.setEnabled(false);
                            holder.accept.setVisibility(View.INVISIBLE);
                            holder.onHold.setVisibility(View.VISIBLE);
                            dbRefAutores.push().setValue(dS.getValue());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });


            }
        });

        builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showAlertDialogDeny(@NonNull final FirebaseDataAuth model, View v, final FirebaseViewHolder holder) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ATENÇÃO!");
        builder.setMessage("Você tem certeza que deseja negar esta solicitação?");
        // add a button
        builder.setPositiveButton("Prosseguir",new DialogInterface
                .OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which)
            {
                final Query excludeFromIndicados =  dbRefIndicados.orderByChild("nomeIndicado").equalTo(model.nomeIndicado);
                excludeFromIndicados.addListenerForSingleValueEvent(new ValueEventListener() {
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

                final Query excludeCopyFromAutores = dbRefAutores.orderByChild("nomeIndicado").equalTo(model.nomeIndicado);
                excludeCopyFromAutores.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dS1: dataSnapshot.getChildren()){
                            dS1.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void removeIfAuthorFalse(){
        final Query exclude =  dbRefAutores.orderByChild("autor").equalTo(false);
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

    public void setAutoresTrue(){
        final Query setAutores =  dbRefAutores.orderByChild("autor").equalTo(false);
        setAutores.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    dataSnapshot1.getRef().child("autor").setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    } // ESTE MÉTODO SERÁ RESPONSÁVEL POR APAGAR TODS OS DADOS RESTANTES DO DB INDICADOS, TENDO EM VISTA DE QUE QUANDO O BOTÃO FOR CLICADO,
    //TODAS AS CHECAGENS E CONFIRMAÇÕES JA TERÃO SIDO FEITAS ;

    public void cleanIndicados(){
        final Query exclude =  dbRefIndicados.orderByChild("nomeIndicado");
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

}


