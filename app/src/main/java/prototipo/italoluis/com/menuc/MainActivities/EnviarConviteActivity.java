package prototipo.italoluis.com.menuc.MainActivities;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import prototipo.italoluis.com.menuc.Models.Indicados;
import prototipo.italoluis.com.menuc.R;

public class EnviarConviteActivity extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Indicados");
    Indicados indicados;
    EditText nome, email;
    ImageButton button_send;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_convite);

        TextView aviso = findViewById(R.id.convite_expli);
        aviso.setMovementMethod(new ScrollingMovementMethod());
        nome = findViewById(R.id.indicado1);
        email = findViewById(R.id.indicado2);
        button_send = findViewById(R.id.btn_send);




        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isEmailValid(email.getText().toString().trim()) && !TextUtils.isEmpty(nome.getText().toString())){
                    Toast.makeText(EnviarConviteActivity.this, "Email inválido!", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(nome.getText().toString()) || TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(EnviarConviteActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }
                else {
                    indicados = new Indicados(nome.getText().toString().trim(), email.getText().toString().trim(), user.getEmail(), false );
                    reference.push().setValue(indicados);
                    Toast.makeText(EnviarConviteActivity.this, "Solicitação Enviada!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    boolean isEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}