package controller;

import modell.DataModel;
import ui.View;

public class Controller {
	private View view;
	private String selectedComPort;
	private boolean connected = false;
	private DataModel data;
	
	private static final long REFRESH_TIME = 500; //(mSec)
	
	public Controller(){
		view = new View(this);
		new Updater().run();		
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
		view.setConnectButtonConnect(false);
		this.connected = true;
		view.setConnectButtonEnabled(true);
	}
	
	/**
	 * disconnect from com port
	 */
	public void disconnect(){
		//disconnect from port
		this.connected = false;
		view.setComComboItems(findAvailableComPorts());
		view.setConnectButtonConnect(true);
	}
	
	private String[] findAvailableComPorts(){
		//TODO: To be implemented
		return new String[] {"COM1", "COM2", "COM3"};
	}
	
	private class Updater implements Runnable {

	       public void run() {
	    	   while(true){
	    		   try {
					Thread.sleep(REFRESH_TIME);
				} catch (InterruptedException e) {
					System.out.println(e.getStackTrace().toString());
				}
	    		   if (isConnected()){
	    			   data = DataConstructor.convert("dummyMessage");
	    			   view.update(data);
	    		   }
	    	   }
	       }
	    }

	
	//getters and setters
	public String[] getAvailableComPorts(){
		return this.findAvailableComPorts();
	}

	public String getSelectedComPort() {
		return selectedComPort;
	}

	public void setSelectedComPort(String selectedComPort) {
		if (null != selectedComPort && !"".equals(selectedComPort)){
			this.selectedComPort = selectedComPort;
			view.setConnectButtonEnabled(true);
		} else {
			view.setConnectButtonEnabled(false);
		}
	}
	
	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

}
