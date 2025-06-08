package lewisdthall.fourprongedJSONfirefit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import java.util.Random;

public class Particle {
    private final Paint paint = new Paint();
    private float size;
    private float speed;
    private float x;
    private float y;

    public Particle(int paintColour, float size, float speed, float x, float y) {
        this.paint.setColor(paintColour);
        this.size = size;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void update(int paintColour, float size, float speed, float x, float y) {
        this.paint.setColor(paintColour);
        this.size = size;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void updatePosition(float x, float y) {
        this.x += x;
        this.y += y;
    }


    public static class ParticleView extends View {
        Particle particle = new Particle(Color.WHITE, 20, 10, 10, 10);
        float speedX, speedY;



        public ParticleView(Context context) {
            super(context);
        }

        public ParticleView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public ParticleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        public ParticleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public void particle(FourProngedEntity fpe, float x, float y) {
            particle.update(fpe.getParticleColour(), fpe.getSize(), fpe.getSpeed(), x, y);
            speedX = new Random().nextInt(100);
            speedX = 80 > speedX && speedX > 20 ? particle.speed / 100 * speedX : particle.speed / 2;
            speedY = particle.speed - speedX;
        }

        public void boundParticle() {
            if (particle.x < 0 || particle.x > getWidth()) {
                speedX *= -1.0f;
            }
            if (particle.y < 0 || particle.y > getHeight()) {
                speedY *= -1.0f;
            }
        }


        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawCircle(particle.x, particle.y, particle.size, particle.paint);
            particle.updatePosition(speedX, speedY);
            boundParticle();
            invalidate();
        }

        public void setBackgroundColour(int colour) {
            setBackgroundColor(colour);
        }
    }
}
