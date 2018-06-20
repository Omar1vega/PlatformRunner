package vegao1.onyxassignment4;

public class ScoreSheet {
    protected int points;

    public int addPoints(GameItem gameItem){
        MarioVisitor visitor = new MarioVisitor();
        points += gameItem.getPoints(visitor);
        return points;
    }
}
