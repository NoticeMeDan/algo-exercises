import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class CompareDNA {
    private double sum;
    private final int D = 10000;
    private final int K = 20;
    private ArrayList<String> species, dna;

    public CompareDNA(ArrayList<String> species, ArrayList<String> dna) {
        this.species = species;
        this.dna = dna;
    }
    public double compare (int[] x, int[] y){
        return cosAngle(x, y);
    }

    public double compareTwoDNA (String seq1, String seq2){
        return compare(dnaToVector(seq1), dnaToVector(seq2));
    }

    public int[] dnaToVector (String dna){
        int[] result = new int[this.D];
        String subString = null;

        for (int i = 0; i + this.K < dna.length(); i++) {
            subString = dna.substring(i, this.K + i);
            result[hashFunc(subString)]++;
        }

        return result;
    }

    public int hashFunc (String subString){
        return Math.abs(subString.hashCode() % this.D);
    }

    boolean willHitBounds (int i){
        return this.K + i > this.D;
    }

    /**
     * The cos angle between two vectors of d-dimensions
     * http://onlinemschool.com/math/library/vector/angl/
     */
    private double cosAngle (int[] x, int[] y){
        double nominator = 0;
        double divisor_X = 0;
        double divisor_Y = 0;

        for (int i = 0; i < this.D; i++) {
            nominator += x[i] * y[i];
            divisor_X += Math.pow(x[i], 2);
            divisor_Y += Math.pow(y[i], 2);
        }

        divisor_X = Math.sqrt(divisor_X);
        divisor_Y = Math.sqrt(divisor_Y);

        return nominator / (divisor_X * divisor_Y);
    }

    public void printComparisons () {
        System.out.println("._______________________________________________________________________.");
        String format = "|%1$-10s		|%2$-10s 		|  %3$-20s |\n";
        System.out.format(format, "Specie 1", "Specie 2", "DNA match");
        System.out.println("+=======================================================================+");

        if (this.species.size() == this.dna.size()) {
            for (int i = 0; i < this.species.size(); i++) {
                for (int j = i + 1; j < this.species.size(); j++) {
                    System.out.format(format, this.species.get(i), this.species.get(j), this.compareTwoDNA(dna.get(i), dna.get(j)));
                    System.out.println("+-----------------------+-----------------------+-----------------------+");
                }
            }
        } else System.out.format("whoops", "whoops", "whoops");
    }

    public static void main (String[]args){
        String regex = "\\>(?<species>[A-Za-z\\-]*?)\\s[0-9A-Z ]*?\\n(?<dna>[A-Z\\n]+)\\n";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(StdIn.readAll());
        ArrayList<String> species = new ArrayList<String>();
        ArrayList<String> dna = new ArrayList<String>();

        while (matcher.find()) {
            //Search for Name
            species.add(matcher.group("species"));

            //Search for dna
            dna.add(matcher.group("dna"));
        }

        CompareDNA CompareDNA = new CompareDNA(species, dna);
        CompareDNA.printComparisons();
    }
}