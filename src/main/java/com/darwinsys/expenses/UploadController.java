package com.darwinsys.expenses;

import org.springframework.web.bing.annotation.*;

@RestController
public class UploadController {

	@GetMapping("/")
	public String index() {
		return "This is the Spring-based Upload server";
	}
}
