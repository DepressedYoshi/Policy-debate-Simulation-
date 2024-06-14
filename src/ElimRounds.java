import java.util.Random;

public class ElimRounds extends Round{
    private Judge judge2;
    private Judge judge3;
    public ElimRounds(DebateTeam aff, DebateTeam neg, Judge judge, Judge judge2, Judge judge3) {
        super(aff, neg, judge);
        coinFlip(aff, neg);
        this.judge2 = judge2;
        this.judge3 = judge3;
    }
//Coinflip / assign sides
    //todo implements check for previous rounds
    //currently just ramdomly swag neg and aff team because default constructor have always assign the fist to be Aff,
    private void coinFlip(DebateTeam aff, DebateTeam neg) {
        if (Math.random()<0.5){
            DebateTeam temp = aff;
            this.aff = neg ;
            this.neg = temp ;
        }
    }

// Elim version of the simulation for debate
    //randomizes ballot count and check for winner instead of sigular votes
    @Override
    public void debating() {
        int affBallots = 0;
        for (int i = 0; i < 3; i++) {
            if (Math.random() <= 0.5){
                affBallots++;
            }
        }
        if (affBallots >=2){
            setWinner(aff);
            aff.setWins(aff.getWins()+affBallots);
        }else{
            setWinner(neg);
            neg.setWins(neg.getWins()+(3-affBallots));
        }
    }
}
