package org.mm.streamprocessing.data;

public class MessageData {
    int channel;
    int uniqueNumber;
    MessageDataTypes type;

    public MessageData(MessageDataTypes type, int channel, int uniqueNumber ) {
        this.channel = channel;
        this.uniqueNumber = uniqueNumber;
        this.type = type;
    }

    public int getChannel() {
        return channel;
    }

    public int getUniqueNumber() {
        return uniqueNumber;
    }

    public MessageDataTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString()+channel+"_"+uniqueNumber;
    }
}
