public class Tester {
  public static void main(String[] args) {
    Text.clear();
    // Testing coloring
    /*
     * System.out.println(Game.colorByPercent(3,4));
     * System.out.println(Game.colorByPercent(4,4));
     * System.out.println(Game.colorByPercent(2,4));
     * System.out.println(Game.colorByPercent(1,5));
     */
    Game.drawBackground();
    // Game.drawText("ajfjsaljf", 1, 3);
    // Game.drawText("ajfjsaljf", 2, 4);

    Game.TextBox(2, 2, 10, 3, "This should get cut off and be fully formatted");
    Text.go(32,1);

  }
}
