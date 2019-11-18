package prototipo.italoluis.com.fireprot3.BloggerStructure;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.fireprot3.APIs.APIBloggerLoader;
import prototipo.italoluis.com.fireprot3.MainActivities.AutorizacaoActivity;
import prototipo.italoluis.com.fireprot3.BlogModel.Item;
import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import prototipo.italoluis.com.fireprot3.MainActivities.ListaAutoresActivity;
import prototipo.italoluis.com.fireprot3.MainActivities.MenuInicialActivity;
import prototipo.italoluis.com.fireprot3.R;
import prototipo.italoluis.com.fireprot3.MainActivities.EnviarConviteActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadorPosts extends AppCompatActivity {
    private FloatingActionButton fab_main, fab1_quest, fab2_invite, fab3_author, fab4_autorizacao;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    private TextView txt_quest, txt_invite, txt_author, txt_autoziracao;
    private Boolean isOpen = false;
    private RecyclerView recyclerView;
    private AdaptadorPosts adapter;
    private String urlCompleta;
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list_model);
        recyclerView = findViewById(R.id.listadepost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdaptadorPosts(this, items);
        recyclerView.setAdapter(adapter);
        urlCompleta = getIntent().getStringExtra("urlCompletaBlogger");
        txt_quest =  findViewById(R.id.txt_questionario);
        txt_invite =  findViewById(R.id.txt_indicacao);
        txt_author =  findViewById(R.id.txt_autores);
        txt_autoziracao = findViewById(R.id.txt_autorizacao);

        fabConfig();
        getData();
        fabMainAction();
        fabOnClick();
    }

    private void fabOnClick(){
        fab1_quest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityWAnimation(Questionarios.class);
            }
        });

        fab2_invite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityWAnimation(EnviarConviteActivity.class);
            }
        });

        fab3_author.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityWAnimation(ListaAutoresActivity.class);
            }
        });

        fab4_autorizacao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 startActivityWAnimation(AutorizacaoActivity.class);
            }
        });
    }

    private void startActivityWAnimation(Class nextActivity){
        Intent intent = new Intent(ListadorPosts.this, nextActivity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            closeFloatActionButton();
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ListadorPosts.this).toBundle());
        }else{
            closeFloatActionButton();
            startActivity(intent);
        }

    }

    private void fabConfig(){
        fab_main = findViewById(R.id.fabmain);
        fab1_quest = findViewById(R.id.fab1);
        fab2_invite = findViewById(R.id.fab2);
        fab3_author = findViewById(R.id.fab3);
        fab4_autorizacao = findViewById(R.id.fab4);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotacao2);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotacao1);
    }

    private void getData(){
        Call<PostList> postList = APIBloggerLoader.getService().getPostList(urlCompleta);

        postList.enqueue(new Callback<PostList>() {

            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                if (list.getItems() == null) {
                    startActivity(new Intent(ListadorPosts.this, MenuInicialActivity.class));
                    Toast.makeText(ListadorPosts.this, "Ops. Parece que não há nada aqui...", (Toast.LENGTH_LONG+4)).show();

                } else {
                    items.addAll(list.getItems());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(new AdaptadorPosts(ListadorPosts.this, list.getItems()));
                    Toast.makeText(ListadorPosts.this, "Efetuado com sucesso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(ListadorPosts.this, "Não foi possível carregar a página. " +
                        "Verifique sua internet e tente novamente.", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void fabMainAction(){

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {
                    closeFloatActionButton();
                } else {
                    openFloatActionButton();
                }


            }
        });
    }

    private void openFloatActionButton(){
        startAnimationFab(fab1_quest, fab_open, fab2_invite, fab3_author, fab4_autorizacao, fab_open);
        fab_main.startAnimation(fab_clock);
        isFabClickable(true);
        txt_author.setVisibility(View.VISIBLE);
        txt_author.startAnimation(fab_open);
        txt_invite.setVisibility(View.VISIBLE);
        txt_invite.startAnimation(fab_open);
        txt_quest.setVisibility(View.VISIBLE);
        txt_quest.startAnimation(fab_open);
        txt_autoziracao.setVisibility(View.VISIBLE);
        txt_autoziracao.startAnimation(fab_open);
        isOpen = true;
    }

    private void startAnimationFab(FloatingActionButton fab1_quest, Animation fab_open, FloatingActionButton fab2_invite, FloatingActionButton fab3_author, FloatingActionButton fab4_autorizacao, Animation fab_open2) {
        fab1_quest.startAnimation(fab_open);
        fab2_invite.startAnimation(fab_open);
        fab3_author.startAnimation(fab_open);
        fab4_autorizacao.startAnimation(fab_open2);
        fab_main.startAnimation(fab_anticlock);
    }

    private void closeFloatActionButton(){
        startAnimationFab(fab1_quest, fab_close, fab2_invite, fab3_author, fab4_autorizacao, fab_close);
        isFabClickable(false);
        txt_author.setVisibility(View.INVISIBLE);
        txt_author.startAnimation(fab_close);
        txt_invite.setVisibility(View.INVISIBLE);
        txt_invite.startAnimation(fab_close);
        txt_quest.setVisibility(View.INVISIBLE);
        txt_quest.startAnimation(fab_close);
        txt_autoziracao.setVisibility(View.INVISIBLE);
        txt_autoziracao.startAnimation(fab_close);
        isOpen = false;
    }

    private void isFabClickable(boolean b) {
        fab1_quest.setClickable(b);
        fab2_invite.setClickable(b);
        fab3_author.setClickable(b);
        fab4_autorizacao.setClickable(b);
    }
}