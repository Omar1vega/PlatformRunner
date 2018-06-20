package vegao1.onyxassignment4;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

public class ImageCollect extends SurfaceView{
    public ImageCollect(Context context){
        super(context);
    }
    public Bitmap[] userDead(){
        Bitmap[] user = new Bitmap[3];
            user[0]= BitmapFactory.decodeResource(getResources(),
                    R.drawable.player1_dead1);
            user[1]=BitmapFactory.decodeResource(getResources(),
                    R.drawable.player1_dead2);
            user[2]=BitmapFactory.decodeResource(getResources(),
                    R.drawable.player1_dead3);
        return user;
    }
    public Bitmap[] userJump(){
        Bitmap[] user = new Bitmap[4];
        user[0]=BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_jump1);
        user[1] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_jump2);
        user[2] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_jump3);
        user[3] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_jump4);
        return user;
    }
    public  Bitmap[] userLeft(){
        Bitmap[] user = new Bitmap[2];
        user[0]=BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_left1);
        user[1] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_left2);
        return user;
    }
    public Bitmap[] userRight(){
        Bitmap[] user = new Bitmap[2];
        user[0]=BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_right1);
        user[1] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player1_right2);
        return user;
    }
    public Bitmap ground(){
        return BitmapFactory.decodeResource(getResources(),
                R.drawable.ground);
    }
    public Bitmap[] gamecontrol(){
        Bitmap[] user = new Bitmap[4];
        user[0]=BitmapFactory.decodeResource(getResources(),
                R.drawable.control_fire);
        user[1] = BitmapFactory.decodeResource(getResources(),
                R.drawable.control_jump);
        user[2] = BitmapFactory.decodeResource(getResources(),
                R.drawable.control_left);
        user[3] = BitmapFactory.decodeResource(getResources(),
                R.drawable.control_right);
        return user;
    }
    public Bitmap gameOver(){
        return BitmapFactory.decodeResource(getResources(),
                R.drawable.gameover);
    }

}
