package ru.kpfu.itis.pdf_generator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;
import java.util.List;

/**
 * @author Roman Leontev
 * 19:16 15.05.2021
 * group 11-905
 */

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PDFJsonDTO {
    private String titleOrganization;
    private String login;
    private Integer numberStudents;
    private Long numberFile;
    private String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdAt;
    private List<TableElementDTO> tableElements;
}
