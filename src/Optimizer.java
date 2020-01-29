public class Optimizer{

    String[] names = null;
    String[] positions = null;
    Double[][] info = null;
    Boolean[] selected = null;

    CSVReader csv = null;
    int players = 0;
    int salaryCap = 60000;

    public Optimizer(CSVReader c){
        csv = c;
        countPlayers();

        names = new String[players];
        initStringArr(names);
        positions = new String[players];
        initStringArr(positions);
        info = new Double[players][4];
        initDoubleArr(info);
        selected = new Boolean[players];
        initBoolArr(selected);

        readCSV();

        optimize();
    }

    private void optimize(){
        int salaryRemaining = salaryCap;
        //find highest value player at each position, take them
        takeHighestVal();    
    }

    private void takeHighestVal(){
        String pos = "PG";
        highestValue(pos);
        pos = "SG";
        highestValue(pos);
        pos = "SF";
        highestValue(pos);
        pos = "PF";
        highestValue(pos);
        pos = "C";
        highestValue(pos);
    }

    //adds highest value at given position to selected
    private void highestValue(String pos){
        Double highest = 0.0;
        int index = -1;
        for(int i=0;i<players;i++){
            if (positions[i].contains(pos)&&info[i][3]>highest){
                highest = info[i][3];
                index = i;
            }
        }
        selected[index] = true;
    }

    public void printSelected(){
        System.out.println("\nSelected Players:\n");
        for (int i=0;i<players;i++){
            if(selected[i]==true){           
                for (int j=0;j<4;j++){
                    System.out.print(info[i][j]+"\t|");
                }   
                System.out.print(positions[i]+"\t|"+names[i]+"\n");
            }
        }
        System.out.println("\nSalary\tMin.\tProj.\tValue\n");
    }

    public void printAvailable(){
        System.out.println("\nAvailale Players:\n");
        for (int i=0;i<players;i++){
            for (int j=0;j<4;j++){
                System.out.print(info[i][j]+"\t|");
            }            
            System.out.print(positions[i]+"\t|"+names[i]+"\n");
        }
        System.out.println("\nSalary\tMin.\tProj.\tValue\n");
    }

    //        0     1   2    3
    //info: salary min proj val
    private void readCSV(){
        int x = 0;
        for (int i=0;i<csv.arr.length-1;i++){
            if(!csv.arr[i][4].contains(".")){
                names[x] = csv.arr[i][0];
                positions[x] = csv.arr[i][3];
                info[x][0] = Double.parseDouble(csv.arr[i][4]);
                info[x][1] = Double.parseDouble(csv.arr[i][24]);
                info[x][2] = Double.parseDouble(csv.arr[i][25]);
                info[x][3] = Double.parseDouble(csv.arr[i][26]);
                x++;
            }
        }
    }

    private void countPlayers(){
        players = 0;
        for(int i=0;i<csv.arr.length-1;i++){
            if(!csv.arr[i][4].contains(".")){
                players++;
            }
        }
    }

    private void initStringArr(String[] a){
        for (int i=0;i<a.length;i++){
            a[i]=".";
        }
    }

    private void initDoubleArr(Double[][] d){
        for (int i=0;i<d.length;i++){
            for (int j=0;j<d[i].length;j++){
                d[i][j] = 0.0;
            }
        }
    } 

    private void initBoolArr(Boolean[] b){
        for (int i=0;i<b.length;i++){
            b[i]=false;
        }
    }

}
