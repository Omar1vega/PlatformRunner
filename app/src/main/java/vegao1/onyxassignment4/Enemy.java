package vegao1.onyxassignment4;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Enemy extends Movable implements GameItem{
    protected int reward;
    public Enemy(Bitmap[] img, int x, int y){
        images = img;
        PRINTSIZE = 150;
        rectangle = new Rect();
        setRectangle(x,y);
        live = true;
    }
    public int getReward(){return reward;}
    public int getPoints(MarioVisitor visitor){
        return visitor.visit(this);
    }
    public void update(UpdateVisitor uv){
        uv.update(this);
    }

}
