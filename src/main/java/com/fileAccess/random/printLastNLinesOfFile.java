package com.fileAccess.random;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class printLastNLinesOfFile {

    //using LinkedList
    //You can add the lines to a List, e.g. a LinkedList.
    // When the list has more than five lines, remove the first/last.
    public LinkedList<String> ReadLastNLineFromFile(String filepath, int n) throws Exception {
        FileInputStream in = new FileInputStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        LinkedList<String> lines = new LinkedList<String>();
        for (String tmp; (tmp = br.readLine()) != null; )
            if (lines.add(tmp) && lines.size() > n)
                lines.remove(0);

        return lines;
    }


    //MemoryMappedFile for printing last 5 lines:
    private static void printByMemoryMappedFile(File file) throws FileNotFoundException, IOException {
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel channel=fileInputStream.getChannel();
        ByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        buffer.position((int)channel.size());
        int count=0;
        StringBuilder builder=new StringBuilder();
        for(long i=channel.size()-1;i>=0;i--){
            char c=(char)buffer.get((int)i);
            builder.append(c);
            if(c=='\n'){
                if(count==5)break;
                count++;
                builder.reverse();
                System.out.println(builder.toString());
                builder=null;
                builder=new StringBuilder();
            }
        }
        channel.close();
    }


    private static void printByRandomAcessFile(File file) throws FileNotFoundException, IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        int lines = 0;
        StringBuilder builder = new StringBuilder();
        long length = file.length();
        length--;
        randomAccessFile.seek(length);
        for(long seek = length; seek >= 0; --seek){
            randomAccessFile.seek(seek);
            char c = (char)randomAccessFile.read();
            builder.append(c);
            if(c == '\n'){
                builder = builder.reverse();
                System.out.println(builder.toString());
                lines++;
                builder = null;
                builder = new StringBuilder();
                if (lines == 5){
                    break;
                }
            }

        }
    }

    public void ReadLastLineFromFile(String filepath) throws Exception {
            FileInputStream in = new FileInputStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine = null, tmp;
            while ((tmp = br.readLine()) != null) {
                strLine = tmp;
            }

            String lastLine = strLine;
            System.out.println(lastLine);
            in.close();
        }




    //Paths.get("c:\\demo.txt") Locate a file in a file system.
    //Files.lines() Read all lines from a file as a Stream
    //Stream.skip(long n) Returns a stream consisting of the remaining elements of this stream after discarding the first n elements of the stream
    //Stream.findFirst() Returns an Optional describing the first element of this stream.
    //Get the value from Optional and print it on console.
    //throws NoSuchElementException if input index number is out of range.
    public void java8StreamReadNthLine (int lineNumber){
        try {
                String str = Files.lines(Paths.get("c:\\demo.txt")).skip(lineNumber - 1).findFirst().get();
                System.out.println("Content at " + lineNumber + " Number:- " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


}
