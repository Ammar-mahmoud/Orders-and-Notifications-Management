package com.example.Orders.and.Notifications.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private boolean status;
    private String message;
    public Response(){}
}
