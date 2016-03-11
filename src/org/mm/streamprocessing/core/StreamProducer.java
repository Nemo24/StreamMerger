package org.mm.streamprocessing.core;

import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.stream.IStream;
import java.util.concurrent.BlockingQueue;

public class StreamProducer implements Runnable {
   IStream<MessageData> stream;
   BlockingQueue<MessageData> messageDataBlockingQueue;

    public StreamProducer(IStream<MessageData> stream, BlockingQueue<MessageData> messageDataBlockingQueue) {
        this.stream = stream;
        this.messageDataBlockingQueue = messageDataBlockingQueue;
    }

    @Override
    public void run() {
        while (stream.hasNext()){
             MessageData data = stream.getNext();
            if (data!=null){
                try {
                    messageDataBlockingQueue.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
