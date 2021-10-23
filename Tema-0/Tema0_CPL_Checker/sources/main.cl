
class Main inherits IO{
    -- lists : List;

    list : List <- new List;
    looping : Bool <- true;
    nextLine : String;

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

                    let objectClass : String, object : Object in
                    {
                        objectClass <- tokenizer.next();

                        if      objectClass = "Soda"   then object <- new Soda
                        else if objectClass = "Coffee" then object <- new Coffee
                        else if objectClass = "Laptop" then object <- new Laptop
                        else if objectClass = "Router" then object <- new Router
                        else if objectClass = "Private" then object <- new Private
                        else if objectClass = "Corporal" then object <- new Corporal
                        else if objectClass = "Sergent" then object <- new Sergent
                        else if objectClass = "Officer" then object <- new Officer
                        else if objectClass = "Int" then object <- new Int
                        else if objectClass = "Bool" then object <- new Bool
                        else if objectClass = "String" then object <- new String
                        else if objectClass = "IO" then object <- new IO
                        else abort()
                        fi fi fi fi fi fi fi fi fi fi fi fi;

                        list.add(new Utils.initObject(object, tokenizer));
                    };
               }
            fi;
        } pool;

        looping <- true;

        while looping loop
        {
            nextLine <- in_string();
            if nextLine = "" then
                looping <- false
            else
                let tokenizer : StringTokenizer <- new StringTokenizer in {
                    tokenizer.init(nextLine);

                    let command : String in {
                        command <- tokenizer.next();
                        if command = "print" then
                        {
                            -- TODO - check if command has argument, only show the requested list
                            out_string(list.toString());
                        } else
                            -- TODO - implement other commands
                            ""
                        fi;
                    };
                }
            fi;

        } pool;
    }};
};
