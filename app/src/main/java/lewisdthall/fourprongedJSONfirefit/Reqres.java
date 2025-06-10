package lewisdthall.fourprongedJSONfirefit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Reqres {
    @GET("posts/")
    Call<ArrayList<Post>> getPosts();
}
