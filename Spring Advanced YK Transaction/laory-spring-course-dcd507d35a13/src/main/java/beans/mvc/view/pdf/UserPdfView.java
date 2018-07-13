package beans.mvc.view.pdf;

import beans.models.User;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class UserPdfView extends AbstractPdfView {

    protected void buildPdfDocument(Map model, Document document,
                                    PdfWriter pdfWriter, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        List lstUsers = (List) model.get("users");
        Table userTable = new Table(4);
        userTable.setWidth(90);
        userTable.setBorderWidth(1);
        userTable.addCell("№");
        userTable.addCell("Id");
        userTable.addCell("Name");
        userTable.addCell("Birthday");
        int count = 0;
        for (Iterator iter = lstUsers.iterator(); iter.hasNext();) {
            User user = (User) iter.next();
            userTable.addCell(new Integer(++count).toString());
            userTable.addCell(new Long(user.getId()).toString()); //
            userTable.addCell(user.getName()); //
            userTable.addCell(user.getBirthday().toString()); //
        }
        document.add(userTable);
    }
}
