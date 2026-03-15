package com.ibm.medid.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class WebResponse<T> {

    private T data;

    private String message;

    private String errors;
}
