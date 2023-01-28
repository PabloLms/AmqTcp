import javax.jms.JMSException;

public class ListenerMain {

  public static void main(String[] args) throws JMSException {
    Listener listener = new Listener();
    listener.subscribir("APLICATION1.QUEUE");
    listener.listenerMessageQueque();
    listener.close();
  }
}
