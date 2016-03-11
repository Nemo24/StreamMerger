package org.mm.streamprocessing;

import org.mm.streamprocessing.core.MessageDataCombiner;
import org.mm.streamprocessing.core.MessageDataConsumer;
import org.mm.streamprocessing.core.StreamProducer;
import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.data.MessageDataTypes;
import org.mm.streamprocessing.data.Pair;
import org.mm.streamprocessing.loader.ILoader;
import org.mm.streamprocessing.stream.IStream;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/***
 * Main entry class
 * startProcess starts producer of streams to queues and also starts various consumers
 */
public class StreamCombiner {
    IStream<MessageData> stream1;
    IStream<MessageData> stream2;
    ILoader<Pair<MessageData,MessageData>> loader;
    final int CHANNELQUEUELIMIT = 5; // increase limit for a bigger buffer limit
    final int TARGETQUEUELIMIT = 3; // increase limit for a bigger buffer limit
    BlockingQueue<MessageData> queueForChannel1 = new LinkedBlockingQueue<>(CHANNELQUEUELIMIT);
    BlockingQueue<MessageData> queueForChannel2 = new LinkedBlockingQueue<>(CHANNELQUEUELIMIT);

    public StreamCombiner(IStream<MessageData> stream1, IStream<MessageData> stream2,ILoader<Pair<MessageData,MessageData>> loader) {
        this.stream1 = stream1;
        this.stream2 = stream2;
        this.loader = loader;
    }
    public void startProcess(){
        StreamProducer producer1 = new StreamProducer(stream1,queueForChannel1);
        StreamProducer producer2 = new StreamProducer(stream2,queueForChannel2);
        Thread t1 = new Thread(producer1);
        Thread t2 = new Thread(producer2);
        t1.start();
        t2.start();

        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        for (MessageDataTypes type:MessageDataTypes.values()){
            BlockingQueue<MessageData> targetBlockingQueue1 = new LinkedBlockingQueue<>(TARGETQUEUELIMIT);
            BlockingQueue<MessageData> targetBlockingQueue2 = new LinkedBlockingQueue<>(TARGETQUEUELIMIT);
            MessageDataConsumer consumer1 = new MessageDataConsumer(queueForChannel1,targetBlockingQueue1,type);
            MessageDataConsumer consumer2 = new MessageDataConsumer(queueForChannel2,targetBlockingQueue2,type);
            MessageDataCombiner combiner = new MessageDataCombiner(targetBlockingQueue1,targetBlockingQueue2,loader);
            Thread consumerThread1 = new Thread(consumer1);
            Thread consumerThread2 = new Thread(consumer2);
            consumerThread1.start();
            consumerThread2.start();
            Thread combinerThread  = new Thread(combiner);
            combinerThread.start();
        }
    }

}
