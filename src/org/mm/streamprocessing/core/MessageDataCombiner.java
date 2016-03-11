package org.mm.streamprocessing.core;

import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.data.Pair;
import org.mm.streamprocessing.loader.ILoader;

import java.util.concurrent.BlockingQueue;

public class MessageDataCombiner implements Runnable {
    BlockingQueue<MessageData> messageDataQueue1;
    BlockingQueue<MessageData> messageDataQueue2;
    ILoader<Pair<MessageData,MessageData>> loader;

    public MessageDataCombiner(BlockingQueue<MessageData> messageDataQueue1, BlockingQueue<MessageData> messageDataQueue2, ILoader<Pair<MessageData, MessageData>> loader) {
        this.messageDataQueue1 = messageDataQueue1;
        this.messageDataQueue2 = messageDataQueue2;
        this.loader = loader;
    }

    @Override
    public void run() {
        while (true){
            try {
                MessageData data1 = messageDataQueue1.take();
                MessageData data2 = messageDataQueue2.take();
                Pair<MessageData,MessageData> pair = new Pair<>(data1,data2);
                loader.loadData(pair);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
