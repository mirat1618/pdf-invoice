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
        if(isTableEmpty()) {
            insertDefaultSeller(updatedDefaultSeller);
        } else {
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE default_seller SET " +
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertDefaultSeller(Seller newSeller) {
        try(PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO default_seller (title, " +
                "address," +
                "tax_id," +
                "billing_account," +
                "correspondent_billing_account," +
                "banking_id," +
                "factory_manager," +
                "chief_accountant)" +
                "VALUES (?,?,?,?,?,?,?,?)")) {

            insertStatement.setString(1, newSeller.getTitle());
            insertStatement.setString(2, newSeller.getAddress());
            insertStatement.setString(3, newSeller.getTaxId());
            insertStatement.setString(4, newSeller.getBillingAccount());
            insertStatement.setString(5, newSeller.getCorrespondentBillingAccount());
            insertStatement.setString(6, newSeller.getBankingId());
            insertStatement.setString(7, newSeller.getFactoryManager());
            insertStatement.setString(8, newSeller.getChiefAccountant());

            insertStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTableEmpty() {
        int count = 0;
        try(PreparedStatement selectStatement = connection.prepareStatement("SELECT COUNT(*) AS number_of_rows FROM default_seller")) {
            ResultSet selectResultSet = selectStatement.executeQuery();
            while(selectResultSet.next()) {
                count = selectResultSet.getInt("number_of_rows");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count < 1;
    }
}
