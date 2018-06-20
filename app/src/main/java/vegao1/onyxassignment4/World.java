package vegao1.onyxassignment4;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class World extends SurfaceView implements SurfaceHolder.Callback {
    protected ImageCollect imageCollect;
    protected volatile MarioHero player1;
    protected OnyxRunner onyxRunner;
    protected volatile JumpRunner jumpRunner;
    protected Thread jumpThread;
    private int moveSize = 50;
    private int numSteps = 0;

    private Ground  dg = new Ground();
    public World(Context context){
        super(context);
        imageCollect = new ImageCollect(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public ImageCollect getImageCollect() {return imageCollect;}



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        onyxRunner = new OnyxRunner(this);
        player1 = onyxRunner.getHero();
        Thread gameThread = new Thread(onyxRunner);
        gameThread.start();
        jumpRunner = new JumpRunner(onyxRunner,player1);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                int userx = (int)e.getX();
                int usery = (int)e.getY();
                if(userx<getWidth()/2){
                    //left
                    synchronized (player1){
                        if(!player1.getJumpFlag()) {
                            player1.setLeft();
                        }
                        //dont move into walls
                        player1.shiftBy(-moveSize,0);
                        synchronized (player1) {
                            if (onyxRunner.inSolid()) {
                                player1.shiftBy(moveSize, 0);
                            }
                        }//sync
                    }//sync
                    numSteps++;
                    /*if(numSteps>((getWidth()/moveSize))) {*/
                    /*if(player1.atEdge) {
                        numSteps = 0;
                        onyxRunner.setGroundList(LevelBuilder.gbluprint2);
                        int jkf = LevelBuilder.gbluprint2[LevelBuilder.gbluprint2.length-2].length * dg.getPRINTSIZE()+ 2*player1.getPRINTSIZE();
                        player1.setRectangle(getWidth(),jkf *//*getHeight()-(5*dg.getPRINTSIZE())-player1.getPRINTSIZE()*//*);
                    }*/
                }
                else {
                    //right
                    synchronized (player1){
                        if (!player1.getJumpFlag()) {
                             player1.setRight();
                        }
                        //dont move into walls
                        player1.shiftBy(moveSize,0);
                        synchronized (player1) {
                            if (onyxRunner.inSolid()) {
                                player1.shiftBy(-moveSize, 0);
                            }
                        }//sync
                    }//sync
                }
                if(usery<getHeight()/2){
                    boolean j;
                    synchronized (player1) {
                        j = player1.getJumpFlag();
                        if (!j) {
                            jumpThread = new Thread(jumpRunner);
                            jumpThread.start();
                        }
                    }
                }
        }//end switch
        return true;
    }//ontouch

}//eof
