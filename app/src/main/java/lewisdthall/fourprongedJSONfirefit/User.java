package lewisdthall.fourprongedJSONfirefit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    public User(String avatar, String email, String first_name, int id, String last_name) {
        this.avatar = avatar;
        this.email = email;
        this.first_name = first_name;
        this.id = id;
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatar='" + avatar + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
    public class Users {
        private List<User> users;
        public List<User> get() {
            return users;
        }
        public void set(List<User> users) {
            this.users = users;
        }
    }
    public static class Controller implements Callback<Users> {
            final String BASE_URL="https://reqres.in/api/";
            public Users users;
            public void start(){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Reqres reqres = retrofit.create(Reqres.class);
                Call<Users> call  = reqres.getUsers();
                call.enqueue(this);
            }
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful()){
                    users = response.body();
                    Log.d("USER_API","Getting user data successful " );
                    if(users.get() !=null){
                        Log.d("USER_API", "Users:"+ users.get().toString());

                    } else{
                        Log.d("USER_API","Error User's list is empty");
                    }
                }else{
                    Log.d("USER_API","Error getting users");
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.d("USER_API","Error getting users");
            }
    }
    public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private final Context context;
        private ArrayList<User> users;

        public Adapter(ArrayList<User> users, Context context) {
            this.users = users;
            this.context = context;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.user_card, parent, false);
            return new Holder(view);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            User user = users.get(position);
            holder.name.setText(user.getFirst_name() + " " + user.getLast_name());
            Glide.with(context).load(user.getAvatar()).into(holder.avatar);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public class Holder extends RecyclerView.ViewHolder {
            View itemView;
            TextView name;
            ImageView avatar;
            public Holder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                avatar = itemView.findViewById(R.id.avatar);
                this.itemView = itemView;
            }
        }
    }
}
