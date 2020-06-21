package prototipo.adapto.com.menuc.MainActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import prototipo.adapto.com.menuc.R;

public class FaleConoscoActivity extends AppCompatActivity {

    private TextInputLayout nomeEmail;
    private TextInputLayout assuntoEmail;
    private TextInputLayout mensagemEmail;
    private MaterialButton btn;
    private static final String EMAIL_MENUC = "medicinanuclearifba@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fale_conosco
        );

        nomeEmail = findViewById(R.id.nomeEmail);
        assuntoEmail = findViewById(R.id.assuntoEmail);
        mensagemEmail = findViewById(R.id.corpoEmail);
        btn = findViewById(R.id.sendEmailButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_MENUC});
                it.putExtra(Intent.EXTRA_SUBJECT,"MeNuc - FALE CONOSCO" + assuntoEmail.getEditText().getText().toString());
                it.putExtra(Intent.EXTRA_TEXT,mensagemEmail.getEditText().getText().toString() + "\n \n " + nomeEmail.getEditText().getText().toString());
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it,"Escolha seu aplicativo de email"));
                onStop();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent home = new Intent( FaleConoscoActivity.this, TelaInicialActivity.class);
        startActivity(home);
    }
}