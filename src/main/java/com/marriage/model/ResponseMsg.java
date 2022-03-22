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
public class ResponseMsg<T> {
    private ResponseHead head;
    private T data;
}
