package beans.mvc.view.pdf;

import beans.models.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TicketPdfView extends AbstractPdfView {

    protected void buildPdfDocument(Map model, Document document,
                                    PdfWriter pdfWriter, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        List lstTickets = (List) model.get("tickets");
        Table userTable = new Table(4);
        userTable.setWidth(90);
        userTable.setBorderWidth(1);
        userTable.addCell("№");
        userTable.addCell("Id");
        userTable.addCell("Event");
        userTable.addCell("Date and time");
        userTable.addCell("Seats");
        userTable.addCell("User");
        userTable.addCell("Price");
        int count = 0;
        for (Iterator iter = lstTickets.iterator(); iter.hasNext();) {
            Ticket ticket = (Ticket) iter.next();
            userTable.addCell(new Integer(++count).toString());
            userTable.addCell(new Long(ticket.getId()).toString()); //
            userTable.addCell(ticket.getEvent().getName()); //
            userTable.addCell(ticket.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); //
            userTable.addCell(ticket.getSeats()); //
            userTable.addCell(ticket.getUser().getName()); //
            userTable.addCell(ticket.getPrice().toString()); //
        }
        document.add(userTable);
    }
}
