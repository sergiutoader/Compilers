
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
