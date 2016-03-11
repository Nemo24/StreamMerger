package org.mm.streamprocessing.stream;

import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.data.MessageDataTypes;

import java.io.*;

/**
 * Test Stream implementation which reads off file.
 */
public class FileStream implements IStream<MessageData> {
    BufferedReader reader;
    private FileStream(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public Boolean hasNext() {
        return true; //mimicing an infinite stream. We could return false at end of file read if we wish to terminate
    }

    @Override
    public MessageData getNext() {
        try {
            String line = reader.readLine();
            if (line!=null){
                String[] message = line.split(",");
                return new MessageData(MessageDataTypes.valueOf(message[0]),Integer.parseInt(message[1]),Integer.parseInt(message[2]));
            }
        } catch (IOException e) {

        }
        return null; //Mimic an infinite stream by returning null at end of fileread
    }
    public static FileStream getFileFromClassPath(String fileName){
        InputStream fis =  FileStream.class.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader( new InputStreamReader(fis));
        FileStream stream = new FileStream(reader);
        return stream;
    }
}
