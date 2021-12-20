package me.mirat1618.pdfinvoice.model;

public class Invoice {
    private String number;
    private String datedBy;
    private String documentNumber;
    private String documentDatedBy;
    private Buyer buyer;
    private Seller seller;
    private Item item;

    public Invoice(String number, String datedBy, String documentNumber, String documentDatedBy, Buyer buyer, Seller seller, Item item) {
        this.number = number;
        this.datedBy = datedBy;
        this.documentNumber = documentNumber;
        this.documentDatedBy = documentDatedBy;
        this.buyer = buyer;
        this.seller = seller;
        this.item = item;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDatedBy() {
        return datedBy;
    }

    public void setDatedBy(String datedBy) {
        this.datedBy = datedBy;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentDatedBy() {
        return documentDatedBy;
    }

    public void setDocumentDatedBy(String documentDatedBy) {
        this.documentDatedBy = documentDatedBy;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "number='" + number + '\'' +
                ", datedBy='" + datedBy + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", documentDatedBy='" + documentDatedBy + '\'' +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", item=" + item +
                '}';
    }
}
