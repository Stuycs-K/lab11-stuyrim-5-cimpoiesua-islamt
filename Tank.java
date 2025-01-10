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
}
