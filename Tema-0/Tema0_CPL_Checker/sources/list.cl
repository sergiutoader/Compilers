class List {
    list : LinkedList <- new LinkedList;

    add(o : Object):SELF_TYPE {
        {
            list.cons(o);
            self;
        }
    };

    delete(index : Int) : Object {
        list.delete(index)
    };

    toString():String {
        list.toString()
    };

    merge(other : List):SELF_TYPE {{
        list <- list.merge(other.getList());
        self;
    }};

    filterBy(filterObj : Filter):SELF_TYPE {{
        list.filterBy(filterObj);
        self;
    }};

    sortBy():SELF_TYPE {
        self (* TODO *)
    };

    getList() : LinkedList {
        list
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
    size : Int; -- number of elements in list

    cons(value : Object) : SELF_TYPE {
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

            size <- size + 1;

            self;
        }
    };

    filterBy(filterObj : Filter) : SELF_TYPE {
        let index : Int in {
            while index <= size loop
                if not filterObj.filter(get(index)) then
                    delete(index)
                else
                    index <- index + 1
                fi 
            pool;

            self;
        }
    };

    -- assuming list is not empty
    delete(index : Int) : Object {
        let prev : Node, curr : Node <- head, looping : Bool <- true in {

            if index = 1 then
                head <- head.getNext()
            else
                while looping loop
                    if isvoid curr then
                        looping <- false
                    else
                        if index = 1 then {
                            prev.setNext(curr.getNext());
                            looping <- false;
                        } else {
                            prev <- curr;
                            curr <- curr.getNext();
                            index <- index - 1;
                        } fi
                    fi
                pool
            fi;

            size <- size - 1;
        }
    };

    merge(other : LinkedList) : SELF_TYPE {
        let curr : Node <- head in {
            while not isvoid curr.getNext() loop
                curr <- curr.getNext()
            pool;

            curr.setNext(other.getHead());

            self;
        }
    };

    get(index : Int) : Object {
        let curr : Node <- head, result : Object in {
            
            while 1 < index loop
                if isvoid curr then
                    abort()
                else {
                     index <- index - 1;
                     curr <- curr.getNext(); 
                } fi
            pool;

            if isvoid curr then
                abort()
            else
                curr.getValue()
            fi;
        }
    };

    -- set(index : Int, value : Object) : SELF_TYPE {
    --     let curr : Node <- head in {
    --         while 1 < index loop {
    --             index <- index - 1;
    --             curr <- curr.getNext();
    --         } pool;

    --         curr.setValue(value);

    --         self;
    --     }
    -- };

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

    getHead() : Node { head };
    setHead(n : Node) : Node {{ head <- n; n; }};

    getValue() : Object { head.getValue() };
    setValue(val : Object) : SELF_TYPE {{ head.setValue(val); self; }};

    getNext() : Node { head.getNext() };
    setNext(nxt : Node) : SELF_TYPE {{ head.setNext(nxt); self; }};

    getSize() : Int { size };
};