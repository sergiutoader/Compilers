(*
   The class A2I provides integer-to-string and string-to-integer
conversion routines.  To use these routines, either inherit them
in the class where needed, have a dummy variable bound to
something of type A2I, or simpl write (new A2I).method(argument).
*)


(*
   c2i   Converts a 1-character string to an integer.  Aborts
         if the string is not "0" through "9"
*)
class A2I {

     c2i(char : String) : Int {
    if char = "0" then 0 else
    if char = "1" then 1 else
    if char = "2" then 2 else
        if char = "3" then 3 else
        if char = "4" then 4 else
        if char = "5" then 5 else
        if char = "6" then 6 else
        if char = "7" then 7 else
        if char = "8" then 8 else
        if char = "9" then 9 else
        { abort(); 0; }  -- the 0 is needed to satisfy the typechecker
        fi fi fi fi fi fi fi fi fi fi
     };

(*
   i2c is the inverse of c2i.
*)
     i2c(i : Int) : String {
    if i = 0 then "0" else
    if i = 1 then "1" else
    if i = 2 then "2" else
    if i = 3 then "3" else
    if i = 4 then "4" else
    if i = 5 then "5" else
    if i = 6 then "6" else
    if i = 7 then "7" else
    if i = 8 then "8" else
    if i = 9 then "9" else
    { abort(); ""; }  -- the "" is needed to satisfy the typchecker
        fi fi fi fi fi fi fi fi fi fi
     };

(*
   a2i converts an ASCII string into an integer.  The empty string
is converted to 0.  Signed and unsigned strings are handled.  The
method aborts if the string does not represent an integer.  Very
long strings of digits produce strange answers because of arithmetic 
overflow.

*)
     a2i(s : String) : Int {
        if s.length() = 0 then 0 else
    if s.substr(0,1) = "-" then ~a2i_aux(s.substr(1,s.length()-1)) else
        if s.substr(0,1) = "+" then a2i_aux(s.substr(1,s.length()-1)) else
           a2i_aux(s)
        fi fi fi
     };

(*
  a2i_aux converts the usigned portion of the string.  As a programming
example, this method is written iteratively.
*)
     a2i_aux(s : String) : Int {
    (let int : Int <- 0 in  
           {    
               (let j : Int <- s.length() in
              (let i : Int <- 0 in
            while i < j loop
            {
                int <- int * 10 + c2i(s.substr(i,1));
                i <- i + 1;
            }
            pool
          )
           );
              int;
        }
        )
     };

(*
    i2a converts an integer to a string.  Positive and negative 
numbers are handled correctly.  
*)
    i2a(i : Int) : String {
    if i = 0 then "0" else 
        if 0 < i then i2a_aux(i) else
          "-".concat(i2a_aux(i * ~1)) 
        fi fi
    };
    
(*
    i2a_aux is an example using recursion.
*)      
    i2a_aux(i : Int) : String {
        if i = 0 then "" else 
        (let next : Int <- i / 10 in
        i2a_aux(next).concat(i2c(i - next * 10))
        )
        fi
    };

};
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
class Main inherits IO{

    lists : List <- new List;

    list : List;
    looping : Bool;                 -- looping variable for parsing objects and commands
    main_looping : Bool <- true;    -- looping variable for the main program (ends when reaching EOF)
    nextLine : String;

    -- ends when encountering "END"
    objectParsing() : Object {{
        looping <- true;
        list <- new List;

        while looping loop
        {

            -- read next line from input
            nextLine <- in_string();
            if nextLine = "END" then
                looping <- false
            else
               let tokenizer : StringTokenizer <- new StringTokenizer in {
                    tokenizer.init(nextLine);

                    let objectClass : String, object : Object in
                    {
                        -- create instance for current object
                        objectClass <- tokenizer.next();

                        if      objectClass = "Soda"        then object <- new Soda
                        else if objectClass = "Coffee"      then object <- new Coffee
                        else if objectClass = "Laptop"      then object <- new Laptop
                        else if objectClass = "Router"      then object <- new Router
                        else if objectClass = "Private"     then object <- new Private
                        else if objectClass = "Corporal"    then object <- new Corporal
                        else if objectClass = "Sergent"     then object <- new Sergent
                        else if objectClass = "Officer"     then object <- new Officer
                        else if objectClass = "Int"         then object <- new Int
                        else if objectClass = "Bool"        then object <- new Bool
                        else if objectClass = "String"      then object <- new String
                        else if objectClass = "IO"          then object <- new IO
                        else abort()
                        fi fi fi fi fi fi fi fi fi fi fi fi;
                        -- read the rest of the arguments and initialize the object, adding it to the list
                        list.add(new Utils.initObject(object, tokenizer));
                    };
               }
            fi;
        } pool;

        lists.add(list);
    }};

    -- ends when encountering EOF or load command
    commandParsing() : Object {{
        looping <- true;

        while looping loop
        {
            -- read next line from input
            nextLine <- in_string();
            if nextLine = "" then { -- after reading EOF, we close the main loop
                looping <- false;
                main_looping <- false;
            } else if nextLine = "load" then -- after reading "load", only the command loop is closed
                looping <- false
            else
                let tokenizer : StringTokenizer <- new StringTokenizer in {
                    tokenizer.init(nextLine);

                    let command : String in {
                        -- read next command
                        command <- tokenizer.next();
                        -- implementation for print command
                        if command = "print" then
                            let lists_aux : LinkedList <- lists.getList(), l : Object, index : Int in {
                                -- if command has an argument, read it and extract the list with the index 
                                if tokenizer.hasNext() then {
                                    index <- new A2I.a2i(tokenizer.next());
                                    l <- lists_aux.get(index);
                                    -- print the list
                                    case l of
                                        lst : List => out_string(lst.getList().toString());
                                    esac;

                                } else
                                    -- print all the lists, adding index to the beginning of each string
                                    while index < lists_aux.getSize() loop {
                                        index <- index + 1;
                                        l <- lists_aux.get(index);
                                        case l of
                                            lst : List => out_string(new A2I.i2a(index).concat(": ").concat(lst.getList().toString()));
                                        esac;
                                    } pool
                                fi;
                            }
                        -- implementation for the merge command
                        else if command = "merge" then
                            let index1 : Int, index2 : Int, list1 : Object, list2 : Object in {
                                -- read the indices of the lists
                                index1 <- new A2I.a2i(tokenizer.next());
                                index2 <- new A2I.a2i(tokenizer.next());

                                -- extract the lists that will be merged
                                list2 <- lists.getList().get(index2);
                                list1 <- lists.getList().get(index1);

                                -- add the result at the end of the main list
                                case list1 of
                                    l1 : List =>
                                        case list2 of
                                            l2 : List => lists.add(l1.merge(l2));                
                                        esac;
                                esac;
                                -- remove the old lists
                                lists.delete(index2);
                                lists.delete(index1);
                            }
                        -- implementation for the filterBy command
                        else if command = "filterBy" then
                            let index : Int, filter_str : String, filter_obj : Filter, l_i : Object in {
                                -- read the index of the list and the filter type
                                index <- new A2I.a2i(tokenizer.next());
                                filter_str <- tokenizer.next();
                                -- set filter object with the appropriate type
                                if filter_str = "ProductFilter" then filter_obj <- new ProductFilter
                                else if filter_str = "RankFilter" then filter_obj <- new RankFilter
                                else if filter_str = "SamePriceFilter" then filter_obj <- new SamePriceFilter
                                else abort()
                                fi fi fi;

                                -- extract the list and filter it using the filter object
                                l_i <- lists.getList().get(index);
                                case l_i of
                                    list_i : List => list_i.filterBy(filter_obj);
                                esac; 
                            }
                        -- implementation for the sortBy command
                        else if command = "sortBy" then
                            let index : Int, comparator_str : String, comparator_obj : Comparator, order : String, l_i : Object in {
                                -- read the list index and comparator type
                                index <- new A2I.a2i(tokenizer.next());
                                comparator_str <- tokenizer.next();
                                -- set comparator object with the appropriate type
                                if comparator_str = "PriceComparator" then comparator_obj <- new PriceComparator
                                else if comparator_str = "RankComparator" then comparator_obj <- new RankComparator
                                else if comparator_str = "AlphabeticComparator" then comparator_obj <- new AlphabeticComparator
                                else abort()
                                fi fi fi;

                                -- read order type
                                order <- tokenizer.next();
                                if not order = "ascendent" then
                                    if not order = "descendent" then
                                        abort()
                                    else
                                        0 -- Do nothing
                                    fi
                                else
                                    0 -- Do nothing
                                fi;

                                -- extract the list and sort it using the comparator object
                                 l_i <- lists.getList().get(index);
                                case l_i of
                                    list_i : List => list_i.sortBy(comparator_obj, order="ascendent");
                                esac;
                            }
                        else abort() -- incorrect command
                        fi fi fi fi;
                    };
                }
            fi fi;

        } pool;
    }};

    main():Object {
        while main_looping loop
        {
            objectParsing();
            commandParsing();
        } pool
    };
};
(*
   The class stringTokenizer provides an API for splitting Strings into tokens.
   Currently it can only split strings using " " as a delimiter.
*)

class StringTokenizer {

   str : String;
   hasNext : Bool;

   init(s : String) : StringTokenizer {
      {
         str <- s;
         hasNext <- true;
         self;
      }
   };

   hasNext() : Bool {
      hasNext
   };

   next() : String {
      if hasNext then
      {
         let   i : Int <- nextDelimiterIndex(),
               nextToken : String
         in
         {
            -- extract next token
            nextToken <- str.substr(0, i);
            -- set string as the part after the " " if there are characters left
            if i < str.length() then
               str <- str.substr(i + 1, str.length() - i - 1)
            else {
               str <- "";
               hasNext <- false;
            }
            fi;

            nextToken;
         };
      }
      else
         ""
      fi
   };


   -- returns the index of the next " " in the string
   nextDelimiterIndex() : Int {
      let   i : Int <- 0,
            aux_str : String <- str,
            temp : String,
            len : Int <- str.length(),
            result : Int <- len,
            looping : Bool <- true
      in
      {

         while looping loop {

            if aux_str = "" then
               looping <- false
            -- extract substrings of length 1 until " " or newline string is found
            else if aux_str.substr(i, 1) = " " then
               looping <- false
            else if aux_str.substr(i, 1) = "\n" then
                  looping <- false
            else "" -- Do nothing
            fi fi fi;


            if looping then {
               i <- i + 1;
               looping <- (i < len);
            } else
               "" -- Do nothing
            fi;
         } pool;
         -- set result as i
         result <- i;
         result;
      }
   };
};
(*******************************
 *** Classes Product-related ***
 *******************************)
class Product {
    name : String;
    model : String;
    price : Int;

    init(n : String, m: String, p : Int):SELF_TYPE {{
        name <- n;
        model <- m;
        price <- p;
        self;
    }};

    getprice():Int{ price * 119 / 100 };

    toString() : String {
        self.type_name()
            .concat("(")
            .concat(name)
            .concat(";")
            .concat(model)
            .concat(")")
    };
};

class Edible inherits Product {
    -- VAT tax is lower for foods
    getprice():Int { price * 109 / 100 };
};

class Soda inherits Edible {
    -- sugar tax is 20 bani
    getprice():Int {price * 109 / 100 + 20};
};

class Coffee inherits Edible {
    -- this is technically poison for ants
    getprice():Int {price * 119 / 100};
};

class Laptop inherits Product {
    -- operating system cost included
    getprice():Int {price * 119 / 100 + 499};
};

class Router inherits Product {};

(****************************
 *** Classes Rank-related ***
 ****************************)
class Rank {
    name : String;

    init(n : String):SELF_TYPE {{
        name <- n;
        self;
    }};

    toString():String {
        self.type_name()
            .concat("(")
            .concat(name)
            .concat(")")
    };

    getRankScore() : Int { 0 };
};

class Private inherits Rank {
    getRankScore() : Int { 40 };
};

class Corporal inherits Private {
    getRankScore() : Int { 30 };
};

class Sergent inherits Corporal {
    getRankScore() : Int { 20 };
};

class Officer inherits Sergent {
    getRankScore() : Int { 10 };
};

class Filter {
    filter(o : Object):Bool {false};
};

-- implementation for filters
class ProductFilter inherits Filter {
    filter (o : Object): Bool {
        case o of
            prod : Product => true;
            obj : Object => false;
        esac
    };
};

class RankFilter inherits Filter {
    filter (o : Object): Bool {
        case o of
            rank : Rank => true;
            obj : Object => false;
        esac
    };
};

class SamePriceFilter inherits Filter {
    filter (o : Object): Bool {
        if new ProductFilter.filter(o) then
            case o of
                prod : Product => prod@Product.getprice() = prod.getprice();
            esac
        else
            false
        fi         
    };
};



class Comparator {
    compareTo(o1 : Object, o2 : Object):Int {0};
};

-- implementation for comparators

-- price comparator returns the difference between the prices of the 2 products
class PriceComparator inherits Comparator {
    compareTo(o1 : Object, o2 : Object) : Int {
        case o1 of
        p1 : Product =>
            case o2 of
            p2 : Product => p1.getprice() - p2.getprice();
            obj2 : Object => { abort(); 0; };
            esac;
        obj1 : Object => { abort(); 0; };
        esac
    };
};

-- rank comparator returns the difference between the values of the 2 ranks
class RankComparator inherits Comparator {
    compareTo(o1 : Object, o2 : Object) : Int {
       case o1 of
        r1 : Rank =>
            case o2 of
            r2 : Rank => r2.getRankScore() - r1.getRankScore();
            obj2 : Object => { abort(); 0; };
            esac;
        obj1 : Object => { abort(); 0; };
        esac
    };
};

-- alphabetic comparator returns 1 or ~1, depending on which string is larger
-- alphaebtically
class AlphabeticComparator inherits Comparator {
    compareTo(o1 : Object, o2 : Object) : Int {
       case o1 of
        s1 : String =>
            case o2 of
            s2 : String => if s2 < s1 then 1 else ~1 fi;
            obj2 : Object => { abort(); 0; };
            esac;
        obj1 : Object => { abort(); 0; };
        esac
    };
};


-- class containing various methods
class Utils {

    -- method that initializes the object according to its type
    initObject(object : Object, tokenizer : StringTokenizer) : Object {
        case object of
            product : Product => initProduct(product, tokenizer);
            rank : Rank => initRank(rank, tokenizer);
            i : Int => initInt(tokenizer);
            b : Bool => initBool(tokenizer);
            s : String => initString(tokenizer);
            io : IO => io;
            o : Object => abort();
        esac
    };

    -- returns a product object
    initProduct(product : Product, tokenizer : StringTokenizer) : Product {
        let name : String, model : String, price : Int in
        {
            name <- tokenizer.next();
            model <- tokenizer.next();
            price <- new A2I.a2i(tokenizer.next());

            product.init(name, model, price);
        }
    };

    -- returns a rank object
    initRank(rank : Rank, tokenizer : StringTokenizer) : Rank {
        let name : String in
        {
            name <- tokenizer.next();
            rank.init(name);
        }
    };

    -- converts string to int
    initInt(tokenizer : StringTokenizer) : Int {
        new A2I.a2i(tokenizer.next())
    };

    -- converts string to bool
    initBool(tokenizer : StringTokenizer) : Bool {
        tokenizer.next() = "true"
    };

    -- retuns the parsed string
    initString(tokenizer : StringTokenizer) : String {
        tokenizer.next()
    };

    -- returns a Int object string
    intToString(i : Int) : String {
        "Int(".concat(new A2I.i2a(i)).concat(")")
    };

    -- retuns a Bool object string
    boolToString(b : Bool) : String {
        let result : String in {
            result <- "Bool(";

            if b then
                result <- result.concat("true")
            else
                result <- result.concat("false")
            fi;

            result.concat(")");        
        }
    };
};