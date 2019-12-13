import java.util.ArrayList;

public class Tuple {
    int X;
    int Y;
    String word;
    boolean isItHorizantol;
    ArrayList<Tuple> dependendies;

    public Tuple(int x, int y, String str, boolean truth){
        this.X=x; this.Y=y; this.word=str; this.isItHorizantol=truth;
        dependendies = new ArrayList<>();
    }

    private boolean isDependent(Tuple tuple){
        if(this.X >= tuple.getX() && this.X < tuple.getX()+tuple.getWord().length())
            return true;
        return false;
    }

    public void addDependency(Tuple tuple){
        if(isDependent(tuple))
            dependendies.add(tuple);
    }
    public void displayDependencies(){
        for (int i=0;i<dependendies.size();i++){
            System.out.println(dependendies.get(i).word);
        }
    }

    public Boolean getItHorizantol() {
        return isItHorizantol;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public String getWord() {
        return word;
    }

    public void setItHorizantol(Boolean itHorizantol) {
        isItHorizantol = itHorizantol;
    }

    public void setWord(String str) {
        this.word = str;
    }

    public void setX(int x) {
        X = x;
    }

    public void setY(int y) {
        Y = y;
    }
}
