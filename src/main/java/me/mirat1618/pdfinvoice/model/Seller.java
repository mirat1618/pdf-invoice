package me.mirat1618.pdfinvoice.model;

public class Seller {
    private String title;
    private String address;
    private String taxId;
    private String billingAccount;
    private String correspondentBillingAccount;
    private String bankingId;
    private String factoryManager;
    private String chiefAccountant;

    public Seller(String title, String address, String taxId, String billingAccount, String correspondentBillingAccount, String bankingId, String factoryManager, String chiefAccountant) {
        this.title = title;
        this.address = address;
        this.taxId = taxId;
        this.billingAccount = billingAccount;
        this.correspondentBillingAccount = correspondentBillingAccount;
        this.bankingId = bankingId;
        this.factoryManager = factoryManager;
        this.chiefAccountant = chiefAccountant;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }

    public String getCorrespondentBillingAccount() {
        return correspondentBillingAccount;
    }

    public void setCorrespondentBillingAccount(String correspondentBillingAccount) {
        this.correspondentBillingAccount = correspondentBillingAccount;
    }

    public String getBankingId() {
        return bankingId;
    }

    public void setBankingId(String bankingId) {
        this.bankingId = bankingId;
    }

    public String getFactoryManager() {
        return factoryManager;
    }

    public void setFactoryManager(String factoryManager) {
        this.factoryManager = factoryManager;
    }

    public String getChiefAccountant() {
        return chiefAccountant;
    }

    public void setChiefAccountant(String chiefAccountant) {
        this.chiefAccountant = chiefAccountant;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", taxId=" + taxId +
                ", billingAccount='" + billingAccount + '\'' +
                ", correspondentBillingAccount='" + correspondentBillingAccount + '\'' +
                ", bankingId=" + bankingId +
                ", factoryManager='" + factoryManager + '\'' +
                ", chiefAccountant='" + chiefAccountant + '\'' +
                '}';
    }
}
