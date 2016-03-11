package org.mm.streamprocessing.stream;

import org.mm.streamprocessing.data.MessageData;
import org.mm.streamprocessing.data.Pair;
import org.mm.streamprocessing.loader.ILoader;

public class TestLoader implements ILoader<Pair<MessageData,MessageData>> {
    /***
     * TestLoader uses file1,file2 for test1. Each pair has the same UniqueNumber. We assert this
     * to test ordering
     * @param data
     */
    int count=0;
    @Override
    public void loadData(Pair<MessageData, MessageData> data) {
        MessageData data1 = data.getData1();
        MessageData data2 = data.getData2();
        assert (data1.getType()==data2.getType()): "assertion of types failed!!";
        assert (data1.getUniqueNumber()==data2.getUniqueNumber()): "assertion of types failed!!";
        System.out.println("pair number "+ count +"succesful");
        count++;
    }
}
