package com.example.unicar.web.controller;

import com.example.unicar.core.exception.ExceptionUtil;
import com.example.unicar.core.service.jasperreport.Formato;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
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
	protected <T> ResponseEntity<T> writeResponseBodyCreated(T value) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{/uuid}").buildAndExpand(value).toUri();
		return ResponseEntity.created(location).build();
	}

	protected static ResponseEntity<Resource> response(InputStream inputStream, String report, Formato formato) {
		try {
			int length = inputStream.available();

			InputStreamResource resource = new InputStreamResource(inputStream);

			HttpHeaders header = new HttpHeaders();

			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + report + formato.getExtensao());
			return ResponseEntity.ok().headers(header).contentLength(length).contentType(formato.getMediaType()).body(resource);

		} catch (Exception e) {
			ExceptionUtil.exception(e.getMessage());
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}