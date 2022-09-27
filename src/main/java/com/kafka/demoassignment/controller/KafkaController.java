package com.kafka.demoassignment.controller;


import com.kafka.demoassignment.dto.Output;
import com.kafka.demoassignment.dto.Patient;
import com.kafka.demoassignment.dto.Subscriber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.*;


@RestController
public class KafkaController {

    @GetMapping("/api/readFile")
    public Output readFileAndUpdate() throws IOException {

        File file = new File("src/main/resources/HealthAuth-20220909.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        Output output=new Output();
        int flag=0;
        while ((st = br.readLine()) != null){

            Subscriber subscriber=new Subscriber();
            Patient patient=new Patient();
            if(st.startsWith("SUB")){
                flag=0;
                subscriber.setCASE_NUMBER(st.substring(3,18));
                subscriber.setMEM_ID(st.substring(19,34));
                subscriber.setMEM_FIRST_NAME(st.substring(35,50));
                subscriber.setMEM_MIDDLE_NAME(st.substring(51,66));
                subscriber.setMEM_LAST_NAME(st.substring(67,82));
                subscriber.setMEM_ADD_1(st.substring(83,98));
                subscriber.setMEM_ADD_2(st.substring(99,114));
                subscriber.setMEM_CITY(st.substring(115,130));
                subscriber.setMEM_PIN(st.substring(131,146));
                output.setSubscriber(subscriber);
            }
            if(st.startsWith("PAT")){
                flag=1;
                patient.setCASE_NUMBER(st.substring(3,18));
                patient.setPAT_ID(st.substring(19,34));
                patient.setPAT_FIRST_NAME(st.substring(35,50));
                patient.setPAT_MIDDLE_NAME(st.substring(51,66));
                patient.setPAT_LAST_NAME(st.substring(67,82));
                patient.setPAT_SEX(st.substring(83,98));
                patient.setPAT_DOB(st.substring(99,114));
                patient.setPAT_PLANE_TYPE(st.substring(115,130));
                patient.setPAT_PLAN_NAME(st.substring(131,135));
                output.setPatient(patient);

            }
            if(flag==1){
                System.out.println(output);

            }


        }

        return output;
    }
}
