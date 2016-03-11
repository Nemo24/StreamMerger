package org.mm.streamprocessing.stream;

import org.mm.streamprocessing.StreamCombiner;
import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.data.Pair;
import org.mm.streamprocessing.loader.ConsolePrintLoader;
import org.mm.streamprocessing.loader.ILoader;

public class MainUnitTest {
    public static void main(String[] args) {
        IStream<MessageData> stream1 =  FileStream.getFileFromClassPath("file1");
        IStream<MessageData> stream2 =  FileStream.getFileFromClassPath("file2");
        ILoader<Pair<MessageData,MessageData>> loader = new TestLoader();
        StreamCombiner streamCombiner = new StreamCombiner(stream1,stream2,loader);
        streamCombiner.startProcess();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
}
