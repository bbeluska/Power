package comunication;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Comm{

	SerialPort serialPort;
	
	/**
	 * Search for available ports.
	 * 
	 * @return Array of String with available port names.
	 */
    public String[] searchForPorts()
    {
    	return SerialPortList.getPortNames();
    }
	
    /**
     * Connect to the given port.
     * 
     * @param portName the port to connect to
     * @return true if connection successful.
     */
	public boolean connect(String portName){
		serialPort = new SerialPort(portName);
		try {
			return serialPort.openPort();
		} catch (SerialPortException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Disconnect from the last connected port.
	 */
	public void disconnect(){
		if (null != serialPort && serialPort.isOpened()){
			try {
				serialPort.closePort();
			} catch (SerialPortException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Read from the opened serial port's buffer.
	 * 
	 * @return data from buffer
	 */
	public String readPort(){
		if (null != serialPort && serialPort.isOpened()){
			try {
				return serialPort.readString();
			} catch (SerialPortException e) {
				e.printStackTrace();
				return null;
			}	
		} else {
			return null;
		}
	}
	
	/**
	 * Check if serial port is still opened.
	 * 
	 * @return true if open.
	 */
	public boolean isConnected(){
		return null != serialPort && serialPort.isOpened();
	}
}