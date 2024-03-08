package com.example.demohttpsrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.net.http.HttpClient;
import java.security.KeyStore;

@SpringBootApplication
public class DemoHttpsRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHttpsRequestApplication.class, args);
	}
}

@RestController
class RestTemplateClientController {

	private static final String WELCOME_URL = "https://localhost:8443/welcome";

	static {
		// Transport Security (SSL) Workaround for your “localhost” development environment
		//for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				(hostname, sslSession) -> hostname.equals("localhost"));
	}

	@Autowired
	private RestTemplateBuilder restTemplate;

	@GetMapping("/welcomeclient")
	public String greetMessage() {
		RestTemplate restTemplate = this.restTemplate.build();
		String response = restTemplate.getForObject(WELCOME_URL, String.class);
		return response;
	}
}
