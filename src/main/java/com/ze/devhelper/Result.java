package com.ze.devhelper;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result {
    int status = 0;
    String message = "";

    public Result() {
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
