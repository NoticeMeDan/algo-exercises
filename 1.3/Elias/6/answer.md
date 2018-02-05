# Answer

While `q` isn't empty, it pushes the oldest element of `q` onto the `stack`.  
After that, it enqueues the top element of `stack` to `q`, until `stack` is empty.

By doing this, it changes absolutely nothing about `q`, as the order is reversed twice.


Example:  
We have a Queue `q` with the values `[1, 2, 3, 4, 5]`, along with an empty Stack `stack`.  
We start by dequeue'ing `q` onto `stack` until it's empty.  
Now they have the following values:  

```
q: []  
stack: [5, 4, 3, 2, 1] 
```

We then pop `stack` onto `q`, until it's empty. Now we have the following values:  

```
q: [1, 2, 3, 4, 5]  
stack: [] 
```