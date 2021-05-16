package ru.kpfu.itis.pdf_generator.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.pdf_generator.dto.ListPDFJsonDTO;
import ru.kpfu.itis.pdf_generator.dto.PDFJsonDTO;
import ru.kpfu.itis.pdf_generator.dto.TableElementDTO;
import ru.kpfu.itis.pdf_generator.service.FileGeneratorService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Roman Leontev
 * 19:15 15.05.2021
 * group 11-905
 */

@Service
public class PDFGeneratorService implements FileGeneratorService {

    Path path = Paths.get("/Users/romanleontev/Documents/GitHub/pdf_generator/src/main/java/ru/kpfu/itis/pdf_generator/data/img_kfu.png");

    private static Font bold;

    private static Font normal;

    @Override
    public void createPDF(ListPDFJsonDTO listPDFJsonDTO) {
        BaseFont bf;
        try {
            bf = BaseFont.createFont("src/main/resources/fonts/times_roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            throw new IllegalStateException(e);
        }
        normal = new Font(bf, 12, Font.NORMAL);
        bold = new Font(bf, 12, Font.BOLD);
        Document document = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));

            document.open();

            addContent(document, listPDFJsonDTO, writer);

            document.close();

        } catch (DocumentException | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void addContent(Document document, ListPDFJsonDTO listPDFJsonDTO, PdfWriter writer) {
        for (PDFJsonDTO pdfJsonDTO : listPDFJsonDTO.getPdfJsons()) {
            try {

                PdfPTable table = createHeader();

                document.add(table);

                PdfPTable table1 = createTableWithMainInfo(pdfJsonDTO);

                document.add(table1);

                PdfPTable table2 = createTableParticipants(pdfJsonDTO);

                document.add(table2);

                document.newPage();

            } catch (DocumentException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private PdfPTable createTableParticipants(PDFJsonDTO pdfJsonDTO) {

        PdfPTable table2 = new PdfPTable(6);

        PdfPCell c3 = new PdfPCell(new Phrase("Сформирован", bold));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c3);

        c3 = new PdfPCell(new Phrase("Оформлен", bold));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c3);

        c3 = new PdfPCell(new Phrase("Зачислил", bold));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c3);

        c3 = new PdfPCell(new Phrase("Комментарий", bold));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c3);

        c3 = new PdfPCell(new Phrase("ФИО, Должность", bold));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c3);

        c3 = new PdfPCell(new Phrase("IP-адрес", bold));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c3);

        for (TableElementDTO tableElementDTO : pdfJsonDTO.getTableElements()) {
            Chunk chunk = new Chunk(String.valueOf(tableElementDTO.getDateFormed()));
            table2.addCell(String.valueOf(chunk));

            chunk = new Chunk(String.valueOf(tableElementDTO.getDateRegistered()));
            table2.addCell(String.valueOf(chunk));

            chunk = new Chunk(String.valueOf(tableElementDTO.getDateDeposited()));
            table2.addCell(String.valueOf(chunk));

            chunk = new Chunk(tableElementDTO.getComment());
            table2.addCell(String.valueOf(chunk));

            chunk = new Chunk(tableElementDTO.getFullNameAndPosition());
            table2.addCell(String.valueOf(chunk));

            chunk = new Chunk(tableElementDTO.getIpAddress());
            table2.addCell(String.valueOf(chunk));
        }

        return table2;
    }

    private PdfPTable createHeader() {
        try {
            float[] pointColumnWidths = {500F, 150F};
            PdfPTable table = new PdfPTable(pointColumnWidths);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            PdfPCell c1 = new PdfPCell();
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell();
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            Image img = Image.getInstance(path.toAbsolutePath().toString());
            img.setAlignment(Element.ALIGN_LEFT);
            img.scaleAbsoluteHeight(130);
            img.scaleAbsoluteWidth(130);

            table.addCell(img);

            Paragraph paragraph1 = new Paragraph("Подготовленный отчет по данным\nпо № 10323010/250920/0007140", normal);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);

            table.addCell(paragraph1);

            return table;

        } catch (IOException | DocumentException e) {
            throw new IllegalStateException(e);
        }
    }

    private PdfPTable createTableWithMainInfo(PDFJsonDTO pdfJsonDTO) {
        PdfPTable table = new PdfPTable(2);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        for (int i = 0; i < 2; i++) {
            PdfPCell c2 = new PdfPCell();
            c2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c2);
        }

        Paragraph paragraph = new Paragraph("Институт:", normal);
        table.addCell(paragraph);

        paragraph = new Paragraph(pdfJsonDTO.getTitleOrganization());
        table.addCell(paragraph);

        paragraph = new Paragraph("Логин:", normal);
        table.addCell(paragraph);

        paragraph = new Paragraph(pdfJsonDTO.getLogin());
        table.addCell(paragraph);

        paragraph = new Paragraph("Количество студентов:", normal);
        table.addCell(paragraph);

        paragraph = new Paragraph(String.valueOf(pdfJsonDTO.getNumberStudents()));
        table.addCell(paragraph);

        paragraph = new Paragraph("Номер отчёта:", normal);
        table.addCell(paragraph);

        paragraph = new Paragraph(String.valueOf(pdfJsonDTO.getNumberFile()));
        table.addCell(paragraph);

        paragraph = new Paragraph("Тип отчёта:", normal);
        table.addCell(paragraph);

        paragraph = new Paragraph(pdfJsonDTO.getType());
        table.addCell(paragraph);

        return table;
    }
}
