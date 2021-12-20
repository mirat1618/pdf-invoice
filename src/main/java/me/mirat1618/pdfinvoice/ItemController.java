package me.mirat1618.pdfinvoice;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import me.mirat1618.pdfinvoice.model.Item;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ItemController {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private static DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    private static DecimalFormat decimalFormatForPdf;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField measurementUnitTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField pricePerUnitTextField;

    @FXML
    private TextField totalTaxFreeTextField;

    @FXML
    private TextField taxRateTextField;

    @FXML
    private TextField taxSumTextField;

    @FXML
    private TextField totalTextField;

    @FXML
    private TextField originCountryTextField;

    @FXML
    private TextArea noteTextArea;

    public void initialize() {
        //
    }


    @FXML
    private void countSumsFields() {
        countTaxFreeSum();
        countTaxSum();
        countTotal();
    }

    private  void countTaxFreeSum() {
        try {
            int quantity = Integer.parseInt(quantityTextField.getText());
            double pricePerUnit = Double.parseDouble(pricePerUnitTextField.getText());
            totalTaxFreeTextField.setText(decimalFormat.format(quantity * pricePerUnit));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void countTaxSum() {
        try {
            double totalFaxFreeSum = Double.parseDouble(totalTaxFreeTextField.getText());
            double taxRate = Double.parseDouble(taxRateTextField.getText());
            double taxSum = totalFaxFreeSum * (taxRate / 100);
            taxSumTextField.setText(decimalFormat.format(taxSum));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void countTotal() {
        try {
            double totalFaxFreeSum = Double.parseDouble(totalTaxFreeTextField.getText());
            double taxSum = Double.parseDouble(taxSumTextField.getText());
            double total = totalFaxFreeSum + taxSum;
            totalTextField.setText(decimalFormat.format(total));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public Item getItem() {
        String title = titleTextField.getText();
        String measurementUnit = measurementUnitTextField.getText();
        String quantity = quantityTextField.getText();
        String pricePerUnit = formatSumsForPdf(pricePerUnitTextField.getText());
        String totalTaxFree = formatSumsForPdf(totalTaxFreeTextField.getText());
        String taxRate = taxRateTextField.getText();
        String taxSum = formatSumsForPdf(taxSumTextField.getText());
        String total = formatSumsForPdf(totalTextField.getText());
        String originCountry = originCountryTextField.getText();
        String note = noteTextArea.getText();

        return new Item(title, measurementUnit, quantity, pricePerUnit, totalTaxFree, taxRate, taxSum, total, originCountry, note);

    }

    private String formatSumsForPdf(String s) {
        symbols.setGroupingSeparator(' ');
        decimalFormatForPdf = new DecimalFormat("###,###.00", symbols);

        double sum;
        try {
            sum = Double.parseDouble(s);
        } catch ( NumberFormatException e) {
           return s;
        }

        try {
            s = decimalFormatForPdf.format(sum);
        } catch (IllegalArgumentException | ArithmeticException e) {
            return s;
        }
        return s;
    }
}
