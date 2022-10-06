package com.kafka.demoassignment.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.kafka.demoassignment.dto.*;
import com.kafka.demoassignment.processor.EventStreamBuilder;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


@RestController
public class KafkaController {

    private static final String TOPIC = "auth-topic";

    private static final Logger LOG = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate<String, Output> kafkaTemplate;


    @Autowired
    private EventStreamBuilder eventStreamBuilder;


    @GetMapping("/api/readFile")
    public String readFileAndUpdate() throws IOException {

        File folder = new File("/Users/pratikdutta/Documents/demo-assignment/src/main/java/com/kafka/demoassignment/files");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                getOutput(file);

            }
        }
       eventStreamBuilder.process(new StreamsBuilder());
        return "Successfull";
    }

    private void getOutput(File file) throws IOException {
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
                subscriber.setCASE_NUMBER(st.substring(3,18).trim());
                subscriber.setMEM_ID(st.substring(19,34).trim());
                subscriber.setMEM_FIRST_NAME(st.substring(35,50).trim());
                subscriber.setMEM_MIDDLE_NAME(st.substring(51,66).trim());
                subscriber.setMEM_LAST_NAME(st.substring(67,82).trim());
                subscriber.setMEM_ADD_1(st.substring(83,98).trim());
                subscriber.setMEM_ADD_2(st.substring(99,114).trim());
                subscriber.setMEM_CITY(st.substring(115,130).trim());
                subscriber.setMEM_PIN(st.substring(131,146).trim());
                output.setSubscriber(subscriber);
            }
            if(st.startsWith("CAS")){
                flag=0;
                myCase.setCASE_NUMBER(st.substring(3,18).trim());
                myCase.setCASE_TYPE(st.substring(19,34).trim());
                myCase.setCASE_CODE(st.substring(35,50).trim());
                myCase.setCASE_START_DATE(st.substring(51,66).trim());
                myCase.setCASE_END_DATE(st.substring(67,82).trim());
                myCase.setCASE_AUTH_TYPE(st.substring(83,98).trim());
                myCase.setCASE_STATUS(st.substring(99,108).trim());
                output.setMycase(myCase);

            }

            if(st.startsWith("PAT")){
                flag=0;
                patient.setCASE_NUMBER(st.substring(3,18).trim());
                patient.setPAT_ID(st.substring(19,34).trim());
                patient.setPAT_FIRST_NAME(st.substring(35,50).trim());
                patient.setPAT_MIDDLE_NAME(st.substring(51,66).trim());
                patient.setPAT_LAST_NAME(st.substring(67,82).trim());
                patient.setPAT_SEX(st.substring(83,98).trim());
                patient.setPAT_DOB(st.substring(99,114).trim());
                patient.setPAT_PLANE_TYPE(st.substring(115,130).trim());
                patient.setPAT_PLAN_NAME(st.substring(131,135).trim());
                output.setPatient(patient);

            }
            if(st.startsWith("SVC")){
                flag=1;
                service.setCASE_NUMBER(st.substring(3,18).trim());
                service.setSVC_ID(st.substring(19,34).trim());
                service.setSVC_TYPE(st.substring(35,50).trim());
                service.setSVC_CODE(st.substring(51,66).trim());
                service.setSVC_FAC_ID(st.substring(67,82).trim());
                service.setSVC_FAC_NAME(st.substring(83,98).trim());
                service.setSVC_PHY_ID(st.substring(99,114).trim());
                service.setSVC_PHY_NAME(st.substring(115,130).trim());
                output.setService(service);

            }
            if(flag==1){
               LOG.info(output.toString());
                kafkaTemplate.send(TOPIC, output);

            }


        }

    }
}
