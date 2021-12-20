package me.mirat1618.pdfinvoice;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import me.mirat1618.pdfinvoice.model.Buyer;
import me.mirat1618.pdfinvoice.model.Invoice;
import me.mirat1618.pdfinvoice.model.Item;
import me.mirat1618.pdfinvoice.model.Seller;
import me.mirat1618.pdfinvoice.pdf.PdfGenerator;
import java.util.Map;

public class MainController {
    @FXML
    TabPane tabPane;

    @FXML
    Tab invoiceTab;

    @FXML
    Tab buyerTab;

    @FXML
    Tab sellerTab;

    @FXML
    Tab itemTab;


    @FXML
    GridPane invoiceGridPane;

    @FXML
    GridPane buyerGridPane;

    @FXML
    GridPane sellerGridPane;

    @FXML
    GridPane itemGridPane;

    @FXML
    private InvoiceController invoiceController; //  <fx:include fx:id="invoice" source="invoice-view.fxml"/> - fx:id value is used to create its Controller class:  invoice -> invoiceController

    @FXML
    private BuyerController buyerController;

    @FXML
    private SellerController sellerController;

    @FXML
    private ItemController itemController;

    private Map<Tab, GridPane> tabGridPaneBinding;

    public void initialize() {
        tabGridPaneBinding = Map.of(invoiceTab, invoiceGridPane,
                buyerTab, buyerGridPane,
                sellerTab, sellerGridPane,
                itemTab, itemGridPane);

    }

    @FXML
    public void tabPaneClick() {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        // go through TabPane/GridPane pairs
        for(Map.Entry<Tab, GridPane> tabAndGridPane : tabGridPaneBinding.entrySet()) {
            if(tabAndGridPane.getKey() == selectedTab) // find the tab that was selected
                 tabAndGridPane.getValue().setVisible(true); // make its respective GridPane visible
            else
                tabAndGridPane.getValue().setVisible(false); // hide other GridPanes
        }
    }

    @FXML
    public void generatePDF() {
        Seller seller = sellerController.getSeller();
        Buyer buyer = buyerController.getBuyer();
        Item item = itemController.getItem();
        Invoice invoice = invoiceController.getInvoice(seller, buyer, item);
        PdfGenerator.generate(invoice);
    }
}