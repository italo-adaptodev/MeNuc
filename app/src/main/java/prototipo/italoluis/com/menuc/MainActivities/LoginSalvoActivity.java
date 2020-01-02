package prototipo.italoluis.com.menuc.MainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import prototipo.italoluis.com.menuc.R;


public class LoginSalvoActivity extends AppCompatActivity {

    private Button btnLoggin;
    private ProgressBar loginProgressB;
    private EditText userLogin, userSenha;
    private FirebaseAuth mAuth;
    private Intent tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        btnLoggin = findViewById(R.id.login_button);
        loginProgressB = findViewById(R.id.login_progressbar);
        loginProgressB.setVisibility(View.INVISIBLE);
        userLogin = findViewById(R.id.login_email_space);
        userSenha = findViewById(R.id.senha_space);
        mAuth = FirebaseAuth.getInstance();
        tabs = new Intent(this, TelaInicialActivity.class);


        btnLogin();
    }

    private void btnLogin() {
        btnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLoggin.setVisibility(View.INVISIBLE);
                loginProgressB.setVisibility(View.VISIBLE);

                final String loginStr = userLogin.getText().toString();
                final String senhaStr = userSenha.getText().toString();

                if (loginStr.isEmpty() || senhaStr.isEmpty()){
                    Toast.makeText(LoginSalvoActivity.this, "Preencha todos os campos!!", Toast.LENGTH_LONG).show();
                    btnLoggin.setVisibility(View.VISIBLE);
                    loginProgressB.setVisibility(View.INVISIBLE);

                }
                else{
                    singIn(loginStr,senhaStr);

                }


            }

        });
    }


    private void singIn(String loginStr, String senhaStr) {

        mAuth.signInWithEmailAndPassword(loginStr,senhaStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {

                if (task.isSuccessful()){
                    loginProgressB.setVisibility(View.INVISIBLE);
                    btnLoggin.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else{
                    btnLoggin.setVisibility(View.VISIBLE);
                    loginProgressB.setVisibility(View.INVISIBLE);
                    showMessage(task.getException().getMessage());

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




}