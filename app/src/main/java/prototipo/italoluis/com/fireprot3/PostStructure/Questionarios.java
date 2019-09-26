package prototipo.italoluis.com.fireprot3.PostStructure;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.fireprot3.APIs.ApiBloggerQuestionario;
import prototipo.italoluis.com.fireprot3.AutorizacaoActivity;
import prototipo.italoluis.com.fireprot3.BlogModel.Item;
import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import prototipo.italoluis.com.fireprot3.SendInviteActivity;
import prototipo.italoluis.com.fireprot3.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Questionarios extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;

    private FloatingActionButton fab_main, fab1_quest, fab2_invite, fab3_author;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private PostAdapter adapter;
    private List<Item> items = new ArrayList<>();
    private TextView txt_quest, txt_invite, txt_author, receive;
    private AsyncHttpClient client = new AsyncHttpClient();
    private Boolean isOpen = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.listadepost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        manager = new LinearLayoutManager(this);
        adapter = new PostAdapter(this, items);
        recyclerView.setAdapter(adapter);
        receive = findViewById(R.id.receive);
        receive.setText(getIntent().getStringExtra("valor"));


        fabConfig();

        txt_quest =  findViewById(R.id.txt_questionario);
        txt_invite =  findViewById(R.id.txt_indicacao);
        txt_author =  findViewById(R.id.txt_autores);

        txt_quest.setText("Inicio");


        fabMainAction();
        getData();

        fabOnClick();
    }

    private void fabOnClick() {
        fab1_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questionarios.this, TelaInicial.class);
                startActivity(intent);
            }
        });

        fab2_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questionarios.this, SendInviteActivity.class);
                intent.putExtra("valor", receive.getText().toString());
                startActivity(intent);
            }
        });

        fab3_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Questionarios.this, AutorizacaoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fabConfig() {
        fab_main = findViewById(R.id.fabmain);
        fab1_quest = findViewById(R.id.fab1);
        fab2_invite = findViewById(R.id.fab2);
        fab3_author = findViewById(R.id.fab3);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotacao2);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotacao1);
    }

    private void getData() {
        Call<PostList> postList = ApiBloggerQuestionario.getService().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                items.addAll(list.getItems());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(new PostAdapter(Questionarios.this, list.getItems()));
                Toast.makeText(Questionarios.this, "Efetuado com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(Questionarios.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void fabMainAction(){

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {

                    txt_author.setVisibility(View.INVISIBLE);
                    txt_invite.setVisibility(View.INVISIBLE);
                    txt_quest.setVisibility(View.INVISIBLE);
                    fab1_quest.startAnimation(fab_close);
                    fab2_invite.startAnimation(fab_close);
                    fab3_author.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anticlock);
                    fab1_quest.setClickable(false);
                    fab2_invite.setClickable(false);
                    fab3_author.setClickable(false);
                    isOpen = false;
                } else {
                    txt_author.setVisibility(View.VISIBLE);
                    txt_invite.setVisibility(View.VISIBLE);
                    txt_quest.setVisibility(View.VISIBLE);
                    fab1_quest.startAnimation(fab_open);
                    fab2_invite.startAnimation(fab_open);
                    fab3_author.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab1_quest.setClickable(true);
                    fab2_invite.setClickable(true);
                    fab3_author.setClickable(true);
                    isOpen = true;
                }

            }
        });
    }
}




