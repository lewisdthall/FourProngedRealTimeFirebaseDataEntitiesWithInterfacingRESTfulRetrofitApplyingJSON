package lewisdthall.fourprongedJSONfirefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FourProngedMain extends AppCompatActivity {
    ArrayList<FourProngedEntity> listedFPEs = new ArrayList<>();
    RecyclerView recyclerView;
    FourProngedEntity.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FireBase.instance();
        getFPEs();

        EdgeToEdge.enable(this);
        setContentView(R.layout.fpe_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });






        recyclerView = findViewById(R.id.fpe_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FourProngedEntity.Adapter(listedFPEs, this.getFPEResultLauncher);
        recyclerView.setAdapter(adapter);


        Button addFPEButton = findViewById(R.id.add_fpe_button);
        addFPEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourProngedMain.this, FourProngedTool.class);
                intent.putExtra("requestType", "create");
                getFPEResultLauncher.launch(intent);
            }
        });
    }

    public void getFPEs() {
        FireBase.ref("FPEs");
        FireBase.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listedFPEs.clear();
                FourProngedEntity fpe;
                int particleColour, backgroundColour;
                float size, speed;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    particleColour = dataSnapshot.child("particleColour").getValue(Integer.class);
                    backgroundColour = dataSnapshot.child("backgroundColour").getValue(Integer.class);
                    size = dataSnapshot.child("size").getValue(Float.class);
                    speed = dataSnapshot.child("speed").getValue(Float.class);


                    fpe = new FourProngedEntity(
                            dataSnapshot.getKey(),
                            particleColour,
                            backgroundColour,
                            size,
                            speed
                    );

                    listedFPEs.add(fpe);
                }
                adapter.update(listedFPEs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public ActivityResultLauncher<Intent> getFPEResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    getFPEs();
                }
            }
    );
}