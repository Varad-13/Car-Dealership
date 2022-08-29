package com.varad.carDealership;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class alertBoxController {
    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }
}
