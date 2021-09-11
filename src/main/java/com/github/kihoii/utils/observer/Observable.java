package com.github.kihoii.utils.observer;

import com.github.kihoii.utils.enums.States;

import java.io.IOException;

public interface Observable {

    void addObserver(FieldUpdate fieldUpdate);

    void notifyObservers(States state) throws IOException;

}
