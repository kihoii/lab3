package com.github.kihoii.utils.observer;

public interface Observable {

    void addObserver(FieldUpdate fieldUpdate);

    void notifyObservers();

}
