package com.kafka.demoassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Subscriber {
    String CASE_NUMBER;
    String MEM_ID;
    String MEM_FIRST_NAME;
    String MEM_MIDDLE_NAME;
    String MEM_LAST_NAME;
    String MEM_ADD_1;
    String MEM_ADD_2;
    String MEM_CITY;
    String MEM_PIN;
}
