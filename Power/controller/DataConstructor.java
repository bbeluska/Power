package controller;

import modell.DataModel;

public class DataConstructor {

	public static DataModel convert(String message){
		//TODO: to be implemented
		DataModel dataModel = new DataModel();
		if (null != message){
			dataModel.setVoltage(((Float)Float.parseFloat((message.substring(0, 5)))).toString());
			dataModel.setCurrent(((Float)Float.parseFloat((message.substring(13, 18)))).toString());
			dataModel.setPower(((Float)Float.parseFloat((message.substring(30, 36)))).toString());
			dataModel.setSetpoint(((Integer)Integer.parseInt((message.substring(41, 44).trim()))).toString());
			dataModel.setTemperature(((Float)Float.parseFloat((message.substring(25, 29)))).toString());
			//
			Float setPoint = Float.parseFloat(message.substring(41, 44).trim());
			Float power = Float.parseFloat(message.substring(30, 36));
			Float error = setPoint - power;
			dataModel.setErrorDelta(error.toString());
			//
			if (setPoint == 0){
				dataModel.setError("inf");
			} else{
				error = setPoint - ((100 * power) / setPoint);
				dataModel.setError(error.toString());
			}
			//
			String pwm = message.substring(37, 40);
			pwm = pwm.replace('x', ' ');
			dataModel.setPwm(pwm);
			//
			return dataModel;
		}
		return null;
	}
}
