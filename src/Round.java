public class Round {
    protected DebateTeam aff;
    protected DebateTeam neg;
    protected DebateTeam winner;
    protected Judge judge;

    public Round(final DebateTeam aff, final DebateTeam neg, final Judge judge) {
        this.aff = aff;
        this.neg = neg;
        this.winner = null;
        this.judge = judge;
    }



    public DebateTeam getAff() {
        return aff;
    }

    public DebateTeam getNeg() {
        return neg;
    }
    public DebateTeam getWinner() {
        if (winner == null){
            System.out.println("THE DEBATE has NOT been executed - ERROR Winner is null ");
        }
        return winner;
    }

    public void setWinner(DebateTeam winner) {
        this.winner = winner;
    }

    public Judge getJudge() {
        return judge;
    }

    //Main sim Method for a Debate round
    //result in assigning the global DebateTeam field winner and increments win/loss count on each of the DebateTeam object
    //Currently just a ramdomizer
    public void debating() {
        //todo apply judge biases to decesion
        if(Math.random() < 0.50){
            setWinner(aff);
            aff.setWins(aff.getWins()+1);
            neg.setLoses(neg.getLoses()+1);
        }
        else {
            setWinner(neg);
            neg.setWins(neg.getWins()+1);
            aff.setLoses(aff.getLoses()+1);
        }
    }


}
