public class HashPipe

public HashPipe() // create an empty symbol table
public int size() // return the number of elements
public void put(String key, Integer val) // put key-value pair into the table
public Integer get(String key) // value associated with key
public String floor(String key) // largest key less than or equal to key


String control(String key, int h); //returns the contents of the pipe of the given key at the
//given height h, 

private Node floorNode(Key key); //returns the pipe of the floor of the given key

Integer.numberOfTrailingZero(int i) //get height of node;