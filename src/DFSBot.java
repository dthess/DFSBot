public class DFSBot {
    public static void main(String[] args) throws Exception {

        

        String fileName = "resources/DFN NBA FD 1_25.csv";

        CSVReader csv = new CSVReader(fileName);
        
        csv.removeInjuries();
        csv.removeZeroProjected(); 
        //csv.printCSV();        

        Optimizer o = new Optimizer(csv);

        //o.printAvailable();
        o.printSelected();
    }
}