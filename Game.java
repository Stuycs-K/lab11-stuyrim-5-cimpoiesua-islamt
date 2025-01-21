import java.util.*;

public class Game {
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;
  private static ArrayList<Adventurer> enemies = new ArrayList<Adventurer>();
  private static ArrayList<Adventurer> party = new ArrayList<>();

  public static void main(String[] args) {
    run();
  }

  // Display the borders of your screen that will not change.
  // Do not write over the blank areas where text will appear or parties will
  // appear.
  public static void drawBackground() {
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    Text.hideCursor();
    Text.clear();
    for (int i = 1; i < 81; i++) {
      Text.go(1, i);
      System.out.print("-");
    }
    for (int i = 2; i < HEIGHT + 1; i++) {
      Text.go(i, 1);
      System.out.print("|");
      Text.go(i, 80);
      System.out.print("|");
    }
    Text.go(1, HEIGHT);
    for (int i = 2; i < 80; i++) {
      Text.go(HEIGHT - 4, i);
      System.out.print("-");
      Text.go(5, i);
      System.out.print("-");
      Text.go(9, i);
      System.out.print("-");
      Text.go(HEIGHT, i);
      System.out.print("-");
    }

    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
  }

  // Display a line of text starting at
  // (columns and rows start at 1 (not zero) in the terminal)
  // use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s, int startRow, int startCol) {
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    Text.go(startRow, startCol);
    System.out.print(s);

    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
  }

  /*
   * Use this method to place text on the screen at a particular location.
   * When the length of the text exceeds width, continue on the next line
   * for up to height lines.
   * All remaining locations in the text box should be written with spaces to
   * clear previously written text.
   *
   * @param row the row to start the top left corner of the text box.
   *
   * @param col the column to start the top left corner of the text box.
   *
   * @param width the number of characters per row
   *
   * @param height the number of rows
   */
  public static void TextBox(int row, int col, int width, int height, String text) {
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    // Clear the entire text area
    for (int i = 0; i < height; i++) {
      drawText(" ".repeat(width), row + i, col);
    }

    // word split
    String[] words = text.split(" ");
    int currentRow = 0;
    String currentLine = "";

    // length checking
    for (String word : words) {
      if (!currentLine.isEmpty() && currentLine.length() + 1 + word.length() <= width) {
        currentLine += " " + word;
      } else if (currentLine.isEmpty() && word.length() <= width) {
        currentLine = word;
      } else {
        drawText(currentLine, row + currentRow, col);
        currentRow++;
        if (currentRow >= height)
          return;
        if (word.length() <= width) {
          currentLine = word;
        } else {
          currentLine = "";
        }
      }
    }

    // printer
    if (currentRow < height && !currentLine.isEmpty()) {
      drawText(currentLine, row + currentRow, col);
    }

    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
  }

  // return a random adventurer (choose between all available subclasses)
  // feel free to overload this method to allow specific names/stats.
  public static Adventurer createRandomAdventurer() {
    if (Math.random() <= 0.33) {
      return new CodeWarrior("Bob" + (int) (Math.random() * 100), 100);
    } else if (Math.random() > 0.67) {
      return new Healer("Bob" + (int) (Math.random() * 100), 100);
    } else {
      return new Tank("Bob" + (int) (Math.random() * 100), 170);
    }
  }

  /*
   * Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
   * Should include Name HP and Special on 3 separate lines.
   * Note there is one blank row reserved for your use if you choose.
   * jjkjlfa;fjsdj;lk
   * Format:
   * Bob Amy Jun
   * HP: 10 HP: 15 HP: 19
   * Caffeine: 20 Mana: 10 Snark: 1
   * ***THIS ROW INTENTIONALLY LEFT BLANK***
   */
  public static void drawParty(ArrayList<Adventurer> party, int startRow) {

    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    int columnWidth = (WIDTH - 2) / party.size();

    for (int i = 0; i < party.size(); i++) {
      Adventurer member = party.get(i);

      // spacer
      int colPosition = (i * columnWidth) + 2;

      // printer
      drawText(String.format("%-" + columnWidth + "s", member.getName()), startRow, colPosition);
      drawText(String.format("%-" + columnWidth + "s", "HP: " + colorByPercent(member.getHP(), member.getmaxHP())),
          startRow + 1, colPosition);
      drawText(String.format("%-" + columnWidth + "s", member.getSpecialName() + ": " + member.getSpecial()),
          startRow + 2, colPosition);
    }
    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */
  }

  // Use this to create a colorized number string based on the % compared to the
  // max value.
  public static String colorByPercent(int hp, int maxHP) {
    String output = String.format("%2s", hp + "") + "/" + String.format("%2s", maxHP + "");
    if (((double) hp / (double) maxHP) < .25) {
      output = "\u001b[31m" + output + "\u001b[39m";
    } else {
      if (((double) hp / (double) maxHP) < .75) {
        output = "\u001b[33m" + output + "\u001b[39m";
      } else {
        output = "\u001b[37m" + output + "\u001b[39m";
      }
    }
    // COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    return output;
  }

  // Display the party and enemies
  // Do not write over the blank areas where text will appear.
  // Place the cursor at the place where the user will by typing their input at
  // the end of this method.
  public static void drawScreen() {
    Text.reset();
    drawBackground();
    

    // draw player party
    drawParty(party, 6);

    // draw enemy party
    drawParty(enemies, 2);
    Text.go(28, 2);
    Text.showCursor();

  }

  public static String userInput(Scanner in){
    //Move cursor to prompt location
    //show cursor

    //Read user input
    String input = in.nextLine();
    //After you must clear the text that was written

    return input;
}

  public static void quit() {
    Text.reset();
    Text.showCursor();
    Text.go(32, 1);
  }

  public static void run() {
    // Clear and initialize
    Text.hideCursor();
    Text.clear();

    // Things to attack:
    // Make an ArrayList of Adventurers and add 1-3 enemies to it.
    // If only 1 enemy is added it should be the boss class.
    // start with 1 boss and modify the code to allow 2-3 adventurers later.
    enemies.clear();
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    // YOUR CODE HERE
    int enemyCount = (int) (Math.random() * 3) + 1;
    if (enemyCount == 1) {
      enemies.add(new Boss("Boss" + (int) (Math.random() * 100), 200));
    } else {
      for (int i = 0; i < enemyCount; i++) {
        enemies.add(createRandomAdventurer());
      }
    }
    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

    // Adventurers you control:
    // Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    party.clear();
    /* >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */
    int partysize = (int) (Math.random() * 3) + 2;
    for (int i = 0; i < partysize; i++) {
      party.add(createRandomAdventurer());
    }
    /* <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< */

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = ""; // blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    String result = "";
    String d1 = "";
    String d2 = "";
    String d3 = "";
    int actionOn = 0;

    // Draw the window border

    // You can add parameters to draw screen!
    drawScreen(); // initial state.

    // Main loop

    // display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit";
    
    TextBox(27,2,75,1,preprompt);

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      Text.go(29,2);
      Text.showCursor();
      //Read user input
      input = userInput(in);
      Text.go(29,2);
      for(int i = 0; i < 78; i++){
        System.out.print(" ");
      }

      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.startsWith("attack ") || input.startsWith("a ")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          try{
            actionOn = Integer.parseInt(input.substring(input.length()-1));
            if(actionOn >= enemies.size()){
              result = "Invalid target. Choose an enemy between 0 and " + (enemies.size() - 1) + ".");
            }
            else{
              result = (party.get(whichPlayer)).attack(enemies.get(actionOn));
              if((enemies.get(actionOn)).getHP() <= 0){
                enemies.remove(actionOn);
              }
            }
          }catch(NumberFormatException ex){
            result = "Who's your target? Use 'attack (target_index)'.";
          }
          
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("special ") || input.startsWith("sp ")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          try{
            actionOn = Integer.parseInt(input.substring(input.length()-1));
            if(actionOn >= enemies.size()){
              result = "Invalid target. Choose an enemy between 0 and " + (enemies.size() - 1) + ".";
            }
            else{
              result = (party.get(whichPlayer)).specialAttack(enemies.get(actionOn));
              if((enemies.get(actionOn)).getHP() <= 0){
                enemies.remove(actionOn);
              }
            }
          }catch(NumberFormatException ex){
            result = "Your target has to be a number. Use 'apecial (target_index)'.";
          }
          
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          try{
            actionOn = Integer.parseInt(input.substring(input.length()-1));
            if(actionOn >= party.size()){
              result = "Invalid target. Choose an ally between 0 and " + (party.size() - 1) + ".";
            }
            else{
              if(actionOn == whichPlayer){
                result = (party.get(whichPlayer)).support();
              }
              else{
                result = (party.get(whichPlayer)).support(party.get(actionOn));
              }
            }
          }catch(NumberFormatException ex){
            result = "Invalid target. Choose an ally between 0 and \" + (party.size() - 1) + \".";
          }
          
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("su") || input.equals("support")){
          result = (party.get(whichPlayer)).support();
        }
        else if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
          break;
        }
        else{
          result = "What even is that? Use 'attack', 'special', or 'support'.";
        }
        d1 = d2;
        d2 = d3;
        d3 = result;
        TextBox(16,2,75,3,d1);
        TextBox(19,2,75,3,d2);
        TextBox(22,2,75,3,d3);
        if(enemies.size() == 0){
          break;
        }
        //You should decide when you want to re-ask for user input
        //If no errors:
        if(!(result.equals("You must include attack/special/support or a/sp/su followed by the player ID of the player you want to perform the action on. Please try again."))){
          whichPlayer++;
        }

        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit";
          TextBox(27,2,75,1,prompt);


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";
          TextBox(27,2,75,1,prompt);
          partyTurn = false;
          whichOpponent = 0;
        }
        drawParty(party,4);
        drawParty(enemies,11);
        drawScreen();
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        
        
        int whichAction = (int)(3*Math.random())+1;
        if(whichAction == 1){
          actionOn = (int)(Math.random()*party.size());
          result = (enemies.get(whichOpponent)).attack(party.get(actionOn));
          if((party.get(actionOn)).getHP() <= 0){
            party.remove(actionOn);
          }
        }
        else if(whichAction == 2){
          actionOn = (int)(Math.random()*party.size());
          result = (enemies.get(whichOpponent)).specialAttack(party.get(actionOn));
          if((party.get(actionOn)).getHP() <= 0){
            party.remove(actionOn);
          }
        }
        else if(whichAction == 3){
          actionOn = (int)(Math.random()*enemies.size());
          if(actionOn == whichOpponent){
            result = (enemies.get(whichOpponent)).support();
          }
          else{
            result = (enemies.get(whichOpponent)).support(enemies.get(actionOn));
          }
        }
        d1 = d2;
        d2 = d3;
        d3 = result;
        TextBox(16,2,75,3,d1);
        TextBox(19,2,75,3,d2);
        TextBox(22,2,75,3,d3);
        if(party.size() == 0){
          break;
        }
        String prompt = "press enter to see next turn";
        if(whichOpponent < enemies.size()){
          TextBox(27,2,75,1,prompt);
        }
        whichOpponent++;

        drawParty(party,4);
        drawParty(enemies,11);
        drawScreen();

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/support/quit";
        TextBox(27,2,75,1,prompt);
      }

      //display the updated screen after input has been processed.
      drawScreen();


    }//end of main game loop
    drawParty(party,4);
    drawParty(enemies,11);
    drawScreen();

    if(enemies.size() == 0){
      TextBox(27,2,75,1,"All enemies have died. You win!");
    }
    if(party.size() == 0){
      TextBox(27,2,75,1,"All members of your party have died. You lose.");
    }

    //After quit reset things:
    quit();
  }
}