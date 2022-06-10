package proj;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Parser{
    BufferedReader fileReader;
    FileWriter fileWriter;

    List<String> sids = new ArrayList<>();
    public Parser(String fileName) throws IOException {
        fileReader = new BufferedReader(new FileReader(fileName));
        findSids();
        printSorted();
    }
    private void findSids() throws IOException{
        String line;

        int i = 0;
        while((line = fileReader.readLine()) != null){
            int sidStart = line.indexOf("sid=/");
            if(sidStart == -1){
                System.out.println("No sid in line " + i);
                ++i;
                continue;
            }
            int sidEnd = line.indexOf("/&", sidStart);
            if(sidEnd == -1){
                System.out.println("No sid field end in line " + i);
                ++i;
                continue;
            }
            sids.add(line.substring(sidStart + 5, sidEnd));
            ++i;
        }
    }
    private void printSorted() throws IOException {
        if(sids.size() == 0){
            System.out.println("No sids in the log file");
            return;
        }
        Collections.sort(sids);
        fileWriter = new FileWriter("sids.txt");
        sids.forEach(
                e -> {
                    try {
                        fileWriter.write(e + '\n');
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        fileWriter.close();
    }
}
