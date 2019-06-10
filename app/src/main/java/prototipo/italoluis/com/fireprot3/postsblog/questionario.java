package prototipo.italoluis.com.fireprot3.postsblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.List;

import prototipo.italoluis.com.fireprot3.PostAdapter;
import prototipo.italoluis.com.fireprot3.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class questionario extends AppCompatActivity {

  private SwipeRefreshLayout swipeContainer;

  FloatingActionButton fab;
  RecyclerView recyclerView;
  LinearLayoutManager manager;
  PostAdapter adapter;
  List<Item> items = new ArrayList<>();
  Boolean Scroll = false;
  int numberItem, Itemtotal, scrollItem;
  AsyncHttpClient client = new AsyncHttpClient();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    recyclerView = findViewById(R.id.listadepost);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    manager = new LinearLayoutManager(this);
    adapter = new PostAdapter(this, items);
    recyclerView.setAdapter(adapter);
    final Boolean Scroll = false;
    int numberItem, Itemtotal, scrollItem;



    getData();

    FloatingActionButton fab = findViewById(R.id.fabback);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(questionario.this, questionario.class);
        startActivity(intent);
      }
    });
  }

  private void getData() {
    Call<PostList> postList = ApiBloggerQuestionário.getService().getPostList();
    postList.enqueue(new Callback<PostList>() {
      @Override
      public void onResponse(Call<PostList> call, Response<PostList> response) {
        PostList list = response.body();
        items.addAll(list.getItems());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(new PostAdapter(questionario.this, list.getItems()));
        Toast.makeText(questionario.this, "Efetuado com sucesso", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onFailure(Call<PostList> call, Throwable t) {
        Toast.makeText(questionario.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();

      }
    });
  }
}


    /*public void fetchTimelineAsync(int page)
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        client.get(new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
                // Remember to CLEAR OUT old items before appending in the new ones
                adapter.clear();
                // ...the data has come back, add new items to your adapter...
                //adapter.addAll(list);
                // Now we call setRefreshing(false) to signal refresh has finished
                swipeContainer.setRefreshing(false);
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }*/




    /*public void addAll(List<Item> list) {
        items.addAll(list);
        adapter.notifyDataSetChanged();
    }*/

