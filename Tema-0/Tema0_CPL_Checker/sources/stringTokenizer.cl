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
            else
               str <- ""
            fi;

            aux_str;
         };
      }
      else
      {
         abort();
         "";
      }
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

            if aux_str.substr(i, 1) = " " then
               looping <- false
            else
               if aux_str.substr(i, 1) = "\n" then
                  looping <- false
               else "" -- Do nothing
               fi
            fi;


            if looping then {
               i <- i + 1;
               looping <- (i < len);
            } else "" -- Do nothing

            fi;
         } pool;

         result <- i;

         result;
      }
   };

};
