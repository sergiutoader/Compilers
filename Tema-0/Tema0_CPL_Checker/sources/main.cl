
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
                                            lst : List => {
                                                    out_string(new A2I.i2a(index).concat(": ").concat(lst.getList().toString()));        
                                            };
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
                        else
                         -- TODO - implement other commands
                            ""
                        fi fi;
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
