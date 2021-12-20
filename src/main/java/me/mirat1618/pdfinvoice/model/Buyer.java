package me.mirat1618.pdfinvoice.model;

public class Buyer {
    private String title;
    private String internalNumber;
    private String address;
    private String taxId;
    private String shipperAddress;

    public Buyer(String title, String internalNumber, String address, String taxId, String shipperAddress) {
        this.title = title;
        this.internalNumber = internalNumber;
        this.address = address;
        this.taxId = taxId;
        this.shipperAddress = shipperAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(String internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "title='" + title + '\'' +
                ", internalNumber='" + internalNumber + '\'' +
                ", address='" + address + '\'' +
                ", taxId='" + taxId + '\'' +
                ", shipperAddress='" + shipperAddress + '\'' +
                '}';
    }
}
