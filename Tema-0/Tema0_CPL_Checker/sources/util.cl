
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