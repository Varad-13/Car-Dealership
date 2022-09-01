package com.varad.carDealership;

import javafx.application.Platform;
import javafx.fxml.FXML;
public class alertBoxController {
    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }
}
