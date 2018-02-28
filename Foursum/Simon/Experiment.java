import edu.princeton.cs.algs4.*;
import java.util.*;
import java.io.*;

public class Experiment {
    private static int N;
    private static long[] vals;
    private static Map<Integer, ArrayList<File>> dataFiles;
    private static ArrayList<String> dataNumbers;
    private static ArrayList<Double> stopwatchValues;
    
    // 0 for simple, 1 for faster
    private static int experimentChooser;
    
    public static void main(String[] args) {
        stopwatchValues = new ArrayList<>();
        prepareExperimentFromStdIn();
        initializeExperimentFromStdIn();
        //prepareExperimentFromDirectory();
        //initializeExperimentFromDirectory();
    }
    
    private static void prepareExperimentFromStdIn() {
        dataNumbers = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            Integer p = StdIn.readInt();
            String pS = p.toString();
            dataNumbers.add(pS);
        }
    }
    
    private static void prepareExperimentFromDirectory() {
        dataFiles = new HashMap<>();
        File dir = new File("data");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                organizeDataFile(child);
            }
        }
    }
    
    private static void initializeExperimentFromStdIn() {
        N = dataNumbers.size();
        String[] inputArgs = dataNumbers.toArray(new String[dataNumbers.size()]);
        Stopwatch stopwatch = new Stopwatch();
        runExperiment(inputArgs);
        stopwatchValues.add(stopwatch.elapsedTime()); 
    }
    
    private static void initializeExperimentFromDirectory() {
        for (Map.Entry<Integer, ArrayList<File>> entry : dataFiles.entrySet()) {
            N = entry.getKey();
            for (File dataFile : entry.getValue()) {
                 String[] inputArgs = fileAsStringArgs(dataFile);
                 Stopwatch stopwatch = new Stopwatch();
                 runExperiment(inputArgs);
                 stopwatchValues.add(stopwatch.elapsedTime());          
            }
            createReports();
        }
    }
    
    private static void organizeDataFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            Integer lineAsKey = Integer.parseInt(line);
            
            if (dataFiles.containsKey(lineAsKey)) {
                dataFiles.get(lineAsKey).add(file);
            }
            else {
                ArrayList<File> filesWithKey = new ArrayList<File>();
                filesWithKey.add(file);
                dataFiles.put(lineAsKey, filesWithKey);
            }
        } 
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static void runExperiment(String[] vals) {
        //Simple.main(vals);
        //Faster.main(vals);
        
    }
    
    private static String[] fileAsStringArgs(File file) {
        String[] inputArgs;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            int size = Integer.parseInt(line);

            inputArgs = new String[size + 1];
            
            int count = 0;
            while (line != null) {
                inputArgs[count] = line;
                line = br.readLine();
                count++;
            }
            
            return inputArgs;
        } 
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private static void createReports() {
       StringBuilder sb = new StringBuilder();
       double[] stopwatchSimpleValues = new double[stopwatchValues.size()];
       for (int i = 0; i < stopwatchValues.size(); i++) 
           stopwatchSimpleValues[i] = stopwatchValues.get(i);
       stopwatchValues.clear();
       
       double minV = StdStats.min(stopwatchSimpleValues);
       double maxV = StdStats.max(stopwatchSimpleValues);
       double meanV = StdStats.mean(stopwatchSimpleValues);
       
       sb.append("Array of size: " + N + "\n");
       sb.append("Minimum Value: " + minV + " seconds\n");
       sb.append("Maximum Value: " + maxV + " seconds\n");
       sb.append("Mean Value: " + meanV + " seconds\n");
       
       String dirName = "simple_reports";
       
       Reporter reporter = new Reporter(dirName, sb);
       reporter.createReportWithRandomFileName(N + "");
    } 
    
}
