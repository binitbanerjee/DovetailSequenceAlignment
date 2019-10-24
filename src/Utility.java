import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Utility {

    private static Random rand = null;

    public static Random getRand(){
        if(rand==null){
            rand = new Random();
        }
        return rand;
    }

    public static void saveFile(List<String> sequences,String outputFile){
        try {
                File file = new File(outputFile);
                if(file.exists()){
                    file.delete();
                }
                List<String> fastaFormattedFile = new ArrayList<>();
            List<String> splittedSequence;
            StringBuilder temp ;
                String tempSequence;
                for(int index = 0; index<sequences.size();index++){
                    temp = new StringBuilder();
                    tempSequence = sequences.get(index);
                    splittedSequence = new ArrayList<>();
                    for(int charIndex = 0; charIndex<tempSequence.length();charIndex++){
                        temp.append(tempSequence.charAt(charIndex));
                        if((charIndex+1)==79 || ((charIndex+1-79)%80==0)){
                            splittedSequence.add(temp.toString());
                            temp = new StringBuilder();
                        }
                    }
                    if(temp.length()>0){
                        splittedSequence.add(temp.toString());
                    }
                    temp = new StringBuilder(">");
                    for(String s: splittedSequence){
                        temp.append(s).append("\n");
                    }
                    fastaFormattedFile.add(temp.toString());
                }
                Files.write(Paths.get(outputFile),
                        fastaFormattedFile,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
                System.out.println("Output saved in File: "+ outputFile);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static List<String> readFile(String fileName)
    {
        List<String> lines = Collections.emptyList();
        try
        {
            System.out.println(fileName);
            lines = Files.readAllLines(Paths.get(fileName),
                    StandardCharsets.UTF_8);
            List<String> formattedLines = new ArrayList<>();
            StringBuilder compactedSequence = new StringBuilder();
            for(String s: lines){
                compactedSequence.append(s.replaceAll("\n",""));
            }
            formattedLines = Arrays.asList(compactedSequence.toString().split(">"));
            List<String> line = new ArrayList<>();
            for(String s:formattedLines){
                if(!s.isEmpty() || s.length()>0){
                    line.add(s);
                }
            }
            return line;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

}
