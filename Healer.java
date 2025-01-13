public class Healer extends Adventurer{
  int spirits, spiritsmax;
  public Healer(String name, int hp){
    super(name,hp);
    foodMax = 15;
    food = foodMax/2;
  }
  public String getSpecialName(){
    return "spirits";
  }
  public int getSpecial(){
    return spirits;
  }
  public void setSpecial(int n){
    spirits = n;
  }
  public int getSpecialMax(){
    return spiritsmax;
  }

}
