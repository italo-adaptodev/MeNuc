package prototipo.italoluis.com.fireprot3.PackageLogin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import prototipo.italoluis.com.fireprot3.R;
import prototipo.italoluis.com.fireprot3.WebViewConfig;


public class CriarLogin extends AppCompatActivity {

    private EditText nome, senha, email;
    private String URL = "https://docs.google.com/forms/d/e/1FAIpQLSfPsLCEnxMQXaKZQrQuxGXO1uK3VO9lHgakIYJh3yJeQcSuSA/viewform";


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        nome = findViewById(R.id.nomeText);
        email = findViewById(R.id.emailText);
        senha = findViewById(R.id.senhaText);
        Button criarlogin = findViewById(R.id.button);
        TextView textologin = findViewById(R.id.textView4);
        Button logoutbtn = findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();


        btnLogout(logoutbtn);

        btncriarConta(criarlogin);

        loginConectado(textologin);

    }

    private void btnLogout(Button btnLogout) {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Toast.makeText(CriarLogin.this, "Logout", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void btncriarConta(Button btnCriarLogin) {
        btnCriarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userEmail = email.getText().toString();
                final String password = senha.getText().toString();
                final String userName = nome.getText().toString();

                saveUsernameEmail(userName, userEmail);

                if (!TextUtils.isEmpty(userName) || !TextUtils.isEmpty(userEmail) || !TextUtils.isEmpty(password)) {

                    CriarContaFirebase(userEmail, password, view);

                } else {
                    Toast.makeText(CriarLogin.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loginConectado(TextView txtLogin) {
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarLogin.this, LoginSalvo.class);
                startActivity(intent);

            }
        });
    }

    private void CriarContaFirebase(String userEmail, String password, final View view) {
        mAuth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CriarLogin.this, "Conta criada", Toast.LENGTH_LONG).show();
                            showAlertDialogButtonClicked(view);
                        } else {
                            Toast.makeText(CriarLogin.this, "Ocorreu um erro. Tente novamente" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(CriarLogin.this, WebViewConfig.class);
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


}