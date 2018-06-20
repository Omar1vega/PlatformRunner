package vegao1.onyxassignment4;

public class JumpRunner implements Runnable{
    OnyxRunner onyxRunner;
    volatile MarioHero jummper;

    public JumpRunner(OnyxRunner ox,MarioHero mario){
        onyxRunner=ox;
        jummper=mario;
    }
    @Override
    public void run() {
        jummper.jump();
        int jumpHeight;
        synchronized (jummper) {
            jumpHeight = jummper.getRectangle().top -jummper.getPRINTSIZE()*3;
        }
        //go up
        while (jummper.getRectangle().top > jumpHeight) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie){}
            jummper.shiftBy(0, -2);
        }
        //go down
        boolean inAir;
        synchronized (jummper) {
            inAir =onyxRunner.inAir();
        }
        while (inAir) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {}
            synchronized (jummper) {
                jummper.shiftBy(0, +2);
                inAir = onyxRunner.inAir();
            }
        }
        synchronized (jummper) {
            //jummper.shiftBy(0, -24);
            jummper.endjump();
        }
    }
}
