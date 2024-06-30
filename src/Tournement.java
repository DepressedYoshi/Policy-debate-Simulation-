import java.util.ArrayList;
import java.util.Collections;

public class Tournement {
    private static final int NUM_PRESET = 2 ;
    // fields
    private String name;
    private int numPrelim = 6;
    private int numElim = 4;
    private DebateTeam winner = null;
    private ArrayList<DebateTeam> competitior;
    private ArrayList<Judge> judgesList;

//Construnctor

    public Tournement(String name, int numPrelim, int numElim, ArrayList<DebateTeam> competitior, ArrayList<Judge> judgesList) {
        this.name = name;
        this.numPrelim = numPrelim;
        this.numElim = numElim;
        this.competitior = competitior;
        this.judgesList = judgesList;
    }

    // geeter ans setter

    public DebateTeam getWinner() {
        return winner;
    }

    public void setWinner(DebateTeam winner) {
        this.winner = winner;
    }

    public void simulation(){
        prelim();
        elim();
    }

    //----------------------------------------------------PRE_LIM SIMULATION METHODS START----------------------------------------------------------//
    //Main simulation method for the prelim rounds
    public void prelim(){
        for (int i = 0; i < numPrelim; i++) {
            //generat
            ArrayList<Round> pairing = paring(i);
            for (Round r : pairing){
                r.debating();
                System.out.println("This is Round" + i + ", and the winner is " + r.getWinner());
            }
        }
    }
//generate a list of Rounds, simulataes the paring
    // this is not the preset - that is a separeate method
    private ArrayList<Round> paring(int currRounds) {
        ArrayList<Round> rounds = new ArrayList<>();
        //check if it is preset
        if (currRounds < NUM_PRESET){
            rounds = presetPairing();
        }else {
            sortTeamsByWins();
            //todo implement speaks order
            for (int i = 0; i < this.competitior.size(); i += 2) {
                DebateTeam team1 = this.competitior.get(i);
                DebateTeam team2 = this.competitior.get(i + 1);
                Judge judge = this.judgesList.get(i / 2);
                //check for sidelock on even round
                if (currRounds %2 == 0 && team1.getSide().equals("Neg")) {
                    rounds.add(new Round(team1, team2, judge));
                }else{
                    rounds.add(new Round(team2, team1, judge));
                }
            }
        }
        return rounds;
    }
//this generated preset parings, returen arraylist of Round Object
    private ArrayList<Round> presetPairing() {
        ArrayList<Round> pairings = new ArrayList<>();
        Collections.shuffle(competitior);
        Collections.shuffle(judgesList);
        // Handle the case where there is an odd number of teams
        if (this.competitior.size() % 2 != 0) {
            DebateTeam byeTeam = this.competitior.remove(this.competitior.size() - 1);
            byeTeam.setWins(byeTeam.getWins()+1);
            System.out.println(byeTeam.getTeam() + " gets a bye");
        }

        // Check if there are enough judges
        if (this.judgesList.size() < this.competitior.size() / 2) {
            System.out.println("JUDGES NEEDED");
        }

        // Create rounds
        for (int i = 0; i < this.competitior.size(); i += 2) {
            DebateTeam team1 = this.competitior.get(i);
            DebateTeam team2 = this.competitior.get(i + 1);
            Judge judge = this.judgesList.get(i / 2);

            pairings.add(new Round(team1, team2, judge));
        }


        return pairings;
    }
    //Sorted the result list, helper method for other to simulate how teams get paried
    public void sortTeamsByWins() {
        Collections.sort(this.competitior, (team1, team2) -> {
            if (team1.getWins() != team1.getWins()){
                return team2.getWins() - team1.getWins();
            }
            return team1.getSchool().compareTo(team2.getSchool());
        });
    }

    //----------------------------------------------------PRELIM SIMULATION METHODS END----------------------------------------------------------//

    //----------------------------------------------------ELIM SIMULATION METHODS START----------------------------------------------------------//
    //Main simulation method for elim
    public void elim(){
        //initialize Elime rounds and reduce the arraylist size by wins
        ArrayList<ElimRounds> elimRounds  = genBracket(genTeamBreakedList());
        for (int i = 0; i < numElim; i++) {
            for (ElimRounds r: elimRounds){
                r.debating();
                if (i == numElim - 1){
                    this.winner = r.getWinner();
                }
            }
            elimRounds = updateBracker(elimRounds);

        }

    }
    //Update and generate the next round of elim by making a new list of pp who won
    private ArrayList<ElimRounds> updateBracker(ArrayList<ElimRounds> elimRounds) {
        //check if it is final - returns it-self
        if (elimRounds.size() < 2){
            return elimRounds;
        }
        ArrayList<ElimRounds> next = new ArrayList<>();
        for (int i = 0; i < elimRounds.size()/2; i+=2) {
            DebateTeam w1 = elimRounds.get(i).getWinner();
            DebateTeam w2 = elimRounds.get(i+1).getWinner();
            Collections.shuffle(judgesList);
            next.add(new ElimRounds(w1, w2, judgesList.get(i),judgesList.get(i), judgesList.get(i)));
        }
        return next;
    }
//shorten the global fields List for DebatTeam such that it become the stareting pitnf for first Elim
    private ArrayList<DebateTeam> genTeamBreakedList() {
        sortTeamsByWins();
        while (competitior.size() > Math.pow(2.0,numElim)){
            competitior.remove(competitior.size()-1);
        }
        return this.competitior;
    }

//Create the elim braket in the form of a Arraylist from the
    //pari them high-low by wins
    public ArrayList<ElimRounds> genBracket(ArrayList<DebateTeam> teamBreaked){
        Collections.shuffle(judgesList);
        ArrayList<ElimRounds> round = new ArrayList<>();
        int size = teamBreaked.size();
        for (int i = 0; i < size /2; i+=2) {
            round.add(new ElimRounds(teamBreaked.get(i), teamBreaked.get(size - 1 - i), judgesList.get(i), judgesList.get(i+1), judgesList.get(i+2)));
        }
        return round;
    }

    //----------------------------------------------------ELIM SIMULATION METHODS END----------------------------------------------------------//

    //Todo Judgeing pool
    //Todo Speaks result


    @Override
    public String toString() {
        //todo check simulation has occured and no bug can happen
        String winnerStat = " !!!!!!!!!!!!! The Winner of " + this.name + " is " + winner.getTeamCode() + "with a total of " + winner.getWins() + "wins !!!!!!!!!!!!! \n";
        String divider = "------------------------------------------------------------------------------------------------------------------------------------------\n";
        String tStat = "Tournement info: \n" + "Total number of prelim rounds: " + numPrelim + "\n" + "Total number of elim rounds: "+ numElim +"\n";
        return winnerStat + divider + tStat;
    }
}
