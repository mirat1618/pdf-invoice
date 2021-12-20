package me.mirat1618.pdfinvoice.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import me.mirat1618.pdfinvoice.MainApplication;
import me.mirat1618.pdfinvoice.model.Invoice;
import org.apache.commons.io.IOUtils;


public class PdfGenerator {
    public static BaseFont baseFont;

    /*
     iText 5 createFont() method can't read files from resources directory of an executable .jar file.
     So, we copy the font file from 'resources' directory and extract it to the outside world (into a current directory) and grab it from there.
    */
    public static void setBaseFont() {
        // check if current directory has the Cyrillic font
        File fontFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "Arial_Cyrillic.ttf");

        if(!fontFile.exists()) { // if not
            // read the font file from 'resources' folder
            InputStream inputStream = MainApplication.class.getResourceAsStream("Arial_Cyrillic.ttf");
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(System.getProperty("user.dir") + System.getProperty("file.separator") + "Arial_Cyrillic.ttf");
                IOUtils.copy(inputStream,outputStream); // move the font file from 'resources' into a current directory
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Get the font from a current directory
            try {
                baseFont = BaseFont.createFont(fontFile.getPath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            } catch(DocumentException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BaseFont getBaseFont() {
        return baseFont;
    }

    public static void generate(Invoice invoice) {
        Document document = new Document();
        document.setPageSize(PageSize.A4);

        /*
            Top margin (rest)    1.9cm
            Left margin          1.9cm
            Right margin         1.32cm
            Bottom margin		 3.67cm
        */

        document.setMargins(19,13,19, 36);

        setBaseFont();

        Font regularFont = new Font(baseFont, 11, Font.NORMAL);
        Font boldItalicFont = new Font(baseFont,11, Font.BOLDITALIC);
        Font boldFont = new Font(baseFont,11, Font.BOLD);
        Font mediumFont = new Font(baseFont,10, Font.NORMAL);
        Font smallFont = new Font(baseFont,7, Font.NORMAL);
        Font boldUnderlinedFont = new Font(baseFont,11, Font.BOLD | Font.UNDERLINE);

        PdfWriter pdfWriter = null;

        try {
            String currentLocalDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_HH-mm-ss"));
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(currentLocalDateTime)); // use current Date and Time as a PDF file name
            pdfWriter.setPdfVersion(PdfWriter.VERSION_1_7);
        } catch(IOException e) {
            e.printStackTrace();
        } catch(DocumentException e) {
            e.printStackTrace();
        }

        pdfWriter.setPageEvent(new NoteFooter());

        document.open();


        PdfPTable layoutTable = new PdfPTable(12);
        // layoutTable.setSpacingBefore(100);

        layoutTable.setWidthPercentage(100);


        /* Bill no. and dating */
        addCell(layoutTable, "Счёт №", boldItalicFont, 5, 1, Element.ALIGN_RIGHT);
        addCell(layoutTable, invoice.getNumber(), boldItalicFont);
        addCell(layoutTable, invoice.getDatedBy(), boldItalicFont, 6, 1, Element.ALIGN_LEFT);
        /* Bill no. and dating */

        /* Seller */
        addCell(layoutTable, "Продавец", boldUnderlinedFont, 2);
        addCell(layoutTable, invoice.getSeller().getTitle(), boldUnderlinedFont, 10);
        /* Seller */

        /* Seller address */
        addCell(layoutTable, "Адрес", regularFont, 2);
        addCell(layoutTable, invoice.getSeller().getAddress(), regularFont, 10);
        /* Seller address */

        /* Sellers' tax ID */
        addCell(layoutTable, "Индентификационный номер (ИНН)", regularFont, 5);
        addCell(layoutTable, invoice.getSeller().getTaxId(), boldFont, 7);
        /* Sellers' tax ID */

        /* Sellers' billing account */
        addCell(layoutTable, "Расчётный счёт", regularFont, 2);
        addCell(layoutTable, invoice.getSeller().getBillingAccount(), regularFont, 10);
        /* Sellers' billing account */

        /* Corresponding bank account */
        addCell(layoutTable, "Кор. счёт", regularFont, 2);
        addCell(layoutTable, invoice.getSeller().getCorrespondentBillingAccount(), regularFont, 3);
        addCell(layoutTable, "БИК", regularFont, 1);
        addCell(layoutTable, invoice.getSeller().getBankingId(), regularFont, 6);
        /* Corresponding bank account */

        /* Shipper */
        addCell(layoutTable, "Грузоотправитель и его адрес он же", regularFont, 12);
        addCell(layoutTable, "Грузополучатель и его адрес", regularFont, 4);
        addCell(layoutTable, invoice.getBuyer().getShipperAddress(), regularFont, 8);
        /* Shipper */

        addCell(layoutTable, " ", regularFont, 12);

        /* Invoice document */
        addCell(layoutTable, "К платёжно-расчётному документу", regularFont, 5);
        addCell(layoutTable, "№" + invoice.getDocumentNumber() +" от " + invoice.getDocumentDatedBy(), regularFont, 7);
        /* Invoice document */

        /* Purchaser */
        addCell(layoutTable, "Покупатель", boldUnderlinedFont, 2);

        PdfPCell lineCell = new PdfPCell();
        Chunk customerNumberSuperscript = new Chunk(invoice.getBuyer().getInternalNumber());
        customerNumberSuperscript.setTextRise(5f);
        customerNumberSuperscript.setFont(smallFont);
        lineCell.addElement(customerNumberSuperscript);
        layoutTable.addCell(lineCell);

        addCell(layoutTable, invoice.getBuyer().getTitle(), boldUnderlinedFont, 9);
        /* Purchaser */


        /* Purchaser's address */
        addCell(layoutTable, "Адрес", regularFont, 2);
        addCell(layoutTable, invoice.getBuyer().getAddress(), regularFont, 10);
        /* Purchaser's address */

        /* Purchaser's tax ID */
        addCell(layoutTable, "Индентификационный номер (ИНН)", regularFont, 5);
        addCell(layoutTable, invoice.getBuyer().getTaxId(), regularFont, 7);
        /* Purchaser's tax ID */


        /* Products table */
        String[] tableHeaders = {"Наименование товара", "Ед. изм.", "Количество", "Цена за единицу измерения", "Стоимость товаров всего без налога", "Налоговая ставка", "Сумма налога", "Стоимость товаров всего с учетом налога", "Страна происхождения №ТД"};
        String[] tableHeadersNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] row1 = {invoice.getItem().getTitle(),
                invoice.getItem().getMeasurementUnit(),
                invoice.getItem().getQuantity(),
                invoice.getItem().getPricePerUnit(),
                invoice.getItem().getTotalTaxFree(),
                invoice.getItem().getTaxRate(),
                invoice.getItem().getTaxSum(),
                invoice.getItem().getTotal(),
                invoice.getItem().getOriginCountry(),
               };
        String[][] tableRows = {tableHeaders, tableHeadersNumbers, row1};

        lineCell = new PdfPCell();
        lineCell.setColspan(12);
        lineCell.setBorder(0);
        PdfPTable innerTable = new PdfPTable(9);
        innerTable.setWidthPercentage(100);

        addRow(innerTable, tableHeaders, smallFont, Element.ALIGN_CENTER);
        addRow(innerTable, tableHeadersNumbers, smallFont, Element.ALIGN_CENTER);
        addRow(innerTable, row1, smallFont);

        lineCell.addElement(innerTable);
        layoutTable.addCell(lineCell);
        /* Products table */

        addCell(layoutTable, " ", regularFont, 12);

        /* Note */
        addCell(layoutTable, "Примечание", regularFont, 2, 1, Element.ALIGN_LEFT, Element.ALIGN_TOP);
        addCell(layoutTable, invoice.getItem().getNote(), regularFont, 10);
        /* Note */

        addEmptyRows(layoutTable, " ", regularFont, 3);

        /* Line */
        LineSeparator line = new LineSeparator();
        lineCell = new PdfPCell();
        lineCell.setColspan(12);
        lineCell.addElement(line);
        lineCell.setBorder(0);
        layoutTable.addCell(lineCell);
        /* Line */

        /* Total price */
        addCell(layoutTable, "Всего к оплате:", regularFont, 5, 1, Element.ALIGN_RIGHT);
        addCell(layoutTable, invoice.getItem().getTotalTaxFree(), regularFont, 2);
        addCell(layoutTable, invoice.getItem().getTaxSum(), regularFont, 2);
        addCell(layoutTable, invoice.getItem().getTotal(), regularFont, 3);
        /* Total price */

        addEmptyRows(layoutTable, " ", regularFont, 1);

        /* Signatures field */
        addCell(layoutTable, "Руководитель предприятия: _________ " + invoice.getSeller().getFactoryManager(), mediumFont, 6,1, Element.ALIGN_LEFT);
        addCell(layoutTable, "Главный бухгалтер: _________ " + invoice.getSeller().getChiefAccountant(), mediumFont, 6, 1, Element.ALIGN_RIGHT);
        /* Signatures field */

        addEmptyRows(layoutTable, " ", regularFont, 3);

        /* Stamp field */
        addCell(layoutTable, "ВЫДАЛ:", mediumFont, 1);
        addCell(layoutTable, "", regularFont, 4);
        addCell(layoutTable, "(подпись ответственного лица от продавца)", mediumFont, 7);
        /* Stamp field */

        try {
            document.add(layoutTable);
        } catch(DocumentException e) {
            e.printStackTrace();
        }

        document.close();
    }

    private static void addCell(PdfPTable table, String text, Font font) {
        addCell(table, text, font, 1, 1);
    }

    private static void addCell(PdfPTable table, String text, Font font, int colspan) {
        addCell(table, text, font, colspan, 1);
    }

    private static void addCell(PdfPTable table, String text, Font font, int colspan, int rowspan) {
        addCell(table, text, font, colspan, rowspan, Element.ALIGN_LEFT, Element.ALIGN_MIDDLE);
    }

    private static void addCell(PdfPTable table, String text, Font font, int colspan, int rowspan, int horizontalAlignment) {
        addCell(table, text, font, colspan, rowspan, horizontalAlignment, Element.ALIGN_MIDDLE);
    }


    private static void addCell(PdfPTable table, String text, Font font, int colspan, int rowspan, int horizontalAlignment, int verticalAlignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(verticalAlignment);

        cell.setBorder(0);

        table.addCell(cell);
    }

    private static void addRow(PdfPTable table, String[] rows, Font font) {
        addRow(table, rows, font, Element.ALIGN_LEFT);
    }

    private static void addRow(PdfPTable table, String[] rows, Font font, int horizontalAlignment) {
        PdfPCell tableCell;

        for(String row : rows) {
            tableCell = new PdfPCell(new Phrase(row, font));
            tableCell.setHorizontalAlignment(horizontalAlignment);
            table.addCell(tableCell);
        }
    }

    private static void addEmptyRows(PdfPTable table, String text, Font font, int quantity) {
        for(int i=0; i<quantity; i++)
            addCell(table, text, font, 12);

    }
}
