package prototipo.italoluis.com.fireprot3;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.fireprot3.APIs.APIDataHolder;
import prototipo.italoluis.com.fireprot3.BlogModel.Item;
import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import prototipo.italoluis.com.fireprot3.PostStructure.PostAdapter;
import prototipo.italoluis.com.fireprot3.PostStructure.Questionarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuInicial extends AppCompatActivity {


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
        setContentView(R.layout.prototipotelainicial);

        findViewById(R.id.cv_bases_fisicas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuInicial.this, Questionarios.class);
                startActivity(intent);
            }
        });

    }


// ESTE MÉTODO ESTARÁ NUMA CLASSE SIMILAR A CLASSE QUESTIONÁRIOS, ONDE SERÁ CRIADA A RECYCLERVIEW. REPETIREMOS O FINDVIEWBYID MOSTRADO A CIMA
    // PARA TODOS OS CARDVIEWS, CADA UM COM UMA APIHOLDER DIFERENTE PARA GERAR A STRING DA URL


//    private void getData(){
//        Call<PostList> postList = APIDataHolder.getService().getPostList();
//        postList.enqueue(new Callback<PostList>() {
//            @Override
//            public void onResponse(Call<PostList> call, Response<PostList> response) {
//                PostList list = response.body();
//                items.addAll(list.getItems());
//                adapter.notifyDataSetChanged();
//                recyclerView.setAdapter(new PostAdapter(MenuInicial.this, list.getItems()));
//                Toast.makeText(MenuInicial.this, "Efetuado com sucesso", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<PostList> call, Throwable t) {
//                Toast.makeText(MenuInicial.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
