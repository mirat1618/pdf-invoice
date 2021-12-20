package me.mirat1618.pdfinvoice.model;

public class Item {
    private String title;
    private String measurementUnit;
    private String quantity;
    private String pricePerUnit;
    private String totalTaxFree;
    private String taxRate;
    private String taxSum;
    private String total;
    private String originCountry;
    private String note;

    public Item(String title, String measurementUnit, String quantity, String pricePerUnit, String totalTaxFree, String taxRate, String taxSum, String total, String originCountry, String note) {
        this.title = title;
        this.measurementUnit = measurementUnit;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalTaxFree = totalTaxFree;
        this.taxRate = taxRate;
        this.taxSum = taxSum;
        this.total = total;
        this.originCountry = originCountry;
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(String pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getTotalTaxFree() {
        return totalTaxFree;
    }

    public void setTotalTaxFree(String totalTaxFree) {
        this.totalTaxFree = totalTaxFree;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(String taxSum) {
        this.taxSum = taxSum;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", measurementUnit='" + measurementUnit + '\'' +
                ", quantity='" + quantity + '\'' +
                ", pricePerUnit='" + pricePerUnit + '\'' +
                ", totalTaxFree='" + totalTaxFree + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", taxSum='" + taxSum + '\'' +
                ", total='" + total + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
