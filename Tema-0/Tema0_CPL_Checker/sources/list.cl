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

    stringOf(object : Object) : String {
        case object of
            p : Product => p.toString();
            r : Rank => r.toString();
            i : Int => new Utils.intToString(i);
            b : Bool => new Utils.boolToString(b);
            s : String => "String(".concat(s).concat(")");
            io : IO => "IO()";
            o : Object => { abort(); ""; };
        esac
    };

    toString() : String {
        let curr : Node <- head, result : String <- "[ " in {
            
            while not isvoid curr 
            loop {
                result <- result.concat(stringOf(curr.getValue())).concat(", ");
                curr <- curr.getNext();
            } pool;

            result <- result.substr(0, result.length() - 2); -- trim last comma and space added to the end of the string
            result.concat(" ]\n");
        }
    };
};