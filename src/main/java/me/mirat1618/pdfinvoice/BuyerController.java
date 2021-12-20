package me.mirat1618.pdfinvoice;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import me.mirat1618.pdfinvoice.model.Buyer;

public class BuyerController {
    @FXML
    public TextField titleTextField;

    @FXML
    private TextField internalNumberTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField taxIdTextField;

    @FXML
    private TextArea shipperAddressTextArea;

    public Buyer getBuyer() {
        String title = titleTextField.getText();
        String internalNumber = internalNumberTextField.getText();
        String address = addressTextField.getText();
        String taxId = taxIdTextField.getText();
        String shipperAddress = shipperAddressTextArea.getText();

        return new Buyer(title, internalNumber, address, taxId, shipperAddress);
    }
}
