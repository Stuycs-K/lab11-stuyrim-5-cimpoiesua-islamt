public class Healer extends Adventurer {
  int spirits, spiritsmax;
  boolean isRegenerating, isParalyzed;
  int turnregens, turnparalyze;

  public Healer(String name, int hp) {
    super(name, hp);
    spiritsmax = 15;
    spirits = spiritsmax / 2;
  }

  public String getSpecialName() {
    return "spirits";
  }

  public int getSpecial() {
    return spirits;
  }

  public void setSpecial(int n) {
    spirits = n;
  }

  public int getSpecialMax() {
    return spiritsmax;
  }

  public String attack(Adventurer other) {
    int healing = -1 * (int) (Math.random() * 3) + 3;
    other.applyDamage(healing);
    restoreSpecial(2);
    return this + " healed " + other + " and healed " + healing * -1 +
        " health points.";
  }

  // public void regeneration()
  public String specialAttack(Adventurer other) {
    if (getSpecial() >= 12) {
      setSpecial(getSpecial() - 12);
      // regeneration(2);
      return this + " applied regeneration for two turns points of damage.";
    } else {
      return "Not enough spirits for Heal Ring. Find some more!";
    }
  }

  public String support(Adventurer other) {
    return "cleared all debuffs and restored 3 " + other.restoreSpecial(3) + " "+
    other.getSpecialName();
  }

  public String support() {
    if (Math.random() >= 0.1) {
      setHP(getHP() + 5);
      restoreSpecial(3);
      return this + " found some spirits in a field. They restored 5 HP and 3 Spirits.";
    }
    return "self-support failed :(.";
  }

  public void regeneration(Adventurer target, int turns) {
     if (turns > 0) {
      target.setHP(target.getHP() + 2);
      target.restoreSpecial(1);
      turns--;
    }
  }

  public boolean getisRegen() {
    return isRegenerating;
  }

  public boolean getisParalyzed() {
    return isParalyzed;
  }

}
