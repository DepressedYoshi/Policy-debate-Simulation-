public class Round {
    private DebateTeam aff;
    private DebateTeam neg;
    private DebateTeam winner;
    private Judge judge;

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
            System.out.println("THE DEBATE has ");
        }
        return winner;
    }

    public void setWinner(DebateTeam winner) {
        this.winner = winner;
    }

    public Judge getJudge() {
        return judge;
    }

    public void debating() {
        //todo apply judge biases to decesion
        if(Math.random() < 0.50)
            setWinner(aff);
        else
            setWinner(neg);
    }


}
