package vegao1.onyxassignment4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Iterator;

import static android.graphics.Rect.intersects;

public class OnyxRunner implements Runnable {

    protected World world;
    protected LevelBuilder levelBuilder;
    protected volatile MarioHero user;
    protected ArrayList<Ground> groundList;
    protected boolean aboveFlag = true;
    protected Rect background;
    protected int currentScreen;
    protected int currentLevel;
    Iterator levelSwitch;
    public boolean gameRunning =  true;


    public OnyxRunner(World world){
        this.world=world;
        levelBuilder = new LevelBuilder(world.getImageCollect(),world.getWidth(),world.getHeight());
        user = levelBuilder.getMarioHero();
        groundList = levelBuilder.groundList();
        levelSwitch= levelBuilder.levels.iterator();
    }

    public void setGroundList(int[][] newGroundList){
        groundList = levelBuilder.groundList(newGroundList);
    }

    public MarioHero getHero(){
        return user;
    }

    public void jumpRun(){
        user.jump();
    }
    //used to land on block when done jumping
    public boolean inAir(){
        /*boolean between;
        for (Ground g : groundList){
            synchronized (user) {
                aboveFlag = !(!user.hitBottom(g) && user.isBetween(g));
            }//sync
            if(!aboveFlag){return aboveFlag;}
            //same for platforms
        }
        */
        synchronized (user) {
            user.shiftBy(0, 1);
            aboveFlag = inSolid();
            user.shiftBy(0,-1);
        }
        return !aboveFlag;
    }

    public boolean inSolid(){
        for(Ground g: groundList){
            synchronized (user) {
                if (intersects(g.getRectangle(), user.getRectangle())) {
                    return true;
                }
            }//sync
        }
        return false;
    }

    public boolean aboveDeath(){
        return user.getRectangle().bottom> (world.getHeight()-world.getHeight()/25);
    }
    public boolean atEdge(){
        return user.getRectangle().left < 10;
    }

    @Override
    public void run() {
        background  = new Rect();
        background.set(0,0,world.getWidth(),world.getHeight());
        Paint black = new Paint(Color.BLACK);
        SurfaceHolder holder = world.getHolder();
        Canvas canvas = null;

        Iterator screenSwitch = null;

        if (levelSwitch.hasNext()){
            currentLevel ++;
            ArrayList temp  = (ArrayList)(levelSwitch.next());
            screenSwitch = temp.iterator();
            screenSwitch.next();
        }
        else{
            gameRunning=false;
        }
        while (gameRunning){
            //synchronized (world) {
                canvas = holder.lockCanvas();
                //bg
                canvas.drawRect(background,black);
                //ground
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setTextSize(200);
                canvas.drawText("Level #"+currentLevel, world.getWidth() / 2, 200,paint);
                for (Ground g : groundList) {
                    if (g.getLive()){
                        g.draw(canvas);
                    }//live
                }//ground

                //System.out.println("between "+holeFlag);
            boolean holeFlag=false;
                for (Ground g: groundList){
                    if (!holeFlag) {
                        synchronized (user) {
                            user.shiftBy(0,1);
                            holeFlag = (inSolid());
                            user.shiftBy(0,-1);
                            if (holeFlag) {
                                if (!user.hitTop(g)) {
                                    holeFlag = false;
                                }
                                if (holeFlag){break;}
                            }
                        }
                    }//if true once then stay true
                }//loop to check if in hole

                if (user.getLive()){
                //jump?
                    synchronized (user) {
                        if (!user.getJumpFlag()) {
                            //fall when in hole
                            if (!holeFlag) {
                                user.shiftBy(0, +10);
                            }
                        }//jump check
                    }//sync
                }//

                //check above death
                if (aboveDeath()){
                    user.live=false;
                    break;
                }
                if(atEdge()){
                    if(screenSwitch.hasNext()){
                        int[][] temp = (int[][])(screenSwitch.next());
                        setGroundList(temp);
                        int highestBlock = 1;
                        for (int i = 0; i< temp[temp.length-1].length; i++){
                            if(temp[temp.length-1][i] ==1){
                                highestBlock = i;
                            }
                        }
                        highestBlock++;
                        Ground g = new Ground();
                        user.getRectangle().offset(world.getWidth()-user.PRINTSIZE,-(highestBlock*g.getPRINTSIZE() +user.PRINTSIZE));
                    }


                    /*if(LevelBuilder.levels.get(currentLevel).get(++currentScreen) != null){ // There are screens left in the level
                        setGroundList(LevelBuilder.levels.get(currentLevel).get(++currentScreen));
                        user.getRectangle().offset(world.getWidth()-user.PRINTSIZE,0);
                    }*/
                    else{// there are no screens left in current level, change levels
                        if(levelSwitch.hasNext()){
                            holder.unlockCanvasAndPost(canvas);
                            run();
                            //change levels
                        }
                        else{
                            //end game
                        }

                    }

                }

                if (user.getLive()) {
                    //cycle images...not working
                    user.draw(canvas);
                }//user
                //unlock canvas
                holder.unlockCanvasAndPost(canvas);
            //}//syc
        }//loop
        if (!user.getLive()) {
            Rect fin = new Rect();
            fin.set(world.getWidth() / 10,
                    world.getHeight() / 5,
                    world.getWidth() - world.getWidth() / 10,
                    world.getHeight() - world.getHeight() / 5);
            Bitmap endgame = world.getImageCollect().gameOver();
            if (null != canvas) {
                canvas.drawBitmap(endgame, null, fin, null);
            } else {
                canvas = holder.lockCanvas();
                canvas.drawBitmap(endgame, null, fin, null);
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }//run



}
//eof