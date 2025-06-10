package lewisdthall.fourprongedJSONfirefit;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostMain extends AppCompatActivity {
    Post.Loader api;
    ArrayList<Post> posts;
    RecyclerView postView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.post_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        postView = findViewById(R.id.post_view);
        loadPosts();
        Button showPosts = findViewById(R.id.show_posts);
        showPosts.setOnClickListener(v -> displayPosts());


    }
    public void loadPosts() {
        api = new Post.Loader(this);
        api.start();
    }
    public void displayPosts() {
        if(api != null){
            posts = api.posts;
            Post.Adapter adapter = new Post.Adapter(posts, this);
            postView.setLayoutManager(new LinearLayoutManager(this));
            postView.setAdapter(adapter);
        }
    }
}