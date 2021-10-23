
class Main inherits IO{
    -- lists : List;

    list : List <- new List;
    looping : Bool <- true;
    nextLine : String;


    initProduct(name : String, tokenizer : StringTokenizer) : Product {
        let product : Product <- new Product, model : String, price : Int in
        {
            model <- tokenizer.next();
            price <- new A2I.a2i(tokenizer.next());
            product.init(name, model, price);
            product;
        }
    };

    main():Object {
        while looping loop
        {

            -- Read next line from input
            nextLine <- in_string();
            if nextLine = "END" then
                looping <- false
            else
               let tokenizer : StringTokenizer <- new StringTokenizer in {
                    tokenizer.init(nextLine);

                    let name : String, product : Product in
                    {
                        name <- tokenizer.next();

                        if      name = "Soda"   then product <- initProduct(name, tokenizer)
                        else if name = "Coffee" then product <- initProduct(name, tokenizer)
                        else if name = "Laptop" then product <- initProduct(name, tokenizer)
                        else if name = "Router" then product <- initProduct(name, tokenizer)
                        else {abort(); "";}
                        fi fi fi fi;

                        list.add(product);
                        out_string(product.toString().concat("\n"));
                    };
               }
            fi;
        } pool
    };
};
