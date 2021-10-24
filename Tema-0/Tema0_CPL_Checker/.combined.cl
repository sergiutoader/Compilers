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
        { abort(); 0; }  -- the 0 is needed to satisfy the typchecker
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

    set(index : Int, value : Object) : SELF_TYPE {
        let curr : Node <- head in {
            while 1 < index loop {
                index <- index - 1;
                curr <- curr.getNext();
            } pool;

            curr.setValue(value);

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

    getHead() : Node { head };
    setHead(n : Node) : Node {{ head <- n; n; }};

    getValue() : Object { head.getValue() };
    setValue(val : Object) : SELF_TYPE {{ head.setValue(val); self; }};

    getNext() : Node { head.getNext() };
    setNext(nxt : Node) : SELF_TYPE {{ head.setNext(nxt); self; }};

    getSize() : Int { size };
};
class Main inherits IO{
    -- lists : List;

    lists : List <- new List;

    list : List;
    looping : Bool;
    main_looping : Bool <- true;
    nextLine : String;

    objectParsing() : Object {{
        looping <- true;
        list <- new List;

        while looping loop
        {

            -- Read next line from input
            nextLine <- in_string();
            if nextLine = "END" then
                looping <- false
            else
               let tokenizer : StringTokenizer <- new StringTokenizer in {
                    tokenizer.init(nextLine);

                    let objectClass : String, object : Object in
                    {
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

                        list.add(new Utils.initObject(object, tokenizer));
                    };
               }
            fi;
        } pool;

        lists.add(list);
    }};


    commandParsing() : Object {{
        looping <- true;

        while looping loop
        {
            nextLine <- in_string();
            if nextLine = "" then {
                looping <- false;
                main_looping <- false;
            } else if nextLine = "load" then
                looping <- false
            else
                let tokenizer : StringTokenizer <- new StringTokenizer in {
                    tokenizer.init(nextLine);

                    let command : String in {
                        command <- tokenizer.next();
                        if command = "print" then
                            let lists_aux : LinkedList <- lists.getList(), l : Object, index : Int in {
                                if tokenizer.hasNext() then {
                                    index <- new A2I.a2i(tokenizer.next());
                                    l <- lists_aux.get(index);

                                    case l of
                                        lst : List => out_string(lst.getList().toString());
                                    esac;

                                } else
                                    while index < lists_aux.getSize() loop {
                                        index <- index + 1;
                                        l <- lists_aux.get(index);
                                        case l of
                                            lst : List => out_string(new A2I.i2a(index).concat(": ").concat(lst.getList().toString()));
                                        esac;
                                    } pool
                                fi;
                            }
                        else if command = "merge" then
                            let index1 : Int, index2 : Int, list1 : Object, list2 : Object in {
                                index1 <- new A2I.a2i(tokenizer.next());
                                index2 <- new A2I.a2i(tokenizer.next());

                                -- assuming index2 is always larger than index1
                                list2 <- lists.getList().get(index2);
                                list1 <- lists.getList().get(index1);

                                case list1 of
                                    l1 : List =>
                                        case list2 of
                                            l2 : List => lists.add(l1.merge(l2));                
                                        esac;
                                esac;

                                lists.delete(index2);
                                lists.delete(index1);
                            }
                        else if command = "filterBy" then
                            let index : Int, filter_str : String, filter_obj : Filter, l_i : Object in {
                                index <- new A2I.a2i(tokenizer.next());
                                filter_str <- tokenizer.next();
                                if filter_str = "ProductFilter" then filter_obj <- new ProductFilter
                                else if filter_str = "RankFilter" then filter_obj <- new RankFilter
                                else if filter_str = "SamePriceFilter" then filter_obj <- new SamePriceFilter
                                else abort()
                                fi fi fi;

                                l_i <- lists.getList().get(index);
                                case l_i of
                                    list_i : List => list_i.filterBy(filter_obj);
                                esac; 
                            }
                        else
                         -- TODO - implement other commands
                            ""
                        fi fi fi;
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
               aux_str : String
         in
         {
            aux_str <- str.substr(0, i);

            if i < str.length() then
               str <- str.substr(i + 1, str.length() - i - 1)
            else {
               str <- "";
               hasNext <- false;
            }
            fi;

            aux_str;
         };
      }
      else
         ""
      fi
   };


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
};

class Private inherits Rank {};

class Corporal inherits Private {};

class Sergent inherits Corporal {};

class Officer inherits Sergent {};
(* Think of these as abstract classes *)
class Comparator {
    compareTo(o1 : Object, o2 : Object):Int {0};
};

class Filter {
    filter(o : Object):Bool {true};
};

(* TODO: implement specified comparators and filters*)

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

class Utils {

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

    initProduct(product : Product, tokenizer : StringTokenizer) : Product {
        let name : String, model : String, price : Int in
        {
            name <- tokenizer.next();
            model <- tokenizer.next();
            price <- new A2I.a2i(tokenizer.next());

            product.init(name, model, price);
        }
    };

    initRank(rank : Rank, tokenizer : StringTokenizer) : Rank {
        let name : String in
        {
            name <- tokenizer.next();
            rank.init(name);
        }
    };

    initInt(tokenizer : StringTokenizer) : Int {
        new A2I.a2i(tokenizer.next())
    };

    initBool(tokenizer : StringTokenizer) : Bool {
        tokenizer.next() = "true"
    };

    initString(tokenizer : StringTokenizer) : String {
        tokenizer.next()
    };

    intToString(i : Int) : String {
        "Int(".concat(new A2I.i2a(i)).concat(")")
    };

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