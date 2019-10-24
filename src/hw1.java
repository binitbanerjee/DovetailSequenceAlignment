import java.util.List;
import java.util.ArrayList;

public class hw1
{
    private static String outputFile = "";
    private static int pa = 0;
    private static int pc = 0;
    private static int pg = 0;
    private static int pt = 0;
    private static double p = 0.0;
    private static int k = 0;
    private static int n = 0;
    private StringBuilder baseSequence = new StringBuilder();
    private StringBuilder mutatedSequence;

    private void parseInput(String[] args){
        if(args!=null && args.length == 9){
            n =Integer.parseInt(args[1]);
            pa = Integer.parseInt(args[2]);
            pc = Integer.parseInt(args[3])+pa;
            pg = Integer.parseInt(args[4])+pc;
            pt = Integer.parseInt(args[5])+pg;
            k = Integer.parseInt(args[6]);
            p = Float.parseFloat(args[7]);
            outputFile=args[8];
        }
    }

    private String generateSequence(int sequenceNumber){
        String sequence = "";
        if(sequenceNumber==0){
            for(int charIndex=0;charIndex<n;charIndex++){
                baseSequence.append(generateRandomCharacter());
            }
            sequence = baseSequence.toString();
        }
        else{
            sequence = generateMutatedSequence();
        }
        return sequence;
    }

    private String generateMutatedSequence(){
        double randomNumber = 0.0;
        mutatedSequence = new StringBuilder();
        String baseSeq = baseSequence.toString();
        for(int index=0;index<baseSeq.length();index++) {
            randomNumber = Utility.getRand().nextDouble();
            if (randomNumber <= p / 2) {
                mutatedSequence.append(generateRandomCharacter());
            }
            else if(randomNumber>p){
                mutatedSequence.append(baseSeq.charAt(index));
            }
        }
        return mutatedSequence.toString();
    }

    private List<String> generateSequences(){
        List<String> sequences = new ArrayList<>();
        for(int sequenceIndex=0;sequenceIndex<k;sequenceIndex++){
            sequences.add(generateSequence(sequenceIndex));
        }
        return sequences;
    }

    private char generateRandomCharacter(){
        int randomNumber = Utility.getRand().nextInt(pt);
        if(randomNumber<=pa)
            return 'A';
        else if(randomNumber>pa && randomNumber<=pc)
            return 'C';
        else if(randomNumber>pc && randomNumber<=pg)
            return 'G';
        else
            return 'T';
    }

    public void mainController(String[] args){
        if(args == null || args.length<9){
            System.out.println("input data should have 8 parameters");
        }
        else{
            parseInput(args);
            Utility.saveFile(generateSequences(),outputFile);
        }
    }
}