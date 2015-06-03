/**
 *
 * @author Gabe Cronin
 */

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Version;
        
public class ControllerTest
{

        static int ID = 8;
    
	static Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
        static String controllerType = "Stick";
        static Controller logitech = null;
	
	public static void main(String[] args) {

            System.out.println("JInput version: " + Version.getVersion());
            System.out.println("");
        
            for(int i=0; i < ca.length && logitech == null; i++) {
            if(ca[i].getType().toString().equals(controllerType)) {
                // Found a controller
                logitech = ca[i];
                break;
                }
            }
        
            if(logitech == null) {
                // Couldn't find a controller
                System.out.println("Found no desired controller!");
                System.exit(0);
            }
            /*
            System.out.println(logitech.getName());
            System.out.println("Type: "+logitech.getType().toString());
            
            Component[] components = logitech.getComponents();
            System.out.print("Component count: "+components.length);
            for(int j=0; j<components.length; j++){
                System.out.println("");
                
                // Get the components name
                System.out.println("Component "+j+": "+components[j].getName());
                // Get it's identifier, E.g. BUTTON.PINKIE, AXIS.POV and KEY.Z, 
                // see http://www.newdawnsoftware.com/resources/jinput/apidocs/net/java/games/input/Component.Identifier.html
                System.out.println("    Identifier: "+ components[j].getIdentifier().getName());
                System.out.print("    ComponentType: ");
                if (components[j].isRelative())
                    System.out.print("Relative");
                else
                    System.out.print("Absolute");
                
                if (components[j].isAnalog()) 
                    System.out.print(" Analog");
                else
                    System.out.print(" Digital");
            }*/

            while(true) {
                logitech.poll();
                Component[] components = logitech.getComponents();
                StringBuffer buffer = new StringBuffer();
                for(int i=0;i<components.length;i++) {
                    if(i>0) {
                    buffer.append(", ");
                    }
                buffer.append(components[i].getName());
                buffer.append(": ");
                if(components[i].isAnalog()) {
                    buffer.append(components[i].getPollData());
                } else {
                    if(components[i].getPollData()==1.0f) {
                        buffer.append("On");
                    } else {
                        buffer.append("Off");
                    }
                }
            }
            System.out.println(buffer.toString());

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
    }
}
