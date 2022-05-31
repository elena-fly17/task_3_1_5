package com.mukhina.rest_template.task_3_1_5;

import com.mukhina.rest_template.task_3_1_5.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// @SpringBootApplication
public class Task315Application {

    private static final String URL_ALL_USERS = "http://94.198.50.185:7081/api/users";
	private static final String URL_ADD_USER = "http://94.198.50.185:7081/api/users";
	private static final String URL_UPDATE_USER = "http://94.198.50.185:7081/api/users";
	private static final String URL_DELETE_USER = "http://94.198.50.185:7081/api/users/{id}";

	List<String> myCookies;

	User newUser = new User(3L, "James", "Brown", (byte) 30);
	User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 30);

    RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		// SpringApplication.run(Task315Application.class, args);
		Task315Application task315Application = new Task315Application();

		task315Application.getAllUsers();
		task315Application.addUser();
		task315Application.updateUser();
		task315Application.deleteUser();
	}

	// переделанный метод - как у индуса - так как точно неизвестно, в каком формате сервер вернет
	// ответ, то лучше самим заранее указать, какой тип ответа хотим (JSON)
	public void getAllUsers() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> responseEntity =
				restTemplate.exchange(URL_ALL_USERS, HttpMethod.GET, entity, String.class);
		String result = responseEntity.getBody(); // получаем тело ответа со списком юзеров
		myCookies = responseEntity.getHeaders().get("Set-Cookie");
		// mySessionId = myCookies.get(0);
		System.out.println("КУКИ, ПОЛУЧЕННЫЕ ИЗ ПЕРВОГО ЗАПРОСА: " + myCookies);
		System.out.println("СПИСОК ПОЛЬЗОВАТЕЛЕЙ: " + result);
	}

	public void addUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Cookie", myCookies.stream().collect(Collectors.joining(";")));
		// headers.add("Cookie", "JSESSIONID=" + mySessionId);
		HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);
		/*ResponseEntity<User> responseEntity =
				restTemplate.postForEntity(URL_ADD_USER, requestBody, User.class);*/
		ResponseEntity<String> responseEntity =
				restTemplate.exchange(URL_ADD_USER, HttpMethod.POST, requestBody, String.class);
		System.out.println(responseEntity.getHeaders());
		System.out.println(responseEntity.getBody());
	}

	// изменение пользователя
	public void updateUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// headers.set("COOKIE", myCookies.stream().collect(Collectors.joining(";")));
		headers.set("Cookie", myCookies.stream().collect(Collectors.joining(";")));
		// headers.add("Cookie", "JSESSIONID=" + mySessionId);
		HttpEntity<User> requestBody = new HttpEntity<>(updatedUser, headers);
		ResponseEntity<String> responseEntity =
				restTemplate.exchange(URL_UPDATE_USER, HttpMethod.PUT, requestBody, String.class);
		System.out.println(responseEntity.getHeaders());
		System.out.println(responseEntity.getBody());
	}

	// удаление пользователя
	public void deleteUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Cookie", myCookies.stream().collect(Collectors.joining(";")));
		// headers.add("Cookie", "JSESSIONID=" + mySessionId);
		Map<String, String> params = new HashMap<> ();
		params.put("id", "3");
		HttpEntity<Map<String, String>> requestBody = new HttpEntity<>(params, headers);
		ResponseEntity<String> responseEntity =
				restTemplate.exchange(URL_DELETE_USER, HttpMethod.DELETE, requestBody, String.class);
		System.out.println(responseEntity.getHeaders());
		System.out.println(responseEntity.getBody());
	}
}
