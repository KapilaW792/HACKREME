package com.hack.newrelicreme.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class RemeController {

	@GetMapping("/hackreme/restartms")
	private ResponseEntity restartMS() throws IOException, InterruptedException {

		Process process = Runtime.getRuntime().exec("cmd /c run.bat", null, new File("C:\\GIT_REPO\\HACK\\test\\"));

		StringBuilder output = new StringBuilder();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}

		int exitVal = process.waitFor();
		if (exitVal == 0) {
			System.out.println("Success!");
			System.out.println(output);
			// System.exit(0);
		} else {
			// abnormal...
		}

		System.out.println("##################");
		return ResponseEntity.ok().build();
	}

	@GetMapping("/hackreme/restartmule")
	private ResponseEntity restartMule() {
		WebClient webClient = WebClient.create("https://anypoint.mulesoft.com");

		Mono<String> out = webClient.post().uri("/accounts/login")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).attribute("username", "kapilaw")
				.attribute("password", "aaaAAA@123").retrieve().bodyToMono(String.class);

	
	
		System.out.println(out.block());

		System.out.println("$$$$$$$$$$$$$$$$$$");
		return ResponseEntity.ok().build();
	}
}
