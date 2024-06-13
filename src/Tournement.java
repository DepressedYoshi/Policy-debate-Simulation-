import java.util.ArrayList;
import java.util.Collections;

public class Tournement {
    private static final int NUM_PRESET = 2 ;
    // fields
    private int numPrelim = 6;
    private int numElim = 4;
    private DebateTeam winner = null;
    private ArrayList<DebateTeam> competitior;
    private ArrayList<Judge> judgesList;

//Construnctor

    public Tournement(int numPrelim, int numElim, ArrayList<DebateTeam> competitior, ArrayList<Judge> judgesList) {
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
    public void prelim(){
        for (int i = 0; i < numPrelim; i++) {
            ArrayList<Round> pairing = paring(i);
            for (Round r : pairing){
                r.debating();
                System.out.println("This is Round" + i + ", and the winner is " + r.getWinner());
            }
        }
    }

    private ArrayList<Round> paring(int currRounds) {
        ArrayList<Round> rounds = new ArrayList<>();
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

    private ArrayList<ElimRounds> updateBracker(ArrayList<ElimRounds> elimRounds) {
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

    private ArrayList<DebateTeam> genTeamBreakedList() {
        sortTeamsByWins();
        while (competitior.size() > Math.pow(2.0,numElim)){
            competitior.remove(competitior.size()-1);
        }
        return this.competitior;
    }


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

    // TODO return stat witht ostring
    //TODO returen winner
    //Todo Judgeing pool
    //Todo Speaks result

}
