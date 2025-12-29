package com.stale.mate.email.model.service;

import java.util.Map;

public interface EmailService {

	String sendEmail(String type, String email);

	int checkAuthKey(Map<String, String> map);

	String resetPwEmail(String type, String email);

	int resetPwAuthKey(Map<String, String> map);

}
