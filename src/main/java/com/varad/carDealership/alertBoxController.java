package com.varad.carDealership;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class alertBoxController {
    @FXML
    Text text;
    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }
    protected void setLabel(String textString){
        text.setText(textString);
    }
}
