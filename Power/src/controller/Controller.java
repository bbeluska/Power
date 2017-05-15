package controller;

import comunication.Comm;
import modell.DataModel;
import ui.View;

public class Controller {
	private View view;
	private Comm comm;
	private String selectedComPort;
	private boolean connected = false;
	private DataModel data;
	
	private static final long REFRESH_TIME = 500; //(mSec)
	
	public Controller(){
		comm = new Comm();
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
		view.setComComboItems(getAvailableComPorts());
		view.setConnectButtonConnect(true);
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
		//TODO: To be implemented
		//return (String[]) comm.getAvailablePorts().toArray();
		return new String[] {"COM1", "COM2", "COM3"};
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
