package me.mirat1618.pdfinvoice.data;

import me.mirat1618.pdfinvoice.model.Seller;
import java.sql.*;

public class SellerData {
    private static Connection connection = DatabaseConnection.getConnection();

    public static Seller loadDefaultSeller() {
        String title, address, factoryManager, chiefAccountant, billingAccount, correspondentBillingAccount, taxId, bankingId;

        title =  address = factoryManager = chiefAccountant = billingAccount = correspondentBillingAccount = taxId = bankingId = null;

        try(PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM default_seller LIMIT 1")) {
            ResultSet selectResultSet = selectStatement.executeQuery();

            while(selectResultSet.next()) {
                title = selectResultSet.getString("title");
                address = selectResultSet.getString("address");
                taxId = selectResultSet.getString("tax_id");
                billingAccount = selectResultSet.getString("billing_account");
                correspondentBillingAccount = selectResultSet.getString("correspondent_billing_account");
                bankingId = selectResultSet.getString("banking_id");
                factoryManager = selectResultSet.getString("factory_manager");
                chiefAccountant = selectResultSet.getString("chief_accountant");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return new Seller(title, address, taxId, billingAccount, correspondentBillingAccount, bankingId, factoryManager, chiefAccountant);
    }

    public static void updateDefaultSeller(Seller updatedDefaultSeller) {
        try(PreparedStatement updateStatement = connection.prepareStatement("UPDATE default_seller SET " +
                "title = ?, " +
                "address = ?, " +
                "tax_id = ?, " +
                "billing_account = ?, " +
                "correspondent_billing_account = ?," +
                "banking_id = ?, " +
                "factory_manager = ?, " +
                "chief_accountant = ?")) {

            updateStatement.setString(1, updatedDefaultSeller.getTitle());
            updateStatement.setString(2, updatedDefaultSeller.getAddress());
            updateStatement.setString(3, updatedDefaultSeller.getTaxId());
            updateStatement.setString(4, updatedDefaultSeller.getBillingAccount());
            updateStatement.setString(5, updatedDefaultSeller.getCorrespondentBillingAccount());
            updateStatement.setString(6, updatedDefaultSeller.getBankingId());
            updateStatement.setString(7, updatedDefaultSeller.getFactoryManager());
            updateStatement.setString(8, updatedDefaultSeller.getChiefAccountant());

            updateStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
