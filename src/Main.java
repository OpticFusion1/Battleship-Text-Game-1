import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static String alphabet = "ABCDEFGHIJ";

    public static void main (String args[]){

        String[][] grid = new String[10][10];
        String[][] gridE = new String[10][10]; //enemy ships visible
        String[][] gridH = new String[10][10]; //enemy ships hidden until found


        ArrayList<Ship> ships = new ArrayList<Ship>();
        ArrayList<Ship> shipsE = new ArrayList<Ship>();
        ArrayList<Coordinate> guessedCoord = new ArrayList<Coordinate>();
        ArrayList<Coordinate> guessedCoordE = new ArrayList<Coordinate>();
        ArrayList<Coordinate> correctCoordE = new ArrayList<Coordinate>();
        ArrayList<String> usedCoord = new ArrayList<String>();
        ArrayList<String> usedCoordE = new ArrayList<String>();

        Scanner scan = new Scanner(System.in);
        String input = " ";

        //Set to false to skip choosing
        boolean notDebugging = false;
        boolean choosing = notDebugging;
        boolean playing = true;
        boolean turn = false;

        int shipsLeft = 5;
        int shipsLeftE = 5;
        int enemyTurns = 0;

        String guessVal = "";

        //Fill all grids
        fillGrid(grid);
        fillGrid(gridE);
        fillGrid(gridH);

        //Skip choosing
        choosing = notDebugging;
        Ship t1 = createShip("A1,A2", grid, returnCoordinates("A1,A2", usedCoord));
        Ship t2 = createShip("B1,B3", grid, returnCoordinates("B1,B3", usedCoord));
        Ship t3 = createShip("C1,C3", grid, returnCoordinates("C1,C3", usedCoord));
        Ship t4 = createShip("D1,D4", grid, returnCoordinates("D1,D4", usedCoord));
        Ship t5 = createShip("E1,E5", grid, returnCoordinates("E1,E5", usedCoord));
        ships.add(t1);
        ships.add(t2);
        ships.add(t3);
        ships.add(t4);
        ships.add(t5);

        System.out.println("To place a ship, insert the two coordinates in this format: A1,A3");
        while(choosing){
            System.out.print("Place your ship (2 long): ");
            input = scan.nextLine();
            if(checkShip(input, 2, usedCoord)){
                Ship p1 = createShip(input, grid, usedCoord);
                ships.add(p1);
                choosing = false;
                showGrid(grid);
            }else{
                System.out.println("That ship placement is illegal");
            }
        }
        choosing = notDebugging;
        while(choosing){
            System.out.print("Place your ship (3 long): ");
            input = scan.nextLine();
            if(checkShip(input, 3, usedCoord)){
                Ship p2 = createShip(input, grid, usedCoord);
                ships.add(p2);
                choosing = false;
                showGrid(grid);
            }else{
                System.out.println("That ship placement is illegal");
            }
        }
        choosing = notDebugging;
        while(choosing){
            System.out.print("Place your ship (3 long): ");
            input = scan.nextLine();
            if(checkShip(input, 3, usedCoord)){
                Ship p3 = createShip(input, grid, usedCoord);
                ships.add(p3);
                choosing = false;
                showGrid(grid);
            }else{
                System.out.println("That ship placement is illegal");
            }
        }
        choosing = notDebugging;
        while(choosing){
            System.out.print("Place your ship (4 long): ");
            input = scan.nextLine();
            if(checkShip(input, 4, usedCoord)){
                Ship p4 = createShip(input, grid, usedCoord);
                ships.add(p4);
                choosing = false;
                showGrid(grid);
            }else{
                System.out.println("That ship placement is illegal");
            }
        }
        choosing = notDebugging;
        while(choosing){
            System.out.print("Place your ship (5 long): ");
            input = scan.nextLine();
            if(checkShip(input, 5, usedCoord)){
                Ship p5 = createShip(input, grid, usedCoord);
                ships.add(p5);
                choosing = false;
                showGrid(grid);
            }else{
                System.out.println("That ship placement is illegal");
            }
        }

        Ship e1 = randomizeEnemyShip(gridE, 2, usedCoordE);
        shipsE.add(e1);
        Ship e2 = randomizeEnemyShip(gridE, 3, usedCoordE);
        shipsE.add(e2);
        Ship e3 = randomizeEnemyShip(gridE, 3, usedCoordE);
        shipsE.add(e3);
        Ship e4 = randomizeEnemyShip(gridE, 4, usedCoordE);
        shipsE.add(e4);
        Ship e5 = randomizeEnemyShip(gridE, 5, usedCoordE);
        shipsE.add(e5);

        //Game loop
        while(playing){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Your board:");
            showGrid(grid);
//            System.out.println("Enemy Board: ");
//            showGrid(gridH);

//            System.out.println("ENEMY (DEBUG) BOARD: ");
//            showGrid(gridE);

            /**
            //Player turn
            System.out.print("\nType your guess: ");
            input = scan.nextLine();

            //miss (0), hit(1), already guessed(2), sunk(3)
            guessVal = checkGuess(input, gridE, gridH, shipsE, guessedCoord);
            if(guessVal.equals("miss")){
                System.out.println("<<You didn't hit anything>>");
            }
            if(guessVal.equals("hit")){
                System.out.println("<<You hit a ship!>>");

            }
            if(guessVal.equals("already guessed")){
                System.out.println("<<You already guessed that spot>>");

            }
            if(guessVal.equals("ship sunk")){
                System.out.println("<<You sunk an enemy ship!>>");

                shipsLeftE--;
                if(shipsLeftE == 0){
                    System.out.println("<<YOU WON THE GAME!!!>>");
                    break;
                }
            }

            System.out.println("[PRESS ENTER TO CONTINUE]");
            scan.nextLine();
            **/


            //Enemy turn

            //EnemyAI debugging loop
            for(int i = 1; i <= 5; i++){
                System.out.println("<<Turn: "+i+">>");
                enemyTurn(grid, ships, guessedCoordE, correctCoordE);
                showGrid(grid);
            }

            playing = false;
        }
    }

    public static void fillGrid(String[][] grid){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                grid[i][j] = "-";
            }
        }
    }

    public static void showGrid(String[][] grid){
        //prints numbers above cols
        System.out.print("  ");
        for(int i = 1; i <= 10; i++){
            System.out.print(i + " ");
        }
        System.out.print("\n");

        for(int i = 1; i <= 10; i++){
            //prints letters in front of rows
            System.out.print(alphabet.charAt(i-1)+" ");

            for(int j = 1; j <= 10; j++){
                System.out.print(getSymbol(grid, i, j)+" ");
            }
            System.out.println(" ");
        }
    }

    public static String getSymbol(String[][] grid, int x, int y){
        return grid[x-1][y-1];
    }

    public static void setSymbol(String[][] grid, int letter, int num, String symbol){
         grid[letter][num] = symbol;
    }

    public static String[] shipInfo(String input){
        String[] info = new String[3];

        String direction = "";
        int commaIndex = input.indexOf(",");
        String coord1 = input.substring(0, commaIndex);
        String coord2 = input.substring(commaIndex+1);

//        System.out.println("Coord1: "+coord1);
//        System.out.println("Coord2: "+coord2);
//        System.out.println("Coord1 letter: "+coord1.substring(0,1));
//        System.out.println("Coord1 num: "+coord1.substring(1));
//        System.out.println("Coord2 letter: "+coord2.substring(0,1));
//        System.out.println("Coord2 num: "+coord2.substring(1));

        //Checks if ship is horizontal or vertical
        if(coord1.substring(0,1).equals(coord2.substring(0,1))){
            direction = "horizontal";
        }else if(coord1.substring(1).equals(coord2.substring(1))){
            direction = "vertical";
        }else{
            direction = "illegal";
        }

        info[0] = coord1;
        info[1] = coord2;
        info[2] = direction;

        //coordinate 1, coordinate 2, direction
        return info;
    }

    public static boolean checkShip(String input, int length, ArrayList<String> used){
        String[] info = shipInfo(input);
        String coord1 = info[0];
        String coord2 = info[1];

        int checkLength = 0;

        //Checks if the ship is the right length
        if(info[2].equals("illegal")){
            //System.out.println("Illegal ship");
            return false;
        }
        if(info[2].equals("horizontal")){
            checkLength = Math.abs(Integer.parseInt(coord1.substring(1)) - Integer.parseInt(coord2.substring(1))) + 1;
            //System.out.println("checkLength: "+checkLength);
            //System.out.println("length: "+length);
            if(checkLength != length){
                return false;
            }
        }
        if(info[2].equals("vertical")){
            checkLength = Math.abs(alphabet.indexOf(coord1.substring(0,1))-alphabet.indexOf(coord2.substring(0,1))) + 1;
            //System.out.println("checkLength: "+checkLength);
            //System.out.println("length: "+length);
            if(checkLength != length){
                return false;
            }
        }

        if(!checkUsed(input, used)){
            //System.out.println("**usedCoord collision**");
            return false;
        }

        return true;
    }

    public static boolean checkUsed(String input, ArrayList<String> used){
        ArrayList<String> coordinates = returnCoordinates(input, used);
        ArrayList<String> originalUsed = used;

        //System.out.println("CHECKUSED CALLED");

        if(used.size() == 0){
            //System.out.println("used.size(): "+used.size());
            for(String x : coordinates){
                used.add(x);
//                System.out.println("Used: "+used);
//                System.out.println("Check: "+coordinates);
            }
            return true;
        }else{
            //System.out.println("used.size(): "+used.size());
            for(int i = 0; i < coordinates.size(); i++){
                for(int j = 0; j < used.size(); j++){
//                    System.out.println("Used: "+used);
//                    System.out.println("Check: "+coordinates);
                    if(coordinates.get(i).equals(used.get(j))){
                        used = originalUsed;
                        return false;
                    }
                }
            }
            for(String x : coordinates){
                used.add(x);
//                System.out.println("Used: "+used);
//                System.out.println("Check: "+coordinates);
            }
        }

        return true;

    }

    public static Ship createShip(String input, String[][] grid, ArrayList<String> used){
        String[] info = shipInfo(input);
        ArrayList<String> coordinates = returnCoordinates(input, used);

        //System.out.println("Coordinates: "+coordinates);

        //Update the grid to show the ship
        for(String x : coordinates){
            setSymbol(grid, alphabet.indexOf(x.substring(0,1)), Integer.parseInt(x.substring(1))-1, "@");
            //System.out.println("Symbol has been set");
        }

        return new Ship(info[2], coordinates);
    }

    public static Ship randomizeEnemyShip(String[][] grid, int length, ArrayList<String > used){
        boolean randomizing = true;
        int randLetter = 0;
        int randNum = 0;
        String randDir = "up";

        //Pick a random coordinate
        while(randomizing){
            int[] randomCoordinate = randomCoordinate();

            Coordinate randCoord = new Coordinate(randomCoordinate[0], randomCoordinate[1]);

            randLetter = randomCoordinate[0];
            randNum = randomCoordinate[1];

            boolean checkPassed = false;

            ArrayList<String> possibleDirections = randCoord.getDirections(randLetter, randNum);

//        System.out.println("randLetter: "+randLetter);
//        System.out.println("randNum: "+randNum);
//        System.out.println("possibleDirections: "+possibleDirections);

            randDir = possibleDirections.get((int)(Math.random() * possibleDirections.size()));

            if(randDir.equals("up")){
                //System.out.println("math: "+(randLetter - length + 1 >= 0));
                if(randLetter - length + 1 >= 0){
                    //System.out.println("CHECKUSED CALLED: up");
                    String input = getInput(randLetter, randNum, randDir, length);
                    checkPassed = checkShip(input, length, used);
                }
            }
            if(randDir.equals("down")){
                //System.out.println("math: "+(randLetter + length - 1 <= 9));
                if(randLetter + length - 1 <= 9){
                    //System.out.println("CHECKUSED CALLED: down");
                    String input = getInput(randLetter, randNum, randDir, length);
                    checkPassed = checkShip(input, length, used);
                }
            }
            if(randDir.equals("left")){
                //System.out.println("math: "+(randNum - length + 1));
                if(randNum - length + 1 >= 0){
                    //System.out.println("CHECKUSED CALLED: left");
                    String input = getInput(randLetter, randNum, randDir, length);
                    checkPassed = checkShip(input, length, used);
                }
            }
            if(randDir.equals("right")){
                //System.out.println("math: "+(randNum + length - 1 <= 9));
                if(randNum + length - 1 <= 9){
                    //System.out.println("CHECKUSED CALLED: right");
                    String input = getInput(randLetter, randNum, randDir, length);
                    checkPassed = checkShip(input, length, used);
                }
            }

            if(checkPassed){
                //System.out.println("<<Check passed>>");
                randomizing = false;
            }else{
                //System.out.println("<<Check failed>>");
            }
        }

        return createShip(getInput(randLetter, randNum, randDir, length), grid, used);
    }

    public static ArrayList<String> returnCoordinates(String input, ArrayList<String> used){
        String[] info = shipInfo(input);
        ArrayList<String> coordinates = new ArrayList<String>();

        //Add all coordinates of the ship
        if(info[2].equals("horizontal")){
            //System.out.println("horizontal");
            String letter = info[0].substring(0,1);
            int num1 = Integer.parseInt(info[0].substring(1));
            int num2 = Integer.parseInt(info[1].substring(1));

            int smallNum = num1;
            int bigNum = num2;

            if(num1 > num2){
                smallNum = num2;
                bigNum = num1;
            }

            for(int i = smallNum; i <= bigNum; i++){
                coordinates.add(letter+i);
            }
            //System.out.println(coordinates);
        }

        if(info[2].equals("vertical")){
            //System.out.println("vertical");
            int letter1 = alphabet.indexOf(info[0].substring(0,1));
            int letter2 = alphabet.indexOf(info[1].substring(0,1));
            int num = Integer.parseInt(info[0].substring(1));

            int smallLetter = letter1;
            int bigLetter = letter2;

            if(letter1 > letter2){
                smallLetter = letter2;
                bigLetter = letter1;
            }

            for(int i = smallLetter; i <= bigLetter; i++){
                coordinates.add(Character.toString(alphabet.charAt(i))+num);
            }
            //System.out.println(coordinates);
        }

        return coordinates;
    }

    public static int[] randomCoordinate(){
        int[] randCoordinate = new int[2];

        //randLetter, randNum, direction
        randCoordinate[0] = (int)(Math.random() * 9);
        randCoordinate[1] = (int)(Math.random() * 9);


        return randCoordinate;
    }

    public static String getInput(int randLetter, int randNum, String randDir, int length){
        String input = "";
        String letter1 = alphabet.substring(randLetter, randLetter+1);
        String letter2 = "";
        int num1 = randNum+1;
        int num2 = 0;
        String direction = "";

//        System.out.println("Random direction: "+randDir);
//        System.out.println("Length: "+ length);

        if(randDir.equals("up")){
            letter2 = alphabet.substring(randLetter-length+1,(randLetter-length+1)+1);
            num2 = num1;
        }
        if(randDir.equals("down")){
            letter2 = alphabet.substring(randLetter+length-1,(randLetter+length-1)+1);
            num2 = num1;
        }
        if(randDir.equals("right")){
            letter2 = letter1;
            num2 = num1+length-1;
        }
        if(randDir.equals("left")){
            letter2 = letter1;
            num2 = num1-length+1;
        }

        //System.out.println("getInput(): "+letter1+num1+","+letter2+num2);

        return letter1+num1+","+letter2+num2;
    }

    public static String checkGuess(String guess, String[][] gridE, String[][] gridH, ArrayList<Ship> ships, ArrayList<Coordinate> gCoord){

        guess = guess.toUpperCase();

        int letter = alphabet.indexOf(guess.substring(0,1));
        int num = Integer.parseInt(guess.substring(1));

        System.out.println("Letter: "+letter);
        System.out.println("Num: "+num);
        System.out.println("Guess: "+guess);
//        System.out.println("# of ships: "+ships.size());

        for(Coordinate x : gCoord){
            //System.out.println("guess = gCord: "+guess.equals(x));
            if(guess.equals(x.getName())){
                return "already guessed";
            }
        }

//        System.out.println(guess);
//        System.out.println("gCoord: "+gCoord);

        gCoord.add(new Coordinate(guess));

        for(Ship x : ships){
            for(String y : x.getCoordinates()){
                //System.out.println("Coordinate: "+ y);
                if(guess.equals(y)){
                    setSymbol(gridH, letter, num-1, "x");
                    x.getCoordinates().remove(y);

                    if(x.sunk()){
                        return "ship sunk";

                    }

                    return "hit";

                }else{
                    setSymbol(gridH, letter, num-1, "o");
                }
            }
        }

        return "miss";
    }

    public static String enemyTurn(String[][] grid, ArrayList<Ship> ships, ArrayList<Coordinate> gCoord, ArrayList<Coordinate> cCoord){
        String randDir;
        String guess = "";
        String guessVal = "";

        boolean guessing = true;

        ArrayList<String> directions = new ArrayList<String>();

        int letter;
        int num;

        System.out.println("cCoord.size(): "+cCoord.size());
        System.out.print("gCoord: ");
        for(Coordinate x : gCoord){
            System.out.print(x.getName()+", ");
        }
        System.out.println("\n");

        //Guess randomly until a ship is found
        if(cCoord.size() == 0){
            Coordinate randCoord = new Coordinate();
            while(guessing){
                guessing = false;

                System.out.println("yes");

                int[] randomCoordinate = randomCoordinate();

                randCoord = new Coordinate(randomCoordinate[0], randomCoordinate[1]+1);
                guess = randCoord.getName();

                for(Coordinate x : gCoord){
                    if(guess.equals(x.getName())){
                        guessing = true;
                    }
                }
            }

            guessVal = checkGuess(guess, grid, grid, ships, gCoord);

//            System.out.println(randomCoordinate[0]+", "+randomCoordinate[1]);
//            System.out.println("name: "+randCoord.getName());
//            System.out.println("letter: "+randCoord.getLetter());
//            System.out.println("num: "+randCoord.getNum());

            System.out.println("Enemy randGuess: "+guess);

            if(guessVal.equals("hit")){
                cCoord.add(randCoord);
            }

        }else{
//            while(guessing){
//
//            }
            Coordinate c = cCoord.get((int)(Math.random() * cCoord.size()));
            Coordinate g;
            letter = c.getLetter();
            num = c.getNum();

            directions = c.getDirections(letter, num-1);
            randDir = directions.get((int)(Math.random() * directions.size()));

            int newLetter = 0;
            int newNum = 0;

            System.out.println("OG coord: "+c.getName());
            System.out.println("Directions: "+directions);

            if(randDir.equals("up")){
                System.out.println("up");

                newLetter = c.getLetter() - 1;
                newNum = c.getNum();

                g = new Coordinate(newLetter, newNum);
            }
            if(randDir.equals("down")){
                System.out.println("down");

                newLetter = c.getLetter() + 1;
                newNum = c.getNum();
            }
            if(randDir.equals("left")){
                System.out.println("left");

                newLetter = c.getLetter();
                newNum = c.getNum() - 1;
            }
            if(randDir.equals("right")){
                System.out.println("right");

                newLetter = c.getLetter();
                newNum = c.getNum() + 1;
            }

            g = new Coordinate(newLetter, newNum);
            guess = g.getName();

            System.out.println("Enemy educatedGuess: "+guess);
            guessVal = checkGuess(guess, grid, grid, ships, gCoord);

            if(guessVal.equals("hit")){
                cCoord.add(g);
            }
        }

        return guessVal;
    }
}
