package com.marriage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHead {
    private String requestId;
    private String respStatus;
    private String respCode;
    private String respDesc;
}
