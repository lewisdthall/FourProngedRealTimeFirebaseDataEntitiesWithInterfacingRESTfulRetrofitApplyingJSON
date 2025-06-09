package lewisdthall.fourprongedJSONfirefit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Reqres {
    @GET("users/")
    Call<User.Users> getUsers();
}
