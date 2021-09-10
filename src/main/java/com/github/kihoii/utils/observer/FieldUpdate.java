package com.github.kihoii.utils.observer;

import com.github.kihoii.utils.enums.States;

public interface FieldUpdate {

    void handleEvent(States state);

}
