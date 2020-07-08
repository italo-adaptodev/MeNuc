package prototipo.adapto.com.menuc.MainActivities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import prototipo.adapto.com.menuc.R;


public class LoginSalvoActivity extends AppCompatActivity {

    private Button btnLoggin;
    private ProgressBar loginProgressB;
    private TextInputLayout userLogin, userSenha;
    private FirebaseAuth mAuth;
    private Intent tabs;
    private ImageView atom;
    private TextView txtCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        btnLoggin = findViewById(R.id.login_button);
        userLogin = findViewById(R.id.login_email_space);
        userSenha = findViewById(R.id.senha_space);
        mAuth = FirebaseAuth.getInstance();
        atom = findViewById(R.id.atom);
        atom.setVisibility(View.INVISIBLE);
        tabs = new Intent(this, TelaInicialActivity.class);
        txtCadastro = findViewById(R.id.txtCadastro);
        cadastro(txtCadastro);

        btnLogin();
    }

    private void btnLogin() {
        btnLoggin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                atom.setVisibility(View.VISIBLE);
                animate(atom);
                final String loginStr = userLogin.getEditText().getText().toString();
                final String senhaStr = userSenha.getEditText().getText().toString();

                if (loginStr.isEmpty() || senhaStr.isEmpty()){
                    Snackbar.make(getView(), "Preencha todos os campos!!", Snackbar.LENGTH_SHORT)
                            .show();
                    atom.setVisibility(View.INVISIBLE);
                }
                else{
                    singIn(loginStr,senhaStr);
                }


            }

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animate(View view) {
        ImageView v = (ImageView) view;
        Drawable d = v.getDrawable();
        if (d instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            avd.start();
        } else if (d instanceof AnimatedVectorDrawableCompat) {
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            avd.start();
        }
    }

    private void singIn(String loginStr, String senhaStr) {
        btnLoggin.setVisibility(View.INVISIBLE);
        mAuth.signInWithEmailAndPassword(loginStr,senhaStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        updateUI();
                    }
                    else{
                        btnLoggin.setVisibility(View.VISIBLE);
                        Snackbar.make(getView(), "Ocorreu um erro! Tente novamente", Snackbar.LENGTH_SHORT)
                                .show();
                    }
            }
        });

    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void updateUI() {

        startActivity(tabs);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            updateUI();

        }

    }

    private void cadastro(TextView txtLogin) {
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSalvoActivity.this, CriarLoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public View getView() {
        return findViewById(android.R.id.content);
    }
}