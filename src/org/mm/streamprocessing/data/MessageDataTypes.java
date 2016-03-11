package org.mm.streamprocessing.data;

public enum MessageDataTypes
{
    R,G,B;
    public static int SIZE = MessageDataTypes.values().length;

    public static void main(String[] args) {
        for (MessageDataTypes i :MessageDataTypes.values()){
            System.out.println(i.toString());
        }
    }
}
