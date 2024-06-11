import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Tournement {
    private static final int NUM_PRESET = 2 ;
    // fields
    private int numPrelim = 6;
    private int numElim = 4;
    private boolean isPrelim = true;
    private boolean isElim = false;
    private DebateTeam winner = null;
    private ArrayList<DebateTeam> competitior = new ArrayList<>();
    private ArrayList<Judge> judgesList = new ArrayList<>();

//Construnctor

    public Tournement(int numPrelim, int numElim, ArrayList<DebateTeam> competitior, ArrayList<Judge> judgesList) {
        this.numPrelim = numPrelim;
        this.numElim = numElim;
        this.competitior = competitior;
        this.judgesList = judgesList;
    }

    // geeter ans setter


    public boolean isPrelim() {
        return isPrelim;
    }

    public void setPrelim(boolean prelim) {
        isPrelim = prelim;
    }

    public boolean isElim() {
        return isElim;
    }

    public void setElim(boolean elim) {
        isElim = elim;
    }

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
        //todo Implement sid lock
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

                rounds.add(new Round(team1, team2, judge));
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
        Collections.sort(this.competitior, new Comparator<DebateTeam>() {
            @Override
            public int compare(DebateTeam team1, DebateTeam team2) {
                if (team1.getWins() != team1.getWins()){
                    return team2.getWins() - team1.getWins();
                }
                return team1.getSchool().compareTo(team2.getSchool());
            }
        });
    }

    //----------------------------------------------------PRELIM SIMULATION METHODS END----------------------------------------------------------//

    //----------------------------------------------------ELIM SIMULATION METHODS START----------------------------------------------------------//
    public void elim(){
        ArrayList<DebateTeam> teamBreaked =  genTeamBreakedList();
        ArrayList<Round> elimRounds  = genBracket(teamBreaked);
        //todo update list as team get eliminated
    }

    private ArrayList<DebateTeam> genTeamBreakedList() {
        sortTeamsByWins();
        while (competitior.size() > Math.pow(2.0,numElim)){
            competitior.remove(competitior.size()-1);
        }
        return this.competitior;
    }


    /* TODO 1. neg aff flip assignemtn, judge list pref assign ment
    *
    * */
    public ArrayList<Round> genBracket(ArrayList<DebateTeam> teamBreaked){
        ArrayList<Round> round = new ArrayList<>();
        int size = teamBreaked.size();
        for (int i = 0; i < size /2; i+=2) {
            round.add(new Round(teamBreaked.get(i), teamBreaked.get(size - 1 - i), judgesList.get(i)));
        }
        return round;
    }

    //----------------------------------------------------ELIM SIMULATION METHODS END----------------------------------------------------------//

    // TODO return stat
    //TODO returen winner
    //Todo Judgeing pool
    //Todo Speaks result

}
