package me.mirat1618.pdfinvoice;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import me.mirat1618.pdfinvoice.data.SellerData;
import me.mirat1618.pdfinvoice.model.Seller;

public class SellerController {
    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea addressTextArea;

    @FXML
    private TextField taxIdTextField;

    @FXML
    private TextField billingAccountTextField;

    @FXML
    private TextField correspondentBillAccountTextField;

    @FXML
    private TextField bankingIdTextField;

    @FXML
    private TextField factoryManagerTextField;

    @FXML
    private TextField chiefAccountantTextField;

    public void initialize() {
        Seller defaultSeller = SellerData.loadDefaultSeller();
        titleTextField.setText(defaultSeller.getTitle());
        addressTextArea.setText(defaultSeller.getAddress());
        taxIdTextField.setText(String.valueOf(defaultSeller.getTaxId()));
        billingAccountTextField.setText(defaultSeller.getBillingAccount());
        correspondentBillAccountTextField.setText(String.valueOf(defaultSeller.getCorrespondentBillingAccount()));
        bankingIdTextField.setText(String.valueOf(defaultSeller.getBankingId()));
        factoryManagerTextField.setText(defaultSeller.getFactoryManager());
        chiefAccountantTextField.setText(defaultSeller.getChiefAccountant());
    }

    @FXML
    private void updateDefaultSeller() {
        SellerData.updateDefaultSeller(getSeller());
    }

    public Seller getSeller() {
        String title = titleTextField.getText();
        String address = addressTextArea.getText();
        String taxId = taxIdTextField.getText();
        String billingAccount = billingAccountTextField.getText();
        String correspondingBillingAccount = correspondentBillAccountTextField.getText();
        String bankingId = bankingIdTextField.getText();
        String factoryManager = factoryManagerTextField.getText();
        String chiefAccountant = chiefAccountantTextField.getText();

        return new Seller(title, address, taxId, billingAccount, correspondingBillingAccount, bankingId, factoryManager, chiefAccountant);
    }
}
