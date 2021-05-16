package ru.kpfu.itis.pdf_generator.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.pdf_generator.dto.ListPDFJsonDTO;
import ru.kpfu.itis.pdf_generator.service.FileGeneratorService;

/**
 * @author Roman Leontev
 * 19:19 15.05.2021
 * group 11-905
 */

@RestController
public class PDFGeneratorController {

    @Autowired
    private FileGeneratorService fileGeneratorService;

    @ApiOperation("создание pdf")
    @PostMapping(value = "/create_pdf",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createPDF(@RequestBody ListPDFJsonDTO list) {
        System.out.println(list);
        try {
            if (list == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            fileGeneratorService.createPDF(list);
        } catch (IllegalStateException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}