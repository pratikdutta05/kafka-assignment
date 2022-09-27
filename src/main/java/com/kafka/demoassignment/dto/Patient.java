package com.kafka.demoassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Patient {
    String CASE_NUMBER;
    String PAT_ID;
    String PAT_FIRST_NAME;
    String PAT_MIDDLE_NAME;
    String PAT_LAST_NAME;
    String PAT_SEX;
    String PAT_DOB;
    String PAT_PLANE_TYPE;
    String PAT_PLAN_NAME;
}
