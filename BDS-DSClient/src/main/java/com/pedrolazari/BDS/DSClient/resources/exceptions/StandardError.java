package com.pedrolazari.BDS.DSClient.resources.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Data
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;


}
