package me.mirat1618.pdfinvoice;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import me.mirat1618.pdfinvoice.model.Buyer;
import me.mirat1618.pdfinvoice.model.Invoice;
import me.mirat1618.pdfinvoice.model.Item;
import me.mirat1618.pdfinvoice.model.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class InvoiceController {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @FXML
    private TextField numberTextField;

    @FXML
    private DatePicker datedByDatePicker;

    @FXML
    private TextField documentNumberTextField;

    @FXML
    private DatePicker documentDatedByDatePicker;

    public void initialize() {
        datedByDatePicker.setValue(LocalDate.now());
        documentDatedByDatePicker.setValue(LocalDate.now().minusMonths(3));
    }

    public Invoice getInvoice(Seller seller, Buyer buyer, Item item) {
        String number = numberTextField.getText();
        String datedBy = datedByDatePicker.getValue() == null ? "" : datedByDatePicker.getValue().format(dateTimeFormatter);
        String documentNumber = documentNumberTextField.getText();
        String documentDatedBy = documentDatedByDatePicker.getValue() == null ? "" : documentDatedByDatePicker.getValue().format(dateTimeFormatter);

        return new Invoice(number, datedBy, documentNumber, documentDatedBy, buyer, seller, item);
    }
}
