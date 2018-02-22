import edu.princeton.cs.algs4.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Creates and writes a text-file (txt) with the content from a given StringBuilder
 * All text-files will be found in the directory 'reports' which is created in the same path as Reporter is run
 * @authour Simon
 */
public class Reporter {
    private StringBuilder reportString;
    private String folderName;
    
    /**
     * Instantiate the Reporter by delivering the first report (StringBuilder)
     */
    public Reporter(String fn, StringBuilder sb) {
        this.reportString = sb;
        this.folderName = fn;
    }
    
    private void produceReportTextFile(String fileName) {
        File directory = new File(folderName);
        if (!directory.exists()) directory.mkdir();
        
        File file = new File(directory + "/" + fileName + ".txt");
                
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            String[] lines = this.reportString.toString().split("\n");
            writer.write("----------------------------------------");
            writer.newLine();
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.write("----------------------------------------");
            writer.close();
            //StdOut.println("Report created succesfully");
        }
        catch (IOException iex) {
            StdOut.println(iex.getMessage());
        }
    }
    
    /**
     * Assign new report (StringBuilder) to the Reporter
     */
    public void assignNewReport(StringBuilder sb) {
        this.reportString = sb;
    }
    
    /**
     * Creates a report file with a given file name
     * @param fileName the filename for the text-file
     */
    public void createReportWithFileName(String fileName) {
        produceReportTextFile(fileName);
    }
    
    /**
     * Creates a report file with a timestamp file name
     */
    public void createReportWithTimestampFileName() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        produceReportTextFile(sdf.format(timestamp));
    }
    
    /**
     * Creates a report file with a random filename (random number between 0-100000)
     */
    public void createReportWithRandomFileName(String prefix) {
        String randomName = Integer.toString(StdRandom.uniform(100000));
        produceReportTextFile(prefix + "_" + randomName);
    }
}