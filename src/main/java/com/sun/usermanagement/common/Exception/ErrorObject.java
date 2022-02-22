package com.sun.usermanagement.common.Exception;

import lombok.Data;

@Data
public class ErrorObject {

    public int status;
    public String message;
    public Object debug;
}
