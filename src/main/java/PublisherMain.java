import javax.jms.JMSException;

public class PublisherMain {

  public static void main(String[] args) throws JMSException {
    Publisher publisher = new Publisher();
    publisher.createQueque("APLICATION1.QUEUE");
    publisher.sendMessage("Hola como estas");
    publisher.close();
  }
}
