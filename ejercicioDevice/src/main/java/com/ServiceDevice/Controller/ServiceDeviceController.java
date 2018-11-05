package com.ServiceDevice.Controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ServiceDevice.exception.ServiceInvalidException;
import com.ServiceDevice.model.Device;


@RestController
public class ServiceDeviceController {

	private static Map<String, Device> DeviceRepo = new HashMap();
	static {
		
		Device compu = new Device();
		compu.setMac("00:17:4F:08:5F:69");
		String today = "01/01/2018";		
		compu.setTimestamp(today);
		System.out.print(today);
		DeviceRepo.put(compu.getMac(), compu);
		
		Device compu2 = new Device();
		compu2.setMac("00:17:4F:08:5F:70");
		String today2 = "01/02/2018";		
		compu2.setTimestamp(today2);
		System.out.print(today2);
		DeviceRepo.put(compu2.getMac(), compu2);

	}

	@RequestMapping(value = "/devices")
	public ResponseEntity<Collection<Device>> getDevices() {
		return new ResponseEntity<Collection<Device>>(DeviceRepo.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/devices/{mac}", method = RequestMethod.GET)
	public ResponseEntity<Device> getDevice(@PathVariable("mac") String mac) {
		return new ResponseEntity<Device>(DeviceRepo.get(mac), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/devices", method = RequestMethod.POST)
	  public ResponseEntity<String> createDevice(@RequestBody Device Device) {
		
		Device existe = DeviceRepo.get(Device.getMac());
		if (existe!= null)
		{
			return new ResponseEntity<String>("El device ya existe", HttpStatus.CREATED);	
		}
	   
	   SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
	   Date entrada = null;
	   try {
		   
		   entrada = formatoDeFecha.parse(Device.getTimestamp());
		   
    	   } catch (ParseException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}	   
       Calendar cal = Calendar.getInstance();
       cal.set(2018,0,1);
       Date inicio = cal.getTime();
       System.out.println(Device.getTimestamp());
       System.out.println(entrada);
       System.out.println(inicio);
       
       if(entrada.after(inicio) || entrada.equals(inicio)){
    	  DeviceRepo.put(Device.getMac(), Device);
    	  return new ResponseEntity<String>("Device creado satisfactoriamente", HttpStatus.CREATED);
       } else {
    	   
    	  //throw new ServiceInvalidException(); 
    	  return new ResponseEntity<String>("El Device es anterior al 01-01-2018", HttpStatus.BAD_REQUEST);
       }	   
	   
	}
	
	@RequestMapping(value = "/devices/{mac}", method = RequestMethod.PUT)
	   public ResponseEntity<String> updateDevice(@PathVariable("mac") String mac, @RequestBody Device Device) { 
		//  if(!DeviceRepo.containsKey(id))throw new ProductNotfoundException();
		  DeviceRepo.remove(mac);
		  Device.setMac(mac);
		  DeviceRepo.put(mac, Device);
	      return new ResponseEntity<String>("Device actualizado", HttpStatus.OK);
	   }
	
	 @RequestMapping(value = "/devices/{mac}", method = RequestMethod.DELETE)
	   public ResponseEntity<String> delete(@PathVariable("mac") String mac) { 
		 DeviceRepo.remove(mac);
	     return new ResponseEntity<String>("Device eliminado",HttpStatus.OK);
	 }

}
