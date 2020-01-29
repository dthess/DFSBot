import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public String[][] arr = null;

    private int lineCount = 0;
    private BufferedReader br = null;
    private String line = "";
    private String splitBy = ",";
    private String fileName = null;

    public CSVReader(String f) throws IOException {

        fileName = f;
        lineCount();
        arr = new String[lineCount][30];

        try {
            br = new BufferedReader(new FileReader(f));
            int count =0;
            line = br.readLine(); // ignore first line
            while ((line = br.readLine()) != null) {
                arr[count] = line.split(splitBy);
                count++;
            }
            arr[count] = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //private getCSV(){


        //fileName = ;
    //}

    private void lineCount() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        lineCount = 0;
        while (reader.readLine() != null) lineCount++;
        reader.close();
    }

    public void printCSV(){
        for(int i=0;i<arr.length-1;i++){
            System.out.print(i+" ");
            for (int j=0;j<arr[i].length;j++){
                if (j==0&&arr[i][j].length()>11){
                    System.out.print(arr[i][j].substring(0,12)+"\t|");
                } else if(j==1||j>26||(j>13&&j<17)){

                } else{
                    System.out.print(arr[i][j]+"\t|");
                }
            }
            System.out.println();
        }        
        System.out.println("Name\t\tInj\tPos\tSal\tTeam\tOpp\tRest\tPS\tUSG\tPER"+
        "\tOppPace OppDEff OppDvP\tL2-Min\tL5-Min"+
        "\tS-Min\tL5-FP\tS-FP\tFloor\tCeil\tProjMin\tProj-FP\tProj-Val");
    }

    public void removeInjuries(){
        for (int i=0;i<arr.length-1;i++){

            if(!arr[i][2].isEmpty()){
                for (int j=1;j<arr[i].length;j++){
                    arr[i][j] = ".";
                }
            }

       
        }
    }

    public void removeZeroProjected(){
        for (int i=0;i<arr.length-1;i++){
            if(!arr[i][26].equals(".")){
                if(Double.parseDouble(arr[i][26])<=0){
                    for (int j=1;j<arr[i].length;j++){
                        arr[i][j] = ".";
                    }
                }
            }
        }
    }
}

/*

 0     1     2       3       4     5      6      7          8          9
Name Likes Injury Position Salary Team Opponent Rest Projected-Score Usage

10     11      12          13        14     15    16     17    18     19
Eff Opp-Pace Opp-DEff Opp-DefvsPos L2-FGA L5-FGA S-FGA L2-Min L5-Min S-Min

20     21   22    23   24  25   26  
L5-FP S-FP Floor Ceil Min Proj Val

*/