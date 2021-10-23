
class Main inherits IO{
    -- lists : List;

    list : List <- new List;
    looping : Bool <- true;
    nextLine : String;


    initProduct(product : Product, tokenizer : StringTokenizer) : Product {
        let name : String, model : String, price : Int in
        {
            name <- tokenizer.next();
            model <- tokenizer.next();
            price <- new A2I.a2i(tokenizer.next());

            product.init(name, model, price);
            product;
        }
    };

    main():Object {{
        while looping loop
        {

            -- Read next line from input
            nextLine <- in_string();
            if nextLine = "END" then
                looping <- false
            else
               let tokenizer : StringTokenizer <- new StringTokenizer in {
                    tokenizer.init(nextLine);

                    let thing : String, product : Product in
                    {
                        thing <- tokenizer.next();

                        if      thing = "Soda"   then product <- new Soda
                        else if thing = "Coffee" then product <- new Coffee
                        else if thing = "Laptop" then product <- new Laptop
                        else if thing = "Router" then product <- new Router
                        else {abort(); "";}
                        fi fi fi fi;

                        list.add(initProduct(product, tokenizer));
                    };
               }
            fi;
        } pool;
        
        out_string(list.toString());
        nextLine <- in_string();
    }};
};
