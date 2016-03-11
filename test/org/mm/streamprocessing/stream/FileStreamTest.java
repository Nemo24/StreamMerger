package org.mm.streamprocessing.stream;

import org.mm.streamprocessing.data.MessageData;

public class FileStreamTest {
    public static void main(String[] args) {
        int count =0;
        IStream<MessageData> stream = FileStream.getFileFromClassPath("testmessagefile1");
        while (stream.hasNext()){
            MessageData messageData = stream.getNext();
            count++;
            if (messageData==null) break;
            System.out.println(messageData);
        }
        assert count==5:"ReadTestfrom fileStream";
    }
}
