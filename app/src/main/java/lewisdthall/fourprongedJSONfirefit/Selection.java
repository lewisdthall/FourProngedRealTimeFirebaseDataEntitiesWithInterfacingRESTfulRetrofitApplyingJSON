package lewisdthall.fourprongedJSONfirefit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Selection extends AppCompatActivity {
    Button fpeButton, postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fpeButton = findViewById(R.id.fpe);
        postButton = findViewById(R.id.post);
        fpeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, FourProngedMain.class);
            startActivity(intent);
        });
        postButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PostMain.class);
            startActivity(intent);
        });

    }
}
