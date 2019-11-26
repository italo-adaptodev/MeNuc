package prototipo.italoluis.com.fireprot3.BloggerStructure;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        getData();
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








}