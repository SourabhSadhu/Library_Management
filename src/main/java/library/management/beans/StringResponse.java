package library.management.beans;

import org.springframework.stereotype.Service;

@Service
public class StringResponse {

	String response;
	String responseCode;
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
}
