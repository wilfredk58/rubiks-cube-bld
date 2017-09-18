import java.util.*;

public class InspectEdges {

    HashMap<Character,String> letterLocations;
    HashMap<String,Character> solvedState;
    int[][][] cubeInput;

    public InspectEdges() {

    }

    private HashMap<Character,String> generateLetterLocations(HashMap<Character,String> letterLocations) {
        letterLocations = new HashMap<Character,String>();
        // 'a' -> face 1, index [0][1]
        letterLocations.put('a', "001");
        letterLocations.put('b', "101");
        letterLocations.put('c', "012");
        letterLocations.put('d', "201");
        letterLocations.put('e', "021");
        letterLocations.put('f', "301");
        letterLocations.put('g', "010");
        letterLocations.put('h', "401");
        
        letterLocations.put('i', "410");
        letterLocations.put('j', "112");
        letterLocations.put('k', "110");
        letterLocations.put('l', "212");
        letterLocations.put('m', "210");
        letterLocations.put('n', "312");
        letterLocations.put('o', "310");
        letterLocations.put('p', "412");
        
        letterLocations.put('q', "121");
        letterLocations.put('r', "521");
        letterLocations.put('s', "221");
        letterLocations.put('t', "512");
        letterLocations.put('u', "321");
        letterLocations.put('v', "501");
        letterLocations.put('w', "421");
        letterLocations.put('x', "510");
        
        return letterLocations;
    }

    private HashMap<String,Character> generateSolvedState(HashMap<String,Character> solvedState) {
        solvedState = new HashMap<String,Character>();
        // solvedState.get(buffer) returns letter of where to shoot to
        solvedState.put("0,1", 'a');
        solvedState.put("1,0", 'b');
        solvedState.put("0,2", 'c');
        solvedState.put("2,0", 'd');
        solvedState.put("0,3", 'e');
        solvedState.put("3,0", 'f');
        solvedState.put("0,4", 'g');
        solvedState.put("4,0", 'h');
        solvedState.put("4,1", 'i');
        solvedState.put("1,4", 'j');
        solvedState.put("1,2", 'k');
        solvedState.put("2,1", 'l');
        solvedState.put("2,3", 'm');
        solvedState.put("3,2", 'n');
        solvedState.put("3,4", 'o');
        solvedState.put("4,3", 'p');
        solvedState.put("1,5", 'q');
        solvedState.put("5,1", 'r');
        solvedState.put("2,5", 's');
        solvedState.put("5,2", 't');
        solvedState.put("3,5", 'u');
        solvedState.put("5,3", 'v');
        solvedState.put("4,5", 'w');
        solvedState.put("5,4", 'x');

        return solvedState;
    }

    private int[][][] generateCube(String input) {
        if (input.length() != 54) throw new IllegalArgumentException("Not a valid 3x3");

        int[][][] cubeInput = new int[6][3][3];
        int i=0;

        for (int a=0; a<6; a++)
            for (int b=0; b<3; b++)
                for(int c=0; c<3; c++) {
                    char ch = input.charAt(i++);
                    cubeInput[a][b][c] = Integer.valueOf(Character.getNumericValue(ch));
                }

        return cubeInput;
    }

    ArrayList<Character> inspect(HashMap<Character,String> letterLocations, HashMap<String,Character> solvedState, int[][][] inputCube) {
        ArrayList<Character> edges = new ArrayList<Character>();
        String bfr1 = "501";
        String bfr2 = "321";

        for (int i=0; i<12; i++) {
            String bfr = inputCube[Character.getNumericValue(bfr1.charAt(0))][Character.getNumericValue(bfr1.charAt(1))][Character.getNumericValue(bfr1.charAt(2))] + ","
                + inputCube[Character.getNumericValue(bfr2.charAt(0))][Character.getNumericValue(bfr2.charAt(1))][Character.getNumericValue(bfr2.charAt(2))];
            char letter1 = solvedState.get(bfr);
            edges.add(letter1);

            bfr1 = letterLocations.get(letter1);
            char letter2 = letter1 % 2 == 1 ? (char) (letter1 + 1) : (char) (letter1 - 1);
            bfr2 = letterLocations.get(letter2);
            System.out.println(bfr1 + "," + bfr2);
        }
        
        for (Character c : edges)
            System.out.print(c + "-");
        System.out.println();


        return edges;
    }
    
    public static void main(String[] args) {
        InspectEdges test1 = new InspectEdges();
        test1.letterLocations = test1.generateLetterLocations(test1.letterLocations);
        test1.solvedState = test1.generateSolvedState(test1.solvedState);

        test1.cubeInput = test1.generateCube("220001250411513122201424445133431055035243524313551304");
        test1.inspect(test1.letterLocations, test1.solvedState, test1.cubeInput);

        /* Corner Cases:
        flipped edges (1 flipped edge or 2)
        start off with flipped
        more than one cycle */
    }
}