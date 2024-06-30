import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //initilize all variables
        ArrayList<String> schools = new  ArrayList<>();
        ArrayList<String> teams = new  ArrayList<>();
        ArrayList<String> judgeNames = new  ArrayList<>();
        ArrayList<DebateTeam> entries = initEntry(schools, teams);
        ArrayList<Judge> judges = initJudge(judgeNames);
        Tournement chuckBulligal = new Tournement("Chuck Balligal Ivitational",6, 3,entries, judges);
        //simulate preilim
        chuckBulligal.simulation();
        System.out.println(chuckBulligal.toString());
    }




//Todo: LOAD in some data

    /* Method that initalize all the entreis of debate
 * takes in an external list of school and names of the tem team and create teh debate team object */
public static ArrayList<DebateTeam> initEntry(ArrayList<String> school, ArrayList<String> team){
    // I'm sorry
    ArrayList<DebateTeam> a = new ArrayList<>();
    // check if the two list mathc in length - else default to the shorter ones and throw ans error
    if ( school.size() != team.size()){
        System.exit(5);
        System.out.println("ERROR: TEAM OR SCHOOL LIST SIZE MISMATCH ");
    }
    //for loop
    for (int i = 0; i < school.size(); i++) {
        a.add(new DebateTeam(school.get(i), team.get(i)));
    }
    return a;
}

// Initialized the Judge list
public static ArrayList<Judge> initJudge (ArrayList<String> name){
    // I'm sorry
    ArrayList<Judge> a = new ArrayList<>();
    // todo : check for UQ judge, i.e. implement each school has to bring x judge and check if a school bring enough judges
    //todo
    for (int i = 0; i < name.size(); i++) {
        a.add(new Judge(name.get(i)));
    }
    return a;
}
}

