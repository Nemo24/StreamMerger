package org.mm.streamprocessing.data;

public class Pair<T,U> {
    T data1;
    U data2;
    public Pair(T data1,U data2) {
        this.data1 = data1;
        this.data2 = data2;
        }

    public T getData1() {
        return data1;
    }

    public U getData2() {
        return data2;
    }

    @Override
    public String toString() {
        return "("+ data1+","+data2+")";
    }
}
