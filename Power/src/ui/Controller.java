package ui;

public class Controller {
	private View view;
	private String[] comPorts;
	private String selectedComPort;
	
	public Controller(){
		comPorts = findAvailableComPorts();
		view = new View(this);
		
	}
	
	/**
	 * connect to the selected com port
	 */
	public void connect(){
		view.setConnectButtonEnabled(false);
		//try to connect
//		if (connected){
//			view.setConnectButtonConnect(false);
//		} else {
//			view.setConnectButtonConnect(true);
//		}
		view.setConnectButtonEnabled(true);
	}
	
	/**
	 * disconnect from com port
	 */
	public void disconnect(){
		view.setConnectButtonConnect(true);
	}
	
	private String[] findAvailableComPorts(){
		//TODO: To be implemented
		return new String[] {"COM1", "COM2", "","COM3"};
	}
	
	//getters and setters
	public String[] getAvailableComPorts(){
		return this.comPorts;
	}

	public String getSelectedComPort() {
		return selectedComPort;
	}

	public void setSelectedComPort(String selectedComPort) {
		if (!selectedComPort.equals(null) && !"".equals(selectedComPort)){
			this.selectedComPort = selectedComPort;
			view.setConnectButtonEnabled(true);
			System.out.println(selectedComPort + " is selected!");
		} else {
			view.setConnectButtonEnabled(false);
		}
		
	}
	

}
