public class DebateTeam {
    //fields
    private String school;
    private String team;
    private String teamCode;
    private String side;
    private int wins = 0;
    private int loses;
    private int year;

    //constructors
    public DebateTeam(){
        this.school = "";
        this.team = "";
        this.teamCode = "";
        this.side = "";
        this.year = 23;
    }
    public DebateTeam(String school, String team){
        this.school = wikiSchoolName(school);
        this.team = team;
        this.teamCode = wikiTeamCode(team);
        this.side = "";
        this.year = 23;
    }
    public DebateTeam(String school, String team, String side){
        this.school = wikiSchoolName(school);
        this.team = team;
        this.teamCode = wikiTeamCode(team);
        this.side = side;
        this.year = 23;
    }
    public DebateTeam(String school, String team, String side, int year){
        this.school = wikiSchoolName(school);
        this.team = team;
        this.teamCode = wikiTeamCode(team);
        this.side = side;
        this.year = year;
    }
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //private methods
    //covert the tabroom name to match wiki list
    private String wikiSchoolName(String school) {
        //remove spaces
        String ans = "";
        for (int i = 0; i < school.length(); i++) {
            String nextChar = school.substring(i,i+1);
            if (!nextChar.equals(" ")){
                ans += nextChar;
            }
        }
        return  ans;
    }
    //convert the tabroom entry name to the team code format
    private String wikiTeamCode(String team) {
        int index = team.indexOf("&");
        String n2 = team.substring(index+2);

        return team.substring(0,2) + n2.substring(0,2);
    }

    //overarides
    @Override
    public String toString() {
        //the output final wiki link
        //eg https://opencaselist.com/hspolicy23/GlenbrookNorth/CaGo
        return this.school + " " + this.team;
    }
}
