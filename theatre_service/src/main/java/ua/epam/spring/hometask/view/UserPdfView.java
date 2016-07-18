package ua.epam.spring.hometask.view;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import ua.epam.spring.hometask.domain.User;

public class UserPdfView extends AbstractPdfView {

    protected void buildPdfDocument(Map model,
                                    Document document, PdfWriter writer, HttpServletRequest req,
                                    HttpServletResponse resp) throws Exception {

        Set<User> users = (Set<User>) model.get("usersList");

        Paragraph header = new Paragraph(new Chunk("Generate Pdf USing Spring Mvc",FontFactory.getFont(FontFactory.HELVETICA, 20)));
        document.add(header);

        for(User user : users) {
            Paragraph by = new Paragraph(new Chunk("User " + user.getFirstName() + " " + user.getLastName(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            document.add(by);
        }


    }

}

