package prototipo.italoluis.com.fireprot3.MainActivities;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import prototipo.italoluis.com.fireprot3.BloggerStructure.ListadorDePosts;
import prototipo.italoluis.com.fireprot3.LabelKeyController;
import prototipo.italoluis.com.fireprot3.R;


public class MenuInicialActivity extends AppCompatActivity implements View.OnClickListener {

    private LabelKeyController labelKeyController = new LabelKeyController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuinicial);

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
                Toast.makeText(MenuInicialActivity.this,"Clique novamente para sair do app", Toast.LENGTH_SHORT).show();
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
        switch(v.getId()){
            case R.id.cv_bases_fisicas:
                startPostListLoader(labelKeyController.getLabelKeyBaseFisica());
                break;

            case R.id.cv_radiofarmacia:
                startPostListLoader(labelKeyController.getLabelKey2Radiofarmacia());
                break;

            case R.id.cv_radionuclideos:
                startPostListLoader(labelKeyController.getLabelKeyRadionuclideos());
                break;

            case R.id.cv_equipamento:
                startPostListLoader(labelKeyController.getLabelKeyEquips());
                break;

            case R.id.cv_diag_terapia:
                startPostListLoader(labelKeyController.getLabelKeyDiagnoTerapia());
                break;

            case R.id.cv_protec_radio:
                startPostListLoader(labelKeyController.getLabelKeyRadioProtec());
                break;

            case R.id.cv_legislacao:
                startPostListLoader(labelKeyController.getLabelKeyLegisl());
                break;

            case R.id.cv_outros:
                startPostListLoader(labelKeyController.getLabelKeyOutros());
                break;

            default:
                Toast.makeText(this, "Opção incorreta", Toast.LENGTH_LONG).show();
                break;

        }
    }



    private void startPostListLoader(String labelkeyurl) {
        Intent intent =  new Intent(MenuInicialActivity.this, ListadorDePosts.class);
        intent.putExtra("urlCompletaBlogger", labelkeyurl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MenuInicialActivity.this).toBundle());
        }else{
            startActivity(intent);
        }

    }

}
