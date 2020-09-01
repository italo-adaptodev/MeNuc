package adapto.com.menuc.MainActivities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adapto.com.menuc.R;
import adapto.com.menuc.SlidingTabLayout;
import adapto.com.menuc.TabAdapter;

public class TelaInicialActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private FirebaseAuth mAuth;
    private DatabaseReference dbRefAutores = FirebaseDatabase.getInstance().getReference().child("Autores");
    private Set<String> listaAutoresDB;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        mAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MeNuc");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.tabAccent));
        setSupportActionBar(toolbar);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = findViewById(R.id.vp_pagina);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.tabAccent));
        slidingTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            slidingTabLayout.setOutlineAmbientShadowColor(ContextCompat.getColor(this, R.color.tabAccent));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            slidingTabLayout.setOutlineSpotShadowColor(ContextCompat.getColor(this, R.color.tabAccent));
        }
        slidingTabLayout.setDistributeEvenly(true);

        //Configuração do adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        slidingTabLayout.setViewPager(viewPager);
        listaAutoresDB = new HashSet<>();
        listarAutores();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // or finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                startActivity(new Intent(this, SobreOProjetoActivity.class));
                break;
            case R.id.item2:
                startActivity(new Intent(this, FaleConoscoActivity.class));
                break;
            case R.id.item3:
                mAuth.signOut();
                Toast.makeText(TelaInicialActivity.this, "Logout realizado", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginSalvoActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void listarAutores() {
        final Query checkQ = dbRefAutores.orderByChild("nomeIndicado");
        if(listaAutoresDB.isEmpty()) {
            checkQ.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        listaAutoresDB.add(postSnapshot.child("emailIndicado").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }


            });
        }
        SharedPreferences sharedPref = this.getSharedPreferences("LISTA_AUTORES_MENUC", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet("LISTA_AUTORES_TELAINICIAL", listaAutoresDB);
        editor.commit();

    }
}