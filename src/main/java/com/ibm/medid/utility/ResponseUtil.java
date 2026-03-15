package com.ibm.medid.utility;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ibm.medid.dto.WebResponse;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class ResponseUtil {
    //* success response */ 
    public <T> ResponseEntity<WebResponse<T>> successResponse(String message,T data){
        WebResponse<T> webResponse = WebResponse.<T>builder()
        .message(message)
        .data(data)
        .errors(null)
        .build();
        return ResponseEntity.ok(webResponse);
    }

    //! error response 
    public <T> ResponseEntity<WebResponse<T>> errorResponse(String message, T errors,HttpStatus status){
        WebResponse<T> webResponse = WebResponse.<T>builder()
        .message(message)
        .data(null)
        .errors(errors)
        .build();
        return ResponseEntity.status(status).body(webResponse);
    }

    //* Error response jwt entrypoint */
    public void sendError(HttpServletResponse response, String message, int status) throws IOException {
    response.setStatus(status);
    response.setContentType("application/json");

    WebResponse<Object> webResponse = WebResponse.builder()
            .message(message)
            .data(null)
            .errors(null)
            .build();

    response.getWriter().write(new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(webResponse));
}


}
