package vegao1.onyxassignment4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.ParcelUuid;

import java.security.PublicKey;

public abstract class OxBlock implements GameItem {
    protected Bitmap image;
    protected Rect rectangle;
    protected int PRINTSIZE;
    protected boolean live;

    @Override
    public Rect getRectangle() {
        return rectangle;
    }
    public boolean getLive(){ return live;}

    public void setRectangle(int x, int y){
        rectangle.set(x, y, x+PRINTSIZE, y+PRINTSIZE);
    }
    public int getPRINTSIZE(){
        return PRINTSIZE;
    }
    //draw methods
    @Override
    public void draw(Canvas canvas) {
        if(null!=canvas){
            canvas.drawBitmap(image,null,rectangle,null);
        }
    }//draw()

    @Override
    public int getPoints(MarioVisitor visitor) {
        return 0;
    }
    @Override
    public void update(UpdateVisitor visitor) {}

    //COLLISION DETECT
    public boolean hitTop(int y){
        return y>rectangle.top;
    }
    public boolean hitBottom(int y){
        return y<rectangle.bottom;
    }
    public boolean hitRight(int x){
        return x<rectangle.right;
    }
    public  boolean hitLeft(int x){
        return  x>rectangle.left;
    }  

    public boolean hitBottom(OxBlock other){
        return hitBottom(other.getRectangle().top);
    }

    public boolean hitTop(OxBlock other){
        return hitTop(other.getRectangle().bottom);
    }

    public boolean isBetween(OxBlock other){
        boolean r = hitRight(rectangle.right)&&
                other.hitLeft(rectangle.right);
        boolean l = other.hitLeft(rectangle.left)&&
                other.hitRight(rectangle.left);
        return r||l;
    }

}
