import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //initilize all variables
        String filePath = "W:/Code Project/Java/DebatTeam/lib/Tournement Data.xlsx";
        ArrayList<String> schools = readColumnFromExcel(filePath, 0,0);
        ArrayList<String> teams = readColumnFromExcel(filePath, 0, 2);
        ArrayList<String> judgeNames = readColumnFromExcel(filePath,1,3);
        ArrayList<DebateTeam> entries = initEntry(schools, teams);
        ArrayList<Judge> judges = initJudge(judgeNames, entries);
        Tournement chuckBulligal = new Tournement("Chuck Balligal Ivitational",6, 3,entries, judges);
        //simulate preilim
        chuckBulligal.simulation();
        System.out.println(chuckBulligal.toString());
    }

//Todo: LOAD in some data

// the Excel data reader
        public static ArrayList<String> readColumnFromExcel(String filePath,  int sheetNum, int columnIndex) {
            ArrayList<String> columnData = new ArrayList<>();
            try (FileInputStream fis = new FileInputStream(filePath);
                 Workbook workbook = WorkbookFactory.create(fis)) {
                Sheet sheet = workbook.getSheetAt(sheetNum); // Assumes the data is in the first sheet
                for (Row row : sheet) {
                    Cell cell = row.getCell(columnIndex);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                columnData.add(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                columnData.add(String.valueOf(cell.getNumericCellValue()));
                                break;
                            case BOOLEAN:
                                columnData.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            default:
                                columnData.add(""); // Handle other types or null cells
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return columnData;
        }


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
    //ignore the first line of data because its "school"
    for (int i = 1; i < school.size(); i++) {
        a.add(new DebateTeam(school.get(i), team.get(i)));
    }
    return a;
}

// Initialized the Judge list
public static ArrayList<Judge> initJudge (ArrayList<String> name, ArrayList<DebateTeam> team){
    // todo : check for UQ judge, i.e. implement each school has to bring x judges
    //currently it only check if number of jusges is eough
    if ((name.size()-1) * 2 < team.size()){
        System.out.println("ERROR: NOT ENOUGH JUDGES WITH CURRENT SETTINGS");
        return null;
    }

    // I'm sorry

    ArrayList<Judge> a = new ArrayList<>();

    for (int i = 1; i < name.size(); i++) {
        a.add(new Judge(name.get(i)));
    }
    return a;
}
}

