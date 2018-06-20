package vegao1.onyxassignment4;

import android.sax.EndElementListener;

public class MarioVisitor {
    public int visit(Enemy enemy){return enemy.getReward();}
    public int visit(Item item){
        return item.getValue();
    }
}
