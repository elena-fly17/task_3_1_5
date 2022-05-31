package com.mukhina.rest_template.task_3_1_5;

import com.mukhina.rest_template.task_3_1_5.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CRUD_Requests {

    // private static final String URL_ALL_USERS = "http://94.198.50.185:7081/api/users";
    // private static final String URL_ADD_USER = "http://94.198.50.185:7081/api/users";

    // private static final RestTemplate restTemplate = new RestTemplate();

    // получение списка всех пользователей/*public List<String> getAllUsers() {
    //        // отправили запрос и получили ответ
    //        ResponseEntity<String> responseEntity = restTemplate.exchange(URL_ALL_USERS,
    //                HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
    //        // это логически подходит для метода - получали тело запроса и потом из него список юзеров
    //        // return responseEntity.getBody();
    //        List<String> str = responseEntity.getHeaders().get("Set-Cookie"); // получили заголовки ответа
    //        System.out.println(str);
    //        return str;
    //    }*/
    // public List<User> getAllUsers() {


    // добавление пользователя

}
