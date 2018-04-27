public class HashPipe {

    private Node root;
    private int size;

    public HashPipe() {
        this.root = new Node(null,null,32);
        this.size = 0;
    }

    // return the number of elements
    public int size() {
        return this.size;
    }

    // put key-value pair into the table
    public void put(String key, Integer val) {
        Node newNode = new Node(key,val,Integer.numberOfTrailingZeros(key.hashCode()));

        put(this.root, newNode, this.root.getHeight());
        this.size+=1;
    }

    public Node put(Node from, Node to, int height) {
        if (from == null || height < 0) return null;

        Node reference = from.nodes[height];
        if (reference == null) {
            if (to.getHeight() >= height) {
                setReference(from,to,height);
            }
            return put(from,to,--height);
        }

        int cmp = to.getKey().compareTo(reference.getKey());
        if (cmp < 0) {
            if (to.getHeight() >= height) setReference(from,to,height);
            return put(from,to,(height-1));
        } else if (cmp > 0) {
            return put(reference, to, height);
        } else {
            reference.setValue(to.getValue());
            return reference;
        }
    }

    public void setReference(Node from, Node to, int height) {
        Node reference = from.nodes[height];
        from.nodes[height] = to;
        to.nodes[height] = reference;
    }

    // value associated with key
    public Integer get(String key) {
        return floorNode(this.root, key, this.root.getHeight()).getValue();
    }

    // largest key less than or equal to key
    public String floor(String key) {
        return floorNode(this.root, key, this.root.getHeight()).getKey();
    }

    //returns the pipe of the floor of the given key
    public Node floorNode(Node x, String key, int height) {
        if (x == null) return null;

        Node reference = x.nodes[height];
        if (reference == null) {
            if (height < 1) return x;
            else return floorNode(x, key, (height-1));
        }

        int cmp = key.compareTo(reference.getKey());
        if (cmp < 0 && 0 < height) return floorNode(x ,key ,(height-1));
        else if (cmp < 0) return x;
        if (cmp == 0) return reference;

        Node node = floorNode(reference, key, height);
        if (node == null) return reference;
        else return node;
    }

    /** returns the contents of the pipe of the given key at the
     * given height h, counting from below and starting with 0. The control
     * method returns the key that is referenced at that position, or null.*/
    public String control(String key, int h) {
        Node node = floorNode(this.root, key, this.root.getHeight());
        if (node == null || node.getHeight() < h || node.nodes[h] == null) return null;
        return node.nodes[h].getKey();
    }
}