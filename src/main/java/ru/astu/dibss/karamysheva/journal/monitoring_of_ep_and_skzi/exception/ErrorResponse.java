package ru.astu.dibss.karamysheva.journal.monitoring_of_ep_and_skzi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private int status;

    private String error;

    private String message;

}
