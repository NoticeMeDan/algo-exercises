import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class CompareDNA{
  private double sum;
  private final int D = 10000;
  private final int K = 20;
  private ArrayList<String> species, dna;

  public CompareDNA(ArrayList<String> species, ArrayList<String> dna){
    this.species  = species;
    this.dna      = dna;
  }
  //Hej Magnus
  public double compare(int[] p, int[] q){
    return cosAngle(p, q);
  }

  public double compareTwodna(String seq1, String seq2){
    return compare(dnaToVector(seq1), dnaToVector(seq2));
  }

  public int[] dnaToVector(String dna){
    int[] result = new int[this.D];
    String subString = null;

    for(int i = 0; i + this.K < dna.length(); i++){
      subString = dna.substring(i, this.K + i);
      result[hashFunc(subString)] ++;
    }

    return result;
  }

  public int hashFunc(String subString){
    return Math.abs(subString.hashCode() % this.D);

  }

  boolean willHitBounds(int i){
    return this.K+i > this.D;
  }

  /**
  * The cos angle between two vectors of d-dimensions
  * http://onlinemschool.com/math/library/vector/angl/
  */
  private double cosAngle(int[] p, int[] q){
    double nominator = 0; double divisor_P = 0; double divisor_Q = 0;
    
    for(int i = 0; i < this.D; i++){
      nominator += p[i] * q[i];
      divisor_P += Math.pow(p[i], 2);
      divisor_Q += Math.pow(q[i], 2);
    }
    
    divisor_P = Math.sqrt(divisor_P);
    divisor_Q = Math.sqrt(divisor_Q);
    
    return nominator/(divisor_P * divisor_Q);
  }

  // public String printComparison(){
  
  // }

  public static void main(String[] args){
    String regex = "\\>(?<species>[A-Za-z\\-]*?)\\s[0-9A-Z ]*?\\n(?<dna>[A-Z\\n]+)\\n";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(StdIn.readAll());
    ArrayList<String> species = new ArrayList<String>();
    ArrayList<String> dna = new ArrayList<String>();

    while(matcher.find()){
      //Search for Name
      species.add(matcher.group("species"));

      //Search for dna
      dna.add(matcher.group("dna"));
    }

    CompareDNA CompareDNA = new CompareDNA(species, dna);


    if(species.size() == dna.size()){
      for (int i = 0; i < species.size(); i++){
        for (int j = i + 1; j < species.size(); j++){
          System.out.println("first name: " + species.get(i) + " second name: " + species.get(j));
          System.out.println(CompareDNA.compareTwodna(dna.get(i), dna.get(j)));
        }
      }
    }
    else {
      System.out.println("Woops");
    }
  }
}
