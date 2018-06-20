package vegao1.onyxassignment4;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface GameItem {
    public void update(UpdateVisitor visitor);
    public Rect getRectangle();
    public void draw(Canvas canvas);
    public int getPoints(MarioVisitor visitor);
}
