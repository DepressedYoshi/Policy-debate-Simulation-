public class Main {
    public static void main(String[] args) {
        //test
        //https://opencaselist.com/hspolicy23/LittleRockCentral/ChLe
        DebateTeam d1 = new DebateTeam();
        DebateTeam d2 = new DebateTeam("Little Rock Central", "Chandler & Leenhouts");
        DebateTeam d3 = new DebateTeam("Little Rock Central", "Chandler & Leenhouts", "neg");
        DebateTeam d4 = new DebateTeam("Little Rock Central", "Chandler & Leenhouts", "neg", 24);

        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        System.out.println(d4);
    }
}

