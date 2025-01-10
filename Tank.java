public class Tank extends Adventurer{
  int food, foodMax;
  public Tank(String name, int hp){
    super(name,hp);
    foodMax = 18;
    caffeine = caffeineMax/2 + 1;
  }
  public String getSpecialName(){
    return "food";
  }
  public String getSpecial(){
    return food;
  }
  public void setSpecial(int n){
    food = n;
  }
  public int getSpecialMax(){
    return foodMax;
  }
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*3)+2;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " punched "+ other + " and dealt "+ damage +
    " points of damage.";
  }
}
