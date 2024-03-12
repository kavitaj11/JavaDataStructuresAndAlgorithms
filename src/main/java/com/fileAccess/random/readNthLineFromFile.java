package com.fileAccess.random;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class readNthLineFromFile {
    private static final String FILE_PATH ="inputLines.txt" ;

    //We can use Files.readAllLines() from the File API to easily read the contents of a file into memory and extract the line we desire:

    public void readNthLineFromSmallFileUsingFilesAPI(int linenum) throws IOException {
        Path resourceDirectory = Paths.get("src","main","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        String extractedLine = Files.readAllLines(Paths.get(absolutePath+ File.separator+FILE_PATH)).get(linenum-1);

        System.out.println(extractedLine);
    }
    //Large Files
    //If we need to work with large files, we should use the lines method, which returns a Stream so that we can read the file line by line:

    public void readNthLineFromLargeFileUsingFilesAPI(int linenum) throws IOException{
        Path resourceDirectory = Paths.get("src","main","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        try (Stream lines = Files.lines(Paths.get(absolutePath+File.separator+FILE_PATH))) {
            String extractedLine = (String) lines.skip(linenum-1).findFirst().get();

            System.out.println(extractedLine);
        }
    }


    public static void main(String[] args) throws IOException{
        readNthLineFromFile readline= new readNthLineFromFile();
        readline.readNthLineFromSmallFileUsingFilesAPI(4);
        readline.readNthLineFromLargeFileUsingFilesAPI(7);

    }
}
