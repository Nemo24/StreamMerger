package org.mm.streamprocessing.stream;

public interface IStream<T> {
    public T getNext();
    public Boolean hasNext();

}
