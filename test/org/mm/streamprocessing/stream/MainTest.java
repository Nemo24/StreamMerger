package org.mm.streamprocessing.stream;

import org.mm.streamprocessing.StreamCombiner;
import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.data.Pair;
import org.mm.streamprocessing.loader.ConsolePrintLoader;
import org.mm.streamprocessing.loader.ILoader;

public class MainTest {
    public static void main(String[] args) {
        IStream<MessageData> stream1 =  FileStream.getFileFromClassPath("channel1");
        IStream<MessageData> stream2 =  FileStream.getFileFromClassPath("channel2");
        ILoader<Pair<MessageData,MessageData>> loader = new ConsolePrintLoader<>();
        StreamCombiner streamCombiner = new StreamCombiner(stream1,stream2,loader);
        streamCombiner.startProcess();
    }
}
