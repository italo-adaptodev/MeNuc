package prototipo.italoluis.com.fireprot3.APIs;

import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class APIBloggerLoader {

    private final static String url = "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/";

    public static PostServiceBaseFisica postService = null;

    public static PostServiceBaseFisica getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostServiceBaseFisica.class);
        }
        return postService;
    }

    public interface PostServiceBaseFisica {
        @GET
        retrofit2.Call<PostList> getPostList(@Url String fullUrl);
    }

    // criar varias interfaces "postservices", uma pra cada String label statica (8 no total)
}
