package org.mm.streamprocessing.loader;

public class ConsolePrintLoader<T> implements ILoader<T> {
    @Override
    public void loadData(T data) {
        System.out.println(data);
    }
}
