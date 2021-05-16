package ru.kpfu.itis.pdf_generator.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * @author Roman Leontev
 * 10:19 16.05.2021
 * group 11-905
 */

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ListPDFJsonDTO {
    private List<PDFJsonDTO> pdfJsons;
}