public class DebateTeam {
    //fields
    private String school;
    private String team;
    private String teamCode;
    private String side;
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
        return "https://opencaselist.com/hspolicy" + this.year + "/" + this.school + "/" +  this.teamCode + "/" + this.side;
    }
}
