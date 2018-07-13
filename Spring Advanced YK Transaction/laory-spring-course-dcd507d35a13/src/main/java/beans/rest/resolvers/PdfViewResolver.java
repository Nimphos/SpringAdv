package beans.rest.resolvers;

import beans.mvc.view.pdf.TicketPdfView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class PdfViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        TicketPdfView view = new TicketPdfView();
        return view;
    }

}