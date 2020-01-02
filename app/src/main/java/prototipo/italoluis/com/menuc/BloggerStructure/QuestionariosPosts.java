package prototipo.italoluis.com.menuc.BloggerStructure;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.menuc.api.ApiBloggerQuestionario;
import prototipo.italoluis.com.menuc.BlogModel.Item;
import prototipo.italoluis.com.menuc.BlogModel.PostList;
import prototipo.italoluis.com.menuc.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionariosPosts extends AppCompatActivity {


    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private AdaptadorPosts adapter;
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list_model);
        recyclerView = findViewById(R.id.listadepost);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        manager = new LinearLayoutManager(this);
        adapter = new AdaptadorPosts(this, items);
        recyclerView.setAdapter(adapter);
        getData();
    }
    private void getData() {
        Call<PostList> postList = ApiBloggerQuestionario.getService().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                items.addAll(list.getItems());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(new AdaptadorPosts(QuestionariosPosts.this, list.getItems()));
                Toast.makeText(QuestionariosPosts.this, "Efetuado com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(QuestionariosPosts.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}




