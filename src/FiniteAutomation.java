import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
public class FiniteAutomation {
    public boolean CheckWord(String sentence) {
        int curr_state = begState;
        for(int i = 0; i < sentence.length(); ++i){
            if(!states.get(curr_state).transitions.containsKey(Character.toString(sentence.charAt(i))))
                return false;
            curr_state = states.get(curr_state).transitions.get(Character.toString(sentence.charAt(i)));
        }
        if(states.get(curr_state).isFinal)
            return true;

        rec(curr_state);
        return found;
    }
    private boolean found = false;


    private void rec(int currState){
        if(states.get(currState).isFinal){
            found = true;
            return;
        }
        if(found)
            return;
        if(wasHere.get(currState))
            return;

        wasHere.set(currState, true);
        for(var val :  states.get(currState).transitions.values()){
            rec(val);
            if(found)
                return;
        }

    }


    public FiniteAutomation(String filename) {
        try(var myReader = new Scanner(new File(filename))){
            alphabetSize = myReader.nextInt();
            statesCount = myReader.nextInt();
            states = new ArrayList<>(statesCount);
            wasHere = new ArrayList<>(statesCount);
            for(int i = 0; i < statesCount; i++) {
                states.add(new State());
                wasHere.add(i, false);
            }
            begState = myReader.nextInt();
            int finCount = myReader.nextInt();
            while(finCount-- > 0)
                states.get(myReader.nextInt()).isFinal = true;
            int alphCount = 0;
            while(myReader.hasNext()){
                int stState = myReader.nextInt();
                String word = myReader.next();
                int endState = myReader.nextInt();
                if(!alphabet.contains(word)){
                    ++alphCount;
                    alphabet.add(word);
                }
                if(alphCount > alphabetSize)
                    throw new StackOverflowError("Too many words");
                states.get(stState).transitions.put(word, endState);
            }

        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            System.exit(-1);
        }

    }

    class State{

        public boolean isFinal = false;

        public HashMap<String, Integer> transitions = new HashMap<>();
    }

    private int alphabetSize;
    private ArrayList<String> alphabet = new ArrayList<>();
    private int statesCount;
    private ArrayList<Boolean> wasHere;
    private int begState;
    private ArrayList<State> states;
}
