package lewisdthall.fourprongedJSONfirefit;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class FourProngedActivity extends AppCompatActivity {

    FourProngedEntity fpe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fpe_create_view);

        Particle.ParticleView particleView = findViewById(R.id.particle_view);
        NumberPicker sizePicker = findViewById(R.id.particle_size_picker);
        NumberPicker speedPicker = findViewById(R.id.particle_speed_picker);
        Button particleColourButton = findViewById(R.id.particle_colour_button);
        Button backgroundColourButton = findViewById(R.id.background_colour_button);
        Button goBackButton = findViewById(R.id.go_back_button);
        Button deleteButton = findViewById(R.id.delete_button);



        if (Objects.equals(getIntent().getStringExtra("requestType"), "create")) {
            fpe = new FourProngedEntity("1", Color.WHITE, Color.BLACK, 20.0f, 10);
            particleView.setBackgroundColour(Color.BLACK);
        }
        else if (Objects.equals(getIntent().getStringExtra("requestType"), "update")) {
            fpe = (FourProngedEntity) getIntent().getSerializableExtra("fpe");
            assert fpe != null;
            particleView.particle(fpe, 10, 10);
            particleView.setBackgroundColour(fpe.getBackgroundColour());
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}