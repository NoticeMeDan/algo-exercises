public class Node {
    private String key;
    private Integer value;
    private int height;
    protected Node[] nodes;

    Node(String key, Integer value, int height) {
        this.key = key;
        this.value = value;
        this.height = height;
        this.nodes = new Node[height+1];
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public int getHeight() {
        return height;
    }

    public void setValue(int value) {
        this.value = value;
    }
}