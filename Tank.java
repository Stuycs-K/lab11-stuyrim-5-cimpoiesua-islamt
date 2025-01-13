public class Tank extends Adventurer{
  int food, foodMax;
  public Tank(String name, int hp){
    super(name,hp);
    foodMax = 18;
    food = foodMax/2 + 1;
  }
  public String getSpecialName(){
    return "food";
  }
  public int getSpecial(){
    return food;
  }
  public void setSpecial(int n){
    food = n;
  }
  public int getSpecialMax(){
    return foodMax;
  }
  public String attack(Adventurer other){
    if(Math.random() >= 0.1){
    int damage = (int)(Math.random()*3)+2;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " punched "+ other + " and dealt "+ damage +
    " points of damage.";
    }
    else {
      return this + " attack on " + other + " failed! Better luck next time.";
    }
  }
  public String specialAttack(Adventurer other){
  if(getSpecial() >= 15){
    setSpecial(getSpecial()-15);
    int damage = (int)(Math.random()*2)+12;
    other.applyDamage(damage);
    return this + " body slammed on "+other+
    " dealing "+ damage +" points of damage.";
    }else{
      return "Not enough food to body slam. Get to eating!"+attack(other);
    }
  }
  public String support(Adventurer other){
    return "Inspired "+other+" and restores "
    + other.restoreSpecial(2)+" "+other.getSpecialName();
  }
  public String support(){
    setHP(getHP() + 3);
    restoreSpecial(4);
    return this + " ate enough food that 3 hp was restored. " + "4 " + getSpecialName()+ " was also restored";
  }
}
