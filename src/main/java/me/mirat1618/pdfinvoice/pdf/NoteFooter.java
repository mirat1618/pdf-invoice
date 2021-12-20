package me.mirat1618.pdfinvoice.pdf;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;

public class NoteFooter extends PdfPageEventHelper {
    private static BaseFont baseFont = null;
    private static Font regularFont = null;

    {
        baseFont = PdfGenerator.getBaseFont();
        regularFont = new Font(baseFont, 11, Font.NORMAL);
    }

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, firstLineFooter(), (document.left()), document.bottom() + 10, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, secondLineFooter(), (document.left()), document.bottom(), 0);
    }

    private Phrase firstLineFooter() {
        Phrase p = new Phrase("Примечания: 1. Без печати недействителен.", regularFont);
        return p;
    }

    private Phrase secondLineFooter() {
        Phrase p = new Phrase("2. Первый экземпляр - покупателю, второй экземпляр - продавцу", regularFont);
        return p;
    }
}
