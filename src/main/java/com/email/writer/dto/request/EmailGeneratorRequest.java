package com.email.writer.dto.request;

import lombok.Data;

@Data
public class EmailGeneratorRequest {
    private String emailContent;
    private String tone;
}
 