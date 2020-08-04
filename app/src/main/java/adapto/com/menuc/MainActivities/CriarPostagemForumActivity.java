package adapto.com.menuc.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import adapto.com.menuc.Fragmentos.Forum.Model.PostagemForum;
import adapto.com.menuc.R;

public class CriarPostagemForumActivity extends AppCompatActivity {

    Button cancelar, enviar;
    TextInputLayout titulo, conteudo;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference dbRefForum = database.getReference().child("Forum-Teste");
    DatabaseReference dbRefForum = database.getReference().child("Forum");
    public static final String DATE_FORMAT_1 = "dd MMM yyyy";

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
                criarPostagemForum(titulo.getEditText().getText().toString(), conteudo.getEditText().getText().toString());
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
    }

    private void criarPostagemForum(String strTitulo, String strConteudo) {
        if(TextUtils.isEmpty(strTitulo) || TextUtils.isEmpty(strConteudo)) {
            Snackbar.make(getView(), "Preencha todos os campos!!", Snackbar.LENGTH_SHORT)
                    .show();
            titulo.requestFocus();
        }else{
            PostagemForum postagemForum = new PostagemForum(user.getDisplayName(), strTitulo, strConteudo, 0, getCurrentDate());
            postagemForum.setKey(dbRefForum.push().getKey());
            dbRefForum.child(postagemForum.getKey()).setValue(postagemForum);
            Snackbar.make(getView(), "Postagem enviada!", Snackbar.LENGTH_SHORT)
                    .show();
            finish();
            onBackPressed();
        }


    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1);
        dateFormat.setTimeZone(TimeZone.getTimeZone("BRT"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }


    public View getView() {
        return findViewById(android.R.id.content);
    }
}
