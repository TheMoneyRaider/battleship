import java.util.*;
public class Battleship {
    int length;
    int[] segs;
    public boolean alive;
    Random random=new Random();
    public Battleship(int l){
      this.length=l;
      this.segs = new int[l];
      this.alive = true;
    }
    public int getLength(){
        return this.length;
    }
    public void setPos(int place){
      for(int i=this.segs.length-1;i>=0;i--){
        this.segs[i] = place+i;
      }
    }
    public void setPosRan(int size){
      int place = random.nextInt(size-this.length);
        for(int i=this.segs.length-1;i>=0;i--){
        this.segs[i] = place+i;
      }
    }
    public void setNPos(int i, int b){
        this.segs[i]=b;
    }
    public int getPos(int i){
    return this.segs[i];
    }
    public boolean getLife(){
        return this.alive;
    }
    public void die(){
        this.alive =false;
    }   
    public int[] getSegs(){
        return segs;
    }
}