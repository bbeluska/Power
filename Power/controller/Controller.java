package controller;

import comunication.Comm;
import modell.DataModel;
import ui.View;

public class Controller {
	private View view;
	private Comm comm;
	private String selectedComPort;
	private DataModel data;
	
	private static final long REFRESH_TIME = 500; //(mSec)
	
	/**
	 * Constructor
	 */
	public Controller(){
		comm = new Comm();
		view = new View(this);
		new Updater().run();		
	}
	
	/**
	 * connect to the selected com port
	 */
	public void connect(String portName){
		comm.connect(portName);
		if (comm.isConnected()){
			view.setReadyToConnect(false);
		} else {
			view.setReadyToConnect(true);
		}
	}
	
	/**
	 * disconnect from com port
	 */
	public void disconnect(){
		comm.disconnect();
		view.setComComboItems(getAvailableComPorts());
		view.setReadyToConnect(true);
	}
	
	/**
	 * Inner runnable Class for a separate thread for readings.
	 * 
	 * @author Mester-San
	 *
	 */
	private class Updater implements Runnable {

	       public void run() {
	    	   while(true){
	    		   try {
					Thread.sleep(REFRESH_TIME);
				} catch (InterruptedException e) {
					System.out.println(e.getStackTrace().toString());
				}
	    		   if (comm.isConnected()){
	    			   String message = comm.readPort();
	    			   if(null == message){
	    				   disconnect();
	    			   } else {
	    				   //TODO: to be removed
	    				   System.out.println(message);
	    				   view.update(DataConstructor.convert(message));
	    			   }
	    		   }
	    	   }
	       }
	   }
	
	/***************getters and setters******************/
	
	/**
	 * Get the available ports from the {@link Comm Comm}.
	 * 
	 * @return list of strings with the available port names.
	 */
	public String[] getAvailableComPorts(){
		return comm.searchForPorts();
	}

	/**
	 * Validate and set the selected com port.
	 * 
	 * @param selectedComPort
	 */
	public void setSelectedComPort(String selectedComPort) {
		if (null != selectedComPort && !"".equals(selectedComPort)){
			this.selectedComPort = selectedComPort;
			view.setConnectButtonEnabled(true);
		} else {
			view.setConnectButtonEnabled(false);
		}
	}
}
