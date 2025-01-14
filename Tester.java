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
    // Game.drawBackground();
    // Game.drawText("ajfjsaljf", 1, 3);
    // Game.drawText("ajfjsaljf", 2, 4);
    System.out.println("Test 1:");
    Game.TextBox(0, 0, 10, 3, "Hello World!");

    System.out.println("\nTest 2:");
    Game.TextBox(0, 0, 5, 3, "Supercalifragilisticexpialidocious");

    System.out.println("\nTest 3:");
    Game.TextBox(0, 0, 15, 4, "This is a test of the TextBox function. Make sure it works correctly.");

    System.out.println("\nTest 4:");
    Game.TextBox(0, 0, 10, 2, "1234567890 1234567890");

  }
}
