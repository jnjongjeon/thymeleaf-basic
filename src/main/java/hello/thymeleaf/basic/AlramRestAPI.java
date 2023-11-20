package hello.thymeleaf.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public class AlramRestAPI {

    public static void callAuthApi(String otpUrl, RestTemplate restTemplate) {
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);

        //HttpEntity requestEntity = new HttpEntity<>(headers);
        //ResponseEntity.status(200);
        try{
            ResponseEntity<String> forEntity = restTemplate.getForEntity (otpUrl, String.class);
            log.info ("{}", forEntity);
        }catch (ResourceAccessException e){
            log.info (String.valueOf (e));
        }
    }
}
