package com.kafka.demoassignment.controller;


import com.kafka.demoassignment.dto.*;
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
            Case myCase=new Case();
            Service service=new Service();

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
            if(st.startsWith("CAS")){
                flag=0;
                myCase.setCASE_NUMBER(st.substring(3,18));
                myCase.setCASE_TYPE(st.substring(19,34));
                myCase.setCASE_CODE(st.substring(35,50));
                myCase.setCASE_START_DATE(st.substring(51,66));
                myCase.setCASE_END_DATE(st.substring(67,82));
                myCase.setCASE_AUTH_TYPE(st.substring(83,98));
                myCase.setCASE_STATUS(st.substring(99,108));
                output.setMycase(myCase);

            }

            if(st.startsWith("PAT")){
                flag=0;
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
            if(st.startsWith("SVC")){
                flag=1;
                service.setCASE_NUMBER(st.substring(3,18));
                service.setSVC_ID(st.substring(19,34));
                service.setSVC_TYPE(st.substring(35,50));
                service.setSVC_CODE(st.substring(51,66));
                service.setSVC_FAC_ID(st.substring(67,82));
                service.setSVC_FAC_NAME(st.substring(83,98));
                service.setSVC_PHY_ID(st.substring(99,114));
                service.setSVC_PHY_NAME(st.substring(115,130));
                output.setService(service);

            }
            if(flag==1){
                System.out.println(output);

            }


        }

        return output;
    }
}
