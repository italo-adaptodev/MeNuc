package prototipo.italoluis.com.fireprot3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Invite extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Indicados&Autores");
    Indicados indicados;
    EditText nome, email;
    ImageButton button_send;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String receive, mini;
    TextView usuario;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        preferences = getSharedPreferences("prototipo.italoluis.com.fireprot3", Context.MODE_PRIVATE);
        receive = preferences.getString("nome_usuario", "");
        usuario = findViewById(R.id.nome_usuario);
        mini = "Seja bem vindo, " + receive;
        usuario.setText(mini);

        nome = findViewById(R.id.indicado1);
        email = findViewById(R.id.indicado2);
        button_send = findViewById(R.id.btn_send);
        indicados = new Indicados();
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                indicados.setNomeIndicado(nome.getText().toString().trim());
                indicados.setEmailIndicado(email.getText().toString().trim());
                indicados.setNomePadrinho(receive);
                indicados.setEmailPadrinho(user.getEmail());
                indicados.setAutor(false);

                //reference.push().setValue(indicados);
                reference.push().setValue(indicados);
                Toast.makeText(Invite.this, "ok!",Toast.LENGTH_LONG).show();
            }
        });








    }
}