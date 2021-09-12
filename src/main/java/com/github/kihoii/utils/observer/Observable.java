package com.github.kihoii.utils.observer;

import com.github.kihoii.controller.States;


public interface Observable {

    void addObserver(FieldUpdate fieldUpdate);

    void notifyObservers(States state);

}
