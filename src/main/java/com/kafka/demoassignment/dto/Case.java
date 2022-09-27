package com.kafka.demoassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Case {
    String CASE_NUMBER;
    String CASE_TYPE;
    String CASE_CODE;
    String CASE_START_DATE;
    String CASE_END_DATE;
    String CASE_AUTH_TYPE;
    String CASE_STATUS;

}
