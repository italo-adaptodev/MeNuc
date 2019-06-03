package prototipo.italoluis.com.fireprot3;

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


public class Login extends AppCompatActivity {

    EditText nome;
    EditText senha;
    EditText email;
    Button criarlogin;
    TextView textologin;
    Button logoutbtn;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        nome =  findViewById(R.id.nomeText);
        email =  findViewById(R.id.emailText);
        senha =  findViewById(R.id.senhaText);
        criarlogin = (Button) findViewById(R.id.button);
        textologin = findViewById(R.id.textView4);
        logoutbtn = findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Toast.makeText(Login.this, "Logout", Toast.LENGTH_LONG).show();
            }
        });

        criarlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userEmail = email.getText().toString();
                final  String password = senha.getText().toString();
                final String username = nome.getText().toString();

                saveusername(username);

                if(!TextUtils.isEmpty(username) || !TextUtils.isEmpty(userEmail) || !TextUtils.isEmpty(password)){

                    CriarConta(username, userEmail, password, view);

                }else{
                    Toast.makeText(Login.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }
            }
        });

        textologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Login2.class);
                startActivity(intent);

            }
        });

    }

    private void CriarConta(String username, String userEmail, String password, final View view) {


        mAuth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Conta criada", Toast.LENGTH_LONG).show();
                            showAlertDialogButtonClicked(view);



                        }
                        else{
                            Toast.makeText(Login.this, "Ocorreu um erro. Tente novamente" + task.getException().getMessage(), Toast.LENGTH_LONG ).show();
                        }
                    }
                });
    }

    public void showAlertDialogButtonClicked(View view) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Novidade!");
        builder.setMessage("Agora você tem acesso a uma lista de questionários! Basta clicar no ícone no canto inferior direito da tela.");
        // add a button
        builder.setPositiveButton("Entendido!",new DialogInterface
                .OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog,
                                int which)
            {

                // When the user click yes button
                // then app will close
                Intent intent = new Intent(Login.this, TesteScript.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void saveusername(String user){
       SharedPreferences preferences = getSharedPreferences("prototipo.italoluis.com.fireprot3", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = preferences.edit();
       editor.putString("nome_usuario", user);
       editor.commit();

    }


}
