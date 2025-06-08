package lewisdthall.fourprongedJSONfirefit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        FourProngedEntity fpe = new FourProngedEntity("1", Color.rgb(255, 0, 0), Color.rgb(0, 255, 0), 20.0f, 10);
        ArrayList<FourProngedEntity> listedFPEs = new ArrayList<>();
        listedFPEs.add(fpe);

        RecyclerView recyclerView = findViewById(R.id.fpe_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fpe.getAdapter(listedFPEs));

        Button addFPEButton = findViewById(R.id.add_fpe_button);
        addFPEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FourProngedActivity.class);
                intent.putExtra("requestType", "create");
                startActivity(intent);
            }
        });



    }
}