package ru.kpfu.itis.pdf_generator.service;

import ru.kpfu.itis.pdf_generator.dto.ListPDFJsonDTO;

/**
 * @author Roman Leontev
 * 19:13 15.05.2021
 * group 11-905
 */

public interface FileGeneratorService {
    void createPDF(ListPDFJsonDTO listPDFJsonDTO);
}
