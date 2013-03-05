package interfaces;

import java.util.EventListener;

/**
 * Implement this interface to receive MessageArrived notifications
 * @author Lars Mortensen
 */
public interface MessageArrivedListener extends EventListener {
  
 /**
  * A message arrived on the input socket stream
  * @param event 
  */
  public void MessageArrived(MessageArrivedEvent event);
  
}