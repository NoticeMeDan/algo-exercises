public class HashPipe {
    private Pipe root;

    public HashPipe() {
        // Default height set to 32
        this.root = new Pipe(null, 0, 32);
    }

    // put key-value pair into the pipe
    public void put(String key, Integer val) {
        // Create a new pipe
        Pipe newPipe = new Pipe(key, val, Integer.numberOfTrailingZeros(key.hashCode()));
        // Insert the new pipe into the HashPipe
        put(this.root, newPipe, this.root.getHeight());
    }

    private Pipe put(Pipe from, Pipe to, int height) {
        // Checking if the pipe is valid
        if (from == null || height < 0)
            return null;

        // Create a reference pipe
        Pipe reference = from.pipes[height];
        if (reference == null) {
            // If the height is greater than our from, then our to, will be the referenced pipe
            if (to.getHeight() >= height) {
                setReference(from, to, height);
            }
            return put(from,to,--height);
        }

        int compare = to.getKey().compareTo(reference.getKey());
        if (compare < 0) {
            if (to.getHeight() >= height) setReference(from,to,height);
            return put(from, to ,height-1);
        } else if (compare > 0) {
            return put(reference, to, height);
        } else {
            reference.setValue(to.getValue());
            return reference;
        }
    }

    private void setReference(Pipe from, Pipe to, int height) {
        Pipe reference = from.pipes[height];
        from.pipes[height] = to;
        to.pipes[height] = reference;
    }

    //returns the pipe of the floor of the given key
    private Pipe floorPipe(Pipe x, String key, int height) {
        if (x == null)
            return null;

        Pipe reference = x.pipes[height];
        if (reference == null) {
            if (height < 1)
                return x;
            else
                return floorPipe(x, key, (height-1));
        }

        int compare = key.compareTo(reference.getKey());
        if (compare < 0 && 0 < height)
            return floorPipe(x , key , height-1);
        else if (compare < 0)
            return x;
        if (compare == 0)
            return reference;

        Pipe floorPipe = floorPipe(reference, key, height);
        if (floorPipe == null)
            return reference;
        else
            return floorPipe;
    }

    /**
     * Input is the pipe we want to control. The return values is the referenced pipe.
     */
    public String control(String key, int h) {
        Pipe floorPipe= floorPipe(this.root, key, this.root.getHeight());
        if (floorPipe == null || floorPipe.getHeight() < h || floorPipe.pipes[h] == null)
            return null;

        return floorPipe.pipes[h].getKey();
    }

    public class Pipe {
        private String key;
        private int value, height;
        Pipe[] pipes;

        Pipe(String key, int value, int height) {
            this.key    = key;
            this.value  = value;
            this.height = height;
            this.pipes  = new Pipe[height+1];
        }

        /*              *
         *    GETTERS   *
         *       &      *
         *    SETTERS   *
         *              */

        String getKey() {
            return this.key;
        }

        int getValue() {
            return this.value;
        }

        int getHeight() {
            return this.height;
        }

        void setValue(int value) {
            this.value = value;
        }
    }
}