package interfaces;

import java.util.EventObject;

/**
 * Event for the network chat client exersize
 */
public class MessageArrivedEvent extends EventObject
{

  String message;

  /**
   * The complete message in the form COMMAND#MSG
   *
   * @return
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * Constructs a new MessageArrived Instance
   *
   * @param source
   */
  public MessageArrivedEvent(Object source)
  {
    super(source);
  }

  /**
   * Constructs a new MessageArrived Instance
   *
   * @param source
   * @param message
   */
  public MessageArrivedEvent(Object source, String message)
  {
    super(source);
    this.message = message;
  }
}
