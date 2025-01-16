public class Boss extends Adventurer{
  int bats, batMax;
  boolean isRegenerating, isParalyzed;
  int turnregens, turnparalyze;

  public Boss(String name, int hp) {
    super(name, (int) 2 * hp);
    batMax = 22;
    bats = batMax / 2 + 1;
  }

  public String getSpecialName() {
    return "bats";
  }

  public int getSpecial() {
    return bats;
  }

  public void setSpecial(int n) {
    bats = n;
  }

  public int getSpecialMax() {
    return batMax;
  }

  public String attack(Adventurer other) {
      int damage = (int) (Math.random() * 5) + 3;
      other.applyDamage(damage);
      restoreSpecial(4);
      return this + " punched " + other + " and dealt " + damage +
          " points of damage.";
    }

}
