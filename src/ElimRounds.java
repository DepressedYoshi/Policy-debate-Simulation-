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

    private void coinFlip(DebateTeam aff, DebateTeam neg) {
        if (Math.random()<0.5){
            DebateTeam temp = aff;
            this.aff = neg ;
            this.neg = temp ;
        }
    }


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
