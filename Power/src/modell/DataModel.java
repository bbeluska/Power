package modell;

public class DataModel {
	private String voltage;
	private String current;
	private String power;
	private String setpoint;
	private String error;
	private String temperature;
	
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getSetpoint() {
		return setpoint;
	}
	public void setSetpoint(String setpoint) {
		this.setpoint = setpoint;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
}
