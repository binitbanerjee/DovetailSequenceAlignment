public class Code
{
    
    public static void main(String[] args){
        String[] a = {"hw 3" ,"/home/binit/Documents/MS/BIF/hw1/src/output2.fasta", "1" ,"-1", "-3",
                "/home/binit/Documents/MS/BIF/hw1/src/output3.fasta"};
//        String[] a = {"hw 1" ,"1000", "20", "30","40","70" ,"20", ".05","output.fasta"};
//                "/home/binit/Documents/MS/BIF/hw1/src/outputfile3.fasta"};

//        String[] a = {"hw 2" ,"/home/binit/Documents/MS/BIF/hw1/src/output.fasta", "100", "150","/home/binit/Documents/MS/BIF/hw1/src/output2.fasta"};
        args = a;
       if(args!=null && args.length>0){
           try {
               switch (args[0]) {
                   case "hw 1":
                       /**
                        *Sample code to compile: javac *.java
                        *Sample code to run: java Code 'hw 1' 15 20 30 40 70 20 0.5 'output.txt'
                        */
                       hw1 h = new hw1();
                       h.mainController(args);
                       break;
                   case "hw 2":
                       /**
                        *Sample code to compile: javac *.java
                        *Sample code to run java Code 'hw 1' 15 20 30 40 70 20 0.5 'output.txt'
                        */
                       hw2 h2 = new hw2();
                       h2.mainController(args);
                       break;
                   case "hw 3":
                       hw3 h3 = new hw3();
                       h3.mainController(args);
                       break;
                   default:
                       System.out.println("input param not valid");
                       break;
               }
           }
           catch (Exception ex){
               System.out.println(ex.getMessage());
               ex.printStackTrace();
           }
       }
    }
}