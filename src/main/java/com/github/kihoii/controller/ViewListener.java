package com.github.kihoii.controller;

import java.io.IOException;

public interface ViewListener {

    void onAction(ActionType actionType) throws IOException;

}
