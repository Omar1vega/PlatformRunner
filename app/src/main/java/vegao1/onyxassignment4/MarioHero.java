package vegao1.onyxassignment4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class MarioHero extends Movable {
    Bitmap[] leftImages;
    Bitmap[] rightImages;
    Bitmap[] jumpImages;
    Bitmap[] deadImages;
    private int state;
    private boolean jumpFlag;

    public void setLeft(){
        state=0;
    }
    public void setRight(){
        state=1;
    }
    public void setJump(){
        state=2;
    }
    public void setDead(){
        state=3;
    }

    public Bitmap[] imageToPrint(){
        switch (state){
            case 0:
                return leftImages;
            case 1:
                return rightImages;
            case 2:
                return jumpImages;
            default:
                return deadImages;
        }
    }

    @Override
    public void draw(Canvas canvas){
        if(null!=canvas){
            images = imageToPrint();
            cycleImage();
            canvas.drawBitmap(images[imageCycle],null,rectangle,null);
        }
    }
    @Override
    public void cycleImage() {
        imageCycle = (imageCycle<imageToPrint().length)?(imageCycle++):0;
    }

    public void jump(){
        setJump();
        jumpFlag=true;
    }
    public void endjump(){
        jumpFlag = false;
    }
    public boolean getJumpFlag(){return jumpFlag;}

    public MarioHero(Bitmap[] left,Bitmap[] right,Bitmap[] jump,Bitmap[] dead, int x, int y){
        leftImages = left;
        rightImages = right;
        jumpImages= jump;
        deadImages = dead;
        PRINTSIZE = 150;
        imageCycle=0;
        state = 0;
        rectangle = new Rect();
        setRectangle(x,y);
        live = true;
        jumpFlag=false;
    }


}
