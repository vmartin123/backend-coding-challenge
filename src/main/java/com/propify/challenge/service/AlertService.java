package com.propify.challenge.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

	@Async
	public void sendPropertyDeletedAlert(Long id) {
		// What this method actually does is not important
	}
}
