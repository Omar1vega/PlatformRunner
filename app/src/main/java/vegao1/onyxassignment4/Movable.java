package vegao1.onyxassignment4;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Movable extends OxBlock {
    Bitmap[] images;
    protected int imageCycle;
    protected boolean leftFlag;
    protected boolean rightFlag;

    public void cycleImage(){
        imageCycle = (imageCycle<images.length)?imageCycle++:0;
    }

    public void shiftBy(int x, int y){
        rectangle.offset(x,y);
    }

    @Override
    public void draw(Canvas canvas) {
        if(null!=canvas){
            canvas.drawBitmap(images[imageCycle],null,rectangle,null);
        }
    }//draw()
}
