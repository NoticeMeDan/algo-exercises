import edu.princeton.cs.algs4.*;

public class HashPipe{
  private RedBlackBST<String, Integer>[] trees;
  private int size;

  public HashPipe(){ // create an empty symbol table
    this.trees = new RedBlackBST[32]; //Default for our HashPipe
    this.size = 0; //Default size

    for(int i = 0; i<32; i++){ //Create a RedBlackBST for each index.
      this.trees[i] = new RedBlackBST<String, Integer>();
    }
  }

  public int size(){ // return the number of elements
    return this.size;
  }

  // The rule for put() is the following:
  // The index of the array holds it own height and any height above itself
  // Therefore trees[0] holds every key and value. Trees[4] holds height 4 and everything above.
  public void put(String key, Integer val){ // put key-value pair into the table
    int height = getKeyHeight(key);

    for (int i = 0; i < height; i++){ // do put() as long as the rule holds
      this.trees[i].put(key, val);
    }

    this.size ++; //Finally increase the size of the Pipe
  }

  public Integer get(String key){  // value associated with key
    int height = getKeyHeight(key); //get the height

    return this.trees[height].get(key);
  }

  public String floor(String key){ // largest key less than or equal to key
    int height = getKeyHeight(key);

    return this.trees[height].floor(key);
  }

  //Helper Functions:
  private int getKeyHeight(String key){
    int keyHashValue = key.hashCode(); //Get the HashValue for the character
    int trailingZeros = Integer.numberOfTrailingZeros(keyHashValue); //Get the number of trailingZeros
    int height = trailingZeros += 1; //the height is trailingZeros + 1

    return height;
  }

  // returns the contents of the pipe of the given key at the
  // given height h, counting from below and starting with 0.
  public String control(String key, int h){
    try{ //Try because the key might not exist in the given height
      String controlKey = trees[h].ceiling(key); //Enters the height, finds the ceiling to the key

      if (controlKey.equals(key)){
        // key + 1, because ceiling
        // Returns the smallest key in the symbol table greater than or equal to the key
        return trees[h].ceiling(key + 1);
      } else {
        return null;
      }
    } catch (Exception e){
      e.getMessage();
      return null;
    }
  }

  //Main
  public static void main(String[] args){
    // // Test
    // int i=0;
    // //        String [] in = new String[0];
    // String [] in = new String[26];
    // i=0;
    // for(char c = 'A'; c <= 'Z'; c++ ) in[i++] = "" + c;
    //
    // HashPipe H = new HashPipe();
    //
    // for( int j=0;j<in.length;j++ ){
    //   H.put(in[j], j);
    //   System.out.print("Insert: ");
    //   System.out.println(in[j]);
    //   for( int g=0;g<j;g++ ) {
    //       for( int h=0;h<32;h++ ) {
    //           String ctrl = H.control(in[g],h);
    //           if( ctrl != null ) System.out.print(ctrl);
    //           else System.out.print(".");
    //           System.out.print(" ");
    //       }
    //       System.out.print(" : ");
    //       System.out.println(in[g]);
    //   }
    // }
    HashPipe hashPipe = new HashPipe();
    int counter = 0;

    while(!StdIn.isEmpty()){
      hashPipe.put(StdIn.readString(), counter);
      counter++;
    }
  }
}