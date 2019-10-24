import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class hw2 {

    private static String inputFile = "";
    private static int minFragment = 0;
    private static int maxFragment = 0;
    private static String outputFile = "";

    private void parseInput(String[] args){
        if(args!=null && args.length == 5){
            inputFile = args[1];
            minFragment = Integer.parseInt(args[2]);
            maxFragment = Integer.parseInt(args[3]);
            outputFile=args[4];
            if(minFragment>maxFragment){
                throw new IllegalArgumentException("Minimum range of fragment must be lesser than maximum range of fragment");
            }
        }
    }

    private List<String> fragmentLine(String line, List<String> fragmentedLines){
        String temp = "";
        while(true){
            if(line.length()<minFragment){
                break;
            }
            else if(line.length()==minFragment){
                fragmentedLines.add(line);
                break;
            }
            else{
                int randomSize = getRandomFragmentSize();
                if(randomSize>line.length()){
                    fragmentedLines.add(line);
                    break;
                }
                else{
                    fragmentedLines.add(line.substring(0,randomSize-1));
                    line = line.substring(randomSize);
                }
            }
        }
        return fragmentedLines;
    }

    private List<String> fragmentLines(List<String> lines){
        List<String> fragmentedLines = new ArrayList<>();
        for(String sequence : lines){
            fragmentedLines = fragmentLine(sequence,fragmentedLines);
        }
        return fragmentedLines;
    }


    public void mainController(String[] args){
        if(args == null || args.length!=5){
            System.out.println("input data should have 5 parameters");
        }
        else{
            parseInput(args);
            System.out.println("file is "+inputFile);
            List<String> lines = Utility.readFile(inputFile);
            List<String> fragmentedSequences = fragmentLines(lines);
            Utility.saveFile(fragmentedSequences,outputFile);
        }
    }

    private int getRandomFragmentSize(){
        int randomNumber = Utility.getRand().nextInt(maxFragment-minFragment+1);
        return randomNumber+minFragment;
    }

}
