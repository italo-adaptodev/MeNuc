package prototipo.italoluis.com.fireprot3;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import prototipo.italoluis.com.fireprot3.postsblog.Home;


public class Login2 extends AppCompatActivity {

    private Button loginB;
    private ProgressBar LoginProgressB;
    private EditText userlogin, usersenha;
    private FirebaseAuth mAuth;
    private Intent tabs;
    Dialog dialogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        loginB = findViewById(R.id.login_button);
        LoginProgressB = findViewById(R.id.login_progressbar);
        LoginProgressB.setVisibility(View.INVISIBLE);
        userlogin = findViewById(R.id.login_email_space);
        usersenha = findViewById(R.id.senha_space);
        mAuth = FirebaseAuth.getInstance();
        tabs = new Intent(this, Home.class);




        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginB.setVisibility(View.INVISIBLE);
                LoginProgressB.setVisibility(View.VISIBLE);

                final String loginStr = userlogin.getText().toString();
                final String senhaStr = usersenha.getText().toString();

                if (loginStr.isEmpty() || senhaStr.isEmpty()){
                    Toast.makeText(Login2.this, "Preencha todos os campos!!", Toast.LENGTH_LONG).show();
                    loginB.setVisibility(View.VISIBLE);
                    LoginProgressB.setVisibility(View.INVISIBLE);

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
                    LoginProgressB.setVisibility(View.INVISIBLE);
                    loginB.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else{
                    loginB.setVisibility(View.VISIBLE);
                    LoginProgressB.setVisibility(View.INVISIBLE);
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
            //user is already connected  so we need to redirect him to home page
            updateUI();

        }

    }




}
