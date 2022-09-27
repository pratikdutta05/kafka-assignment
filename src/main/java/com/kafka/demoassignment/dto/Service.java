package com.kafka.demoassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Service {
    String CASE_NUMBER;
    String SVC_ID;
    String SVC_TYPE;
    String SVC_CODE;
    String SVC_FAC_ID;
    String SVC_FAC_NAME;
    String SVC_PHY_ID;
    String SVC_PHY_NAME;
}
