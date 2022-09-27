package com.kafka.demoassignment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Output {
    private Subscriber subscriber;
    private Patient patient;
}
