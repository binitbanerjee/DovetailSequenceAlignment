import java.util.List;

public class hw3 {
    private static String inputFile = "";
    private static int matchingScore = 0;
    private static int insertCost = 0;
    private static int replaceCost = 0;
    private static String outputFile = "";
    private static DoveTail doveTail = null;
    private static List<String> finalSequences = null;

    private void parseInput(String[] args){
        if(args!=null && args.length == 6){
            inputFile = args[1];
            matchingScore = Integer.parseInt(args[2]);
            insertCost = Integer.parseInt(args[4]);
            replaceCost = Integer.parseInt(args[3]);
            outputFile=args[5];
            doveTail = new DoveTail(replaceCost,insertCost,matchingScore);
        }
    }

    private void assemble(List<String> sequences,Boolean isMaxNegative){
        if(sequences.size()==1 || isMaxNegative) {
            finalSequences = sequences;
            return;
        }
        else{
            int score = Integer.MIN_VALUE;
            int max = score;
            int matched = -1;
            String temp = sequences.get(0);
            for(int index = 1;index<sequences.size();index++){
                score = doveTail.compute(temp,sequences.get(index));
                if(score>max && score>0){
                    matched = index;
                    max =score;
                }
//                max = Math.max(score,max);
//                if(score>0){
//                    sequences.remove(index);
//                    sequences.remove(0);
//                    sequences.add(0,temp+sequences.get(index));
//                    break;
//                }
            }
            if(max<0)
                assemble(sequences,true);
            else {
                temp = sequences.get(matched)+sequences.get(0);
                sequences.remove(matched);
                sequences.remove(0);
                sequences.add(0,temp);
                assemble(sequences, false);
            }
        }
    }

    private void sequenceAssembler(List<String> sequences){
        if(sequences!=null && !sequences.isEmpty()){
            assemble(sequences,false);
        }
        else{
            throw new IllegalArgumentException("Input file contains no sequence. kindly recheck the input file name");
        }
    }

    public void mainController(String[] args){
        if(args == null || args.length!=6){
            System.out.println("input data should have 6 parameters");
        }
        else{
            parseInput(args);
            List<String> sequences = Utility.readFile(inputFile);
            sequenceAssembler(sequences);
            Utility.saveFile(finalSequences,outputFile);
        }
    }

}
