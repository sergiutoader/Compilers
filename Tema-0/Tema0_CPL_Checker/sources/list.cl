(*
    In this file, you can find the implementation of lists used for this program.
    There are 3 classes:

    List - provides the API for the main commands (merge, filterBy, sortBy, toString)
    and other methods (add, delete, getList).
    It contains a LinkedList object which is a generic linked list.

    LinkedList - implements the operations provided by the API. It contains a
    head field for the head of the list and a size field for the number of elements
    in the list.

    Node - class for a Node in the linked list. Contains fields for the value of the
    node and a reference to the next node in the list. A void node marks the end of
    the list.

*)

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

    sortBy(comparator : Comparator, ascending : Bool):SELF_TYPE {{
        list.sortBy(comparator, ascending);
        self;
    }};

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
    head : Node; -- head of list
    size : Int; -- number of elements in list

    cons(value : Object) : SELF_TYPE {
        let newNode : Node <- new Node, last : Node in {
            -- creates a new node
            newNode.init(value);

            -- place node at the end of the list
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
        let index : Int <- 1 in {
            -- delete all nodes filtered by the filter object
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

    sortBy(comparator : Comparator, ascending : Bool) : SELF_TYPE {
        let n : Int <- size, m : Int <- size, sign : Int in {

            -- bubble sort implementation, N iterations over the list
            while 0 < n loop {
                let curr : Node <- head, aux : Object, val1 : Object, val2 : Object, compareValue : Int in
                    while not isvoid curr.getNext() loop {
                        -- get current value and next value in the list
                        val1 <- curr.getValue();
                        val2 <- curr.getNext().getValue();

                        -- compare the two values
                        if ascending then
                            compareValue <- comparator.compareTo(val1, val2)
                        else
                            compareValue <- comparator.compareTo(val2, val1)
                        fi;

                        -- swap if compareValue is greater than 0
                        if 0 < compareValue then {
                            aux <- val1;
                            curr.setValue(val2);
                            curr.getNext().setValue(aux);
                        } else
                            0 -- do nothing
                        fi;

                        curr <- curr.getNext();
                    } pool;
                n <- n - 1;
            } pool;


            self;
        }    
    };

    delete(index : Int) : Object {
        let prev : Node, curr : Node <- head, looping : Bool <- true in {
            -- if index is 1, just move head pointer to next element
            if index = 1 then
                head <- head.getNext()
            else
                -- decrement index until it has the value of 1, then make previous node to
                -- point to the next one
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
            -- decrement size
            size <- size - 1;
        }
    };

    -- make the last node in the first list to point to first node in the second list
    merge(other : LinkedList) : SELF_TYPE {
        let curr : Node <- head in {
            while not isvoid curr.getNext() loop
                curr <- curr.getNext()
            pool;

            curr.setNext(other.getHead());

            self;
        }
    };

    -- go to index position in the list and return the value of that node
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

    -- convert any object to its respective string
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

    -- appends all toString() results into a single string
    toString() : String {
        if size = 0 then
            "[  ]\n"
        else
            let curr : Node <- head, result : String <- "[ " in {
                
                while not isvoid curr 
                loop {
                    result <- result.concat(stringOf(curr.getValue())).concat(", ");
                    curr <- curr.getNext();
                } pool;

                result <- result.substr(0, result.length() - 2); -- trim last comma and space added to the end of the string
                result.concat(" ]\n");
            }
        fi
    };

    getHead() : Node { head };
    setHead(n : Node) : Node {{ head <- n; n; }};

    getValue() : Object { head.getValue() };
    setValue(val : Object) : SELF_TYPE {{ head.setValue(val); self; }};

    getNext() : Node { head.getNext() };
    setNext(nxt : Node) : SELF_TYPE {{ head.setNext(nxt); self; }};

    getSize() : Int { size };
};