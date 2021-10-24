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