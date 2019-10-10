package prototipo.italoluis.com.fireprot3;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import prototipo.italoluis.com.fireprot3.BloggerStructure.PostListLoader;


public class MenuInicial extends AppCompatActivity implements View.OnClickListener {

    private String labelKeyBaseFisica = "posts?labels=BASE+FÍSICA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String labelKey2Radiofarmacia = "posts?labels=RADIOFARMACIA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String labelKeyRadionuclideos = "posts?labels=RADIONUCLIDEOS&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String labelKeyEquips = "posts?labels=EQUIPAMENTOS&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String labelKeyDiagnoTerapia = "posts?labels=DIAGNOSTICO+E+TERAPIA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String labelKeyRadioProtec = "posts?labels=PROTECAO+RADIOLOGICA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String labelKeyLegisl = "posts?labels=LEGISLACAO&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String labelKeyOutros = "posts?labels=OUTROS&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private String url = "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/";

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
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja sair do aplicativo?");
        builder.setPositiveButton("Sim",new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which)
            {
                finish();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onClick(View v) {
        String labelkey ="";
        Intent intent = new Intent(MenuInicial.this, PostListLoader.class);

        switch(v.getId()){
            case R.id.cv_bases_fisicas:
                labelkey = labelKeyBaseFisica;
                break;

            case R.id.cv_radiofarmacia:
                labelkey = labelKey2Radiofarmacia;
                break;

            case R.id.cv_radionuclideos:
                labelkey = labelKeyRadionuclideos;
                break;

            case R.id.cv_equipamento:
                labelkey = labelKeyEquips;
                break;

            case R.id.cv_diag_terapia:
                labelkey = labelKeyDiagnoTerapia;
                break;

            case R.id.cv_protec_radio:
                labelkey = labelKeyRadioProtec;
                break;

            case R.id.cv_legislacao:
                labelkey = labelKeyLegisl;
                break;

            case R.id.cv_outros:
                labelkey = labelKeyOutros;
                break;

            default:
                Toast.makeText(this, "Opção incorreta", Toast.LENGTH_LONG).show();
                break;

        }
        startPostListLoader(labelkey, intent);
    }

    private void startPostListLoader(String labelkey, @NonNull Intent intent) {
        intent.putExtra("urlCompletaBlogger", url + labelkey);
        startActivity(intent);
    }

}
