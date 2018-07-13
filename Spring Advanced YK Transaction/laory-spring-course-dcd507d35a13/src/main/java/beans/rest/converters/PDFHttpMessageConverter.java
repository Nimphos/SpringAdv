package beans.rest.converters;

import beans.models.Ticket;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.*;
import org.springframework.http.converter.*;

import com.lowagie.text.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PDFHttpMessageConverter implements HttpMessageConverter<List<Ticket>> {

    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        if (mediaType != null && !MediaType.ALL.equals(mediaType)) {
            Iterator var2 = this.getSupportedMediaTypes().iterator();

            MediaType supportedMediaType;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                supportedMediaType = (MediaType)var2.next();
            } while(!supportedMediaType.isCompatibleWith(mediaType));

            return true;
        } else {
            return true;
        }
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_PDF);
    }

    @Override
    public List<Ticket> read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(List<Ticket> tickets, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        final HttpHeaders headers = httpOutputMessage.getHeaders();
        headers.setContentType(mediaType);

        try {
            httpOutputMessage.getBody().write(objectToPdfDocument(tickets));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        httpOutputMessage.getBody().flush();
    }

    private byte[] objectToPdfDocument(List<Ticket> tickets) throws DocumentException {

        Document document = new Document();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, stream);

        document.open();

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
        for (Iterator iter = tickets.iterator(); iter.hasNext();) {
            Ticket ticket = (Ticket) iter.next();
            userTable.addCell(new Integer(++count).toString());
            userTable.addCell(new Long(ticket.getId()).toString());
            userTable.addCell(ticket.getEvent().getName());
            userTable.addCell(ticket.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            userTable.addCell(ticket.getSeats());
            userTable.addCell(ticket.getUser().getName());
            userTable.addCell(ticket.getPrice().toString());
        }
        document.add(userTable);

        document.close();

        return stream.toByteArray();
    }
}
