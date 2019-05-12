package prototipo.italoluis.com.fireprot3.postsblog;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiBloggerQuestionário {
    private static final String key = "AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private static final String url = "https://www.googleapis.com/blogger/v3/blogs/4680066170031091498/posts/";

    public static PostService postService = null;

    public static PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService {
        @GET("?key="+key)
        Call<PostList> getPostList();

        @GET("{postId}/?key="+key)
        Call<Item> getPostById(@Path("postId") String id);
    }

}

