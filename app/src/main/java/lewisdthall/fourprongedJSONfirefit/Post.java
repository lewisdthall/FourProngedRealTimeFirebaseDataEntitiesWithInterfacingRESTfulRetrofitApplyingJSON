package lewisdthall.fourprongedJSONfirefit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Post implements Serializable {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(int userId, int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    public static class Loader implements Callback<ArrayList<Post>> {
            final String BASE_URL="https://jsonplaceholder.typicode.com/";
            public ArrayList<Post> posts;
            private PostMain context;

            public Loader(){
            }
            public Loader(PostMain context){
                this.context = context;
            }
            public void start(){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Reqres reqres = retrofit.create(Reqres.class);
                Call<ArrayList<Post>> call  = reqres.getPosts();
                call.enqueue(this);
            }
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                if(response.isSuccessful() && response.body() != null){
                    posts = response.body();
                    Log.d("USER_API","Getting user data successful " );
                    if(posts !=null){
                        Log.d("USER_API", "Posts:"+ posts.toString());

                    } else{
                        Log.d("USER_API","Error Post's list is empty");
                    }
                }else{
                    Log.d("USER_API","Error getting posts");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                t.printStackTrace();
                Log.d("USER_API","Error getting posts");
            }
    }
    public static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private final Context context;
        private ArrayList<Post> posts;

        public Adapter(ArrayList<Post> posts, Context context) {
            this.posts = posts;
            this.context = context;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.post_card, parent, false);
            return new Holder(view);
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            Post post = posts.get(position);
            holder.title.setText("Title:" + post.getTitle());
            holder.body.setText("Body: " + post.getBody());
        }

        public static class Holder extends RecyclerView.ViewHolder {
            TextView title, body;
            public Holder(@NonNull View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                body = itemView.findViewById(R.id.body);
            }
        }
    }
}
