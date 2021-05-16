package ru.kpfu.itis.pdf_generator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

/**
 * @author Roman Leontev
 * 23:01 15.05.2021
 * group 11-905
 */

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TableElementDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateFormed;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateRegistered;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date dateDeposited;
    private String comment;
    private String fullNameAndPosition;
    private String ipAddress;
}
