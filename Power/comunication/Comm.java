package comunication;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.comm.*;

public class Comm {
	private HashMap<String, CommPortIdentifier> portMap = new HashMap<String,CommPortIdentifier>();

	@SuppressWarnings("rawtypes")
	public List<String> getAvailablePorts(){
		List<String> portList = new ArrayList<String>();
		
		Enumeration availablePorts = CommPortIdentifier.getPortIdentifiers();
		while (availablePorts.hasMoreElements()){
			CommPortIdentifier pid = (CommPortIdentifier) availablePorts.nextElement();
			if(pid.getPortType() == CommPortIdentifier.PORT_SERIAL){
				portList.add(pid.getName());
				portMap.put(pid.getName(), pid);
				//TODO:To remove
				System.out.println(pid.getName());
			}
		}
		return portList;
	}
	
	public boolean connect(String portName){
		
		return false;
	}
}