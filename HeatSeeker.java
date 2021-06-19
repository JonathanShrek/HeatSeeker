package Code.heatseeker;

import com.birdbraintechnologies.Finch;
import java.util.Random;

/**
 * Created by: Jonathan Shreckengost
 * Date: 06/18/2021
 * Heat seeking mobile robot project for IT-209
 */

public class HeatSeeker
   {
	//feedback on/off states
	static boolean voiceOn = true;
	static boolean buzzOn = false;
	static boolean ledOn = true;
	
	// function to stop everything when exiting the program
	public void stopAll()
	{
		Finch myFinch = new Finch();
		myFinch.stopWheels();
		myFinch.setLED(0, 0, 0);
	}
	
	// function to generate a random number
	public int getRandomNum()
	{
		Random rn = new Random();
		return rn.nextInt(10 + 1) + 1;
	}
	
	// function to return temp in Fahrenheit
	public double getTemp(double temp)
	{
		return (temp * 1.8) + 32;
	}
	
   public static void main(final String[] args)
      {
	  // Creating class object
	  HeatSeeker hs = new HeatSeeker();
	   
      // Instantiating the Finch object
      Finch myFinch = new Finch();
      System.out.println("Finch ready.");

      // Run so long as the Finch is not pointed beak down
      while(!myFinch.isBeakDown()) {
    	  if(myFinch.isObstacle()) {
    		  myFinch.stopWheels();
    		  if (hs.getTemp(myFinch.getTemperature()) > 80.0 && voiceOn == true) {
    			  myFinch.saySomething("This object is hot");
    			  myFinch.setLED(100, 0, 0, 700);;
    			  
    			  // logic to back away and turn a random direction
    			  if (hs.getRandomNum() < 5) {
    				  myFinch.setWheelVelocities(-255, -255, 700);
    				  myFinch.setWheelVelocities(-255, -50, 350);
    			  } else {
    				  myFinch.setWheelVelocities(-255, -255, 700);
    				  myFinch.setWheelVelocities(-50, -255, 350);
    			  }
    		  } else {
    			  // logic to back away and turn a random direction
    			  if (hs.getRandomNum() < 5) {
    				  myFinch.setWheelVelocities(-255, -255, 700);
    				  myFinch.setWheelVelocities(-255, -50, 350);
    			  } else {
    				  myFinch.setWheelVelocities(-255, -255, 700);
    				  myFinch.setWheelVelocities(-50, -255, 350);
    			  }
    		  }
    	  }
    	  
    	  // Else, robot goes straight and LED turns off
    	  else {
    		  myFinch.setLED(0, 0, 0);
    		  myFinch.setWheelVelocities(255,255);
    	  }
      }
      
      hs.stopAll();
      
      // Always end your program with finch.quit()
      myFinch.quit();
      System.exit(0);
      }
   }