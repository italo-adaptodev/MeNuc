package adapto.com.menuc.MainActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import adapto.com.menuc.R;
import adapto.com.menuc.WebViewConfig;


public class CriarLoginActivity extends AppCompatActivity {

    private TextInputLayout nome, senha, email, sobrenome;
    private String URL = "https://docs.google.com/forms/d/e/1FAIpQLSfPsLCEnxMQXaKZQrQuxGXO1uK3VO9lHgakIYJh3yJeQcSuSA/viewform";
    private ImageView atom;
    private FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        nome = findViewById(R.id.nomeText);
        sobrenome = findViewById(R.id.sobrenomeText);
        email = findViewById(R.id.emailText);
        senha = findViewById(R.id.senhaText);
        Button criarlogin = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        btncriarConta(criarlogin);
    }

    private void btncriarConta(Button btnCriarLogin) {
        btnCriarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userEmail = email.getEditText().getText().toString();
                final String password = senha.getEditText().getText().toString();
                final String userName = nome.getEditText().getText().toString();
                final String userSobrenome = sobrenome.getEditText().getText().toString();
                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userEmail) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(userSobrenome)) {
                    saveUsernameEmail(userName + " " + userSobrenome, userEmail);
                    CriarContaFirebase(userEmail, password, view, userName + " " + userSobrenome);
                } else {
                    Snackbar.make(getView(), "Preencha todos os campos!!", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void CriarContaFirebase(String userEmail, String password, final View view, final String userName) {
        mAuth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();
                            user.updateProfile(profileUpdates);
                            Snackbar.make(getView(), "Conta criada com sucesso", Snackbar.LENGTH_SHORT)
                                    .show();
                            showAlertDialogButtonClicked(view);
                        } else {
                            Snackbar.make(getView(), "Ocorreu um erro. Tente novamente" + task.getException().getMessage(), Snackbar.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }

    public void showAlertDialogButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seja bem vindo!!");
        builder.setMessage("Você agora será direcionado para um questionário inicial. " +
                "Por favor, preencha o questionário, depois é só clicar para ser direcionado a tela incial");
        builder.setPositiveButton("Entendido!", new DialogInterface
                .OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(CriarLoginActivity.this, WebViewConfig.class);
                intent.putExtra("URL", URL);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void saveUsernameEmail(String user, String email_user) {
        SharedPreferences preferences = getSharedPreferences("prototipo.italoluis.com.fireprot3", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nome_usuario", user);
        editor.putString("email_usuario", email_user);
        editor.commit();

    }

    public View getView() {
        return findViewById(android.R.id.content);
    }
}