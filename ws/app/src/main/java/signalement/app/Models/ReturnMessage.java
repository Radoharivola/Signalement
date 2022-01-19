package signalement.app.Models;

public class ReturnMessage {
	String token;
	String message;
	Boolean connectionStatus;
	Boolean tokenStatus;
	Object data;
	/**
	 * @param token
	 * @param message
	 * @param connectionStatus
	 * @param tokenStatus
	 * @param data
	 */
	public ReturnMessage(String token, String message, Boolean connectionStatus, Boolean tokenStatus, Object data) {
		this.token = token;
		this.message = message;
		this.connectionStatus = connectionStatus;
		this.tokenStatus = tokenStatus;
		this.data = data;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(Boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	public Boolean getTokenStatus() {
		return tokenStatus;
	}
	public void setTokenStatus(Boolean tokenStatus) {
		this.tokenStatus = tokenStatus;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
