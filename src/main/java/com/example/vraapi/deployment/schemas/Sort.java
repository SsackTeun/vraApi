package com.example.vraapi.deployment.schemas;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Sort {
    private boolean empty;
    private boolean sorted;
    private boolean unsorted;
}
