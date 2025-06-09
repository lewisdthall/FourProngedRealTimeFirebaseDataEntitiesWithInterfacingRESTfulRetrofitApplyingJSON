package lewisdthall.fourprongedJSONfirefit;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class FourProngedEntity implements Serializable {

    private final String id;
    @ColorInt
    private int particleColour;
    @ColorInt
    private int backgroundColour;
    private float size;
    private float speed;

    public FourProngedEntity(String id, int particleColour, int backgroundColour, float size, float speed) {
        this.id = id;
        this.particleColour = particleColour;
        this.backgroundColour = backgroundColour;
        this.size = size;
        this.speed = speed;
    }

    public void update(int particleColour, int backgroundColour, float size, float speed) {
        this.particleColour = particleColour;
        this.backgroundColour = backgroundColour;
        this.size = size;
        this.speed = speed;
    }

    public String getId() {
        return id;
    }

    public int getParticleColour() {
        return particleColour;
    }

    public int getBackgroundColour() {
        return backgroundColour;
    }

    public float getSize() {
        return size;
    }

    public float getSpeed() {
        return speed;
    }


    public static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        private ArrayList<FourProngedEntity> listedFPEs;
        private final ActivityResultLauncher<Intent> context;
        public Adapter(ArrayList<FourProngedEntity> listedFPEs, ActivityResultLauncher<Intent> context) {
            this.listedFPEs = listedFPEs;
            this.context = context;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.fpe_card, parent, false);
            return new Holder(view);
        }

        @Override
        public int getItemCount() {
            return listedFPEs.size();
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            FourProngedEntity fpe = listedFPEs.get(position);
            holder.particleView.particle(fpe);
            holder.particleView.setBackgroundColour(fpe.getBackgroundColour());
            holder.particleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), FourProngedTool.class);
                    intent.putExtra("requestType", "update");
                    intent.putExtra("fpe", fpe);
                    context.launch(intent);
                }
            });

        }

        public void update(ArrayList<FourProngedEntity> listedFPEs) {
            this.listedFPEs = listedFPEs;
            notifyDataSetChanged();
        }

        public class Holder extends RecyclerView.ViewHolder {
            Particle.ParticleView particleView;
            public Holder(@NonNull View itemView) {
                super(itemView);
                particleView = itemView.findViewById(R.id.particle_view);
            }
        }
    }
}
