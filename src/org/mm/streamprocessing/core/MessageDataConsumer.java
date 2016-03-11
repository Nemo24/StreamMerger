package org.mm.streamprocessing.core;

import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.data.MessageDataTypes;

import java.util.concurrent.BlockingQueue;

public class MessageDataConsumer implements Runnable {
    BlockingQueue<MessageData> sourceQueue;
    BlockingQueue<MessageData> typeSpecificTargetQueue;
    MessageDataTypes type;

    public MessageDataConsumer(BlockingQueue<MessageData> sourceQueue, BlockingQueue<MessageData> typeSpecificTargetQueue, MessageDataTypes type) {
        this.sourceQueue = sourceQueue;
        this.typeSpecificTargetQueue = typeSpecificTargetQueue;
        this.type = type;
    }

    @Override
    public void run() {
        while (true){
            MessageData data = sourceQueue.peek();
            synchronized (sourceQueue){
                if (data!=null && data.getType()==type) {
                    try {
                        MessageData data1 = sourceQueue.take();
                        typeSpecificTargetQueue.put(data1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
