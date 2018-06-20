package vegao1.onyxassignment4;

public class Item extends OxBlock implements GameItem{
    protected int value;

    public int getValue(){return value;}

    public int getPoints(MarioVisitor visitor){
        return visitor.visit(this);
    }
}
