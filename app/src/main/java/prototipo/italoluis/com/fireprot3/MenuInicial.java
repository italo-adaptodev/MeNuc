package prototipo.italoluis.com.fireprot3;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import prototipo.italoluis.com.fireprot3.BloggerStructure.PostListLoader;


public class MenuInicial extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prototipotelainicial);

        findViewById(R.id.cv_bases_fisicas).setOnClickListener(this);
        findViewById(R.id.cv_radiofarmacia).setOnClickListener(this);
        findViewById(R.id.cv_radionuclideos).setOnClickListener(this);
        findViewById(R.id.cv_equipamento).setOnClickListener(this);
        findViewById(R.id.cv_diag_terapia).setOnClickListener(this);
        findViewById(R.id.cv_protec_radio).setOnClickListener(this);
        findViewById(R.id.cv_legislacao).setOnClickListener(this);
        findViewById(R.id.cv_outros).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int numCardViewClicado = 0;
        Intent intent = new Intent(MenuInicial.this, PostListLoader.class);

        switch(v.getId()){
            case R.id.cv_bases_fisicas:
                numCardViewClicado = 1;
                break;

            case R.id.cv_radiofarmacia:
                numCardViewClicado = 2;
                break;

            case R.id.cv_radionuclideos:
                numCardViewClicado = 3;
                break;

            case R.id.cv_equipamento:
                numCardViewClicado = 4;
                break;

            case R.id.cv_diag_terapia:
                numCardViewClicado = 5;
                break;

            case R.id.cv_protec_radio:
                numCardViewClicado = 6;
                break;

            case R.id.cv_legislacao:
                numCardViewClicado = 7;
                break;

            case R.id.cv_outros:
                numCardViewClicado = 8;
                break;

            default:
                Toast.makeText(this, "Opção incorreta", Toast.LENGTH_LONG).show();
                break;

        }
        startPostListLoader(numCardViewClicado, intent);
    }

    private void startPostListLoader(int numCardViewClicado, @NonNull Intent intent) {
        intent.putExtra("cardview escolhido", numCardViewClicado);
        startActivity(intent);
    }
}
