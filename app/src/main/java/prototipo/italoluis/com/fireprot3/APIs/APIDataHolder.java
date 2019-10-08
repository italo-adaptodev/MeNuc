package prototipo.italoluis.com.fireprot3.APIs;

import prototipo.italoluis.com.fireprot3.BlogModel.PostList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class APIDataHolder {

    private static final String labelKey = "labels=BASE+FÍSICA&key=AIzaSyC3QWpASkuWTURfubDhYDRfFAh-0S4nQLY";
    private static final String url = "https://www.googleapis.com/blogger/v3/blogs/537701014572510680/";

    public static APIDataHolder.PostService postService = null;

    public static APIDataHolder.PostService getService(){

        if(postService == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(APIDataHolder.PostService.class);
        }
        return postService;
    }

    public interface PostService {
        @GET("posts?"+labelKey)
        retrofit2.Call<PostList> getPostList();

    }

}
