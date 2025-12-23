package com.stale.mate.email.model.service;

import java.util.Map;

public interface EmailService {

	String sendEmail(String type, String email);

	int checkAuthKey(Map<String, String> map);

}
