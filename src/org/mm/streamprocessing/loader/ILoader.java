package org.mm.streamprocessing.loader;

public interface ILoader<T> {
    public void loadData(T data);
}
