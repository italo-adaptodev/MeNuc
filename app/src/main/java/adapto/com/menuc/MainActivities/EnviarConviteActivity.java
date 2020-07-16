package adapto.com.menuc.MainActivities;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import adapto.com.menuc.Models.Indicados;
import adapto.com.menuc.R;

public class EnviarConviteActivity extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Indicados");
    Indicados indicados;
    TextInputLayout nome, email;
    Button button_send;
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

                if(!isEmailValid(email.getEditText().getText().toString().trim()) && !TextUtils.isEmpty(nome.getEditText().getText().toString())){
                    Toast.makeText(EnviarConviteActivity.this, "Email inválido!", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(nome.getEditText().getText().toString()) || TextUtils.isEmpty(email.getEditText().getText().toString())){
                    Toast.makeText(EnviarConviteActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }
                else {
                    indicados = new Indicados(nome.getEditText().getText().toString().trim(), email.getEditText().getText().toString().trim(), user.getEmail(), false );
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