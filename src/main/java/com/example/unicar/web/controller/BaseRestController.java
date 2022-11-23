package com.example.unicar.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.example.unicar.core.util.IsNullUtil.isNullOrEmpty;

public abstract class BaseRestController {

	protected <T> ResponseEntity<List<T>> writeResponseBody(List<T> body) {
		if (!isNullOrEmpty(body)) {
			return ResponseEntity.ok(body);
		}
		return ResponseEntity.noContent().build();
	}

	protected <T> ResponseEntity<T> writeResponseBody(T body) {
		return ResponseEntity.ok(body);
	}

	protected <T> ResponseEntity<T> writeResponseBody() {
		return ResponseEntity.ok().build();
	}
}