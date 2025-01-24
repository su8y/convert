package org.jbt.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jbtgeoconvert.converter.TestConverter;

@RestController
public class TestController {


	@GetMapping("/test")
	public String test() {
		TestConverter testConverter = new TestConverter();
		return testConverter.convert("Hello, world!");
	}
}
