package com.github.kihoii.utils.observer;

import com.github.kihoii.utils.enums.States;

import java.io.IOException;

public interface FieldUpdate {

    void handleEvent(States state) throws IOException;

}
