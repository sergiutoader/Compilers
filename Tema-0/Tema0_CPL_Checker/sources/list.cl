class List {
    list : LinkedList <- new LinkedList;

    add(o : Object):SELF_TYPE {
        {
            list.cons(o);
            self;
        }
    };

    toString():String {
        list.toString()
    };

    merge(other : List):SELF_TYPE {
        self (* TODO *)
    };

    filterBy():SELF_TYPE {
        self (* TODO *)
    };

    sortBy():SELF_TYPE {
        self (* TODO *)
    };
};

class Node {
    value : Object;
    next : Node;

    init (val : Object) : Node {{
        value <- val;
        self;
    }};


    getValue() : Object { value };
    setValue(val : Object) : SELF_TYPE {{ value <- val; self; }};

    getNext() : Node { next };
    setNext(nxt : Node) : SELF_TYPE {{ next <- nxt; self; }};
};

class LinkedList {
    head : Node; -- Head of list

    cons(value : Object) : LinkedList {
        let newNode : Node <- new Node, last : Node in
        {
            newNode.init(value);

            if isvoid head then
                head <- newNode
            else
            {
                last <- head;

                while not isvoid last.getNext() loop
                    last <- last.getNext()
                pool;
                last.setNext(newNode);
            }
            fi;

            self;
        }
    };

    stringOf(o : Object) : String {
        ""
    };

    toString() : String {
        let curr : Node <- head, result : String <- "" in {
            
            while not isvoid curr 
            loop {
                result <- result.concat(stringOf(curr.getValue()));
                curr <- curr.getNext();
            } pool;

            result;
        }
    };
};
