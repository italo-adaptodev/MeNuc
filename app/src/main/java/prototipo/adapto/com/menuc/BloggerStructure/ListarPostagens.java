package prototipo.adapto.com.menuc.BloggerStructure;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import prototipo.adapto.com.menuc.MainActivities.TelaInicialActivity;
import prototipo.adapto.com.menuc.api.APIBloggerLoader;
import prototipo.adapto.com.menuc.BlogModel.Item;
import prototipo.adapto.com.menuc.BlogModel.PostList;
import prototipo.adapto.com.menuc.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarPostagens extends AppCompatActivity {
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
                    startActivity(new Intent(ListarPostagens.this, TelaInicialActivity.class));
                    Toast.makeText(ListarPostagens.this, "Ops. Parece que não há nada aqui...", (Toast.LENGTH_LONG+4)).show();
                } else {
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(new AdaptadorPosts(ListarPostagens.this, list.getItems()));
                }
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(ListarPostagens.this, "Não foi possível carregar a página. " +
                        "Verifique sua internet e tente novamente.", Toast.LENGTH_LONG).show();

            }
        });
    }








}