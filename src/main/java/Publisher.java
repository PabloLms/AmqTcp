import connection.ConnectionAmq;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Publisher {

  private static final boolean TRANSACTED_SESSION = true;

  private final ConnectionAmq connectionAmq;
  private final Session session;

  private Destination destination;

  private MessageProducer messageProducer;

  public Publisher() throws JMSException {
    this.connectionAmq = new ConnectionAmq();
    this.connectionAmq.start();
    this.session = connectionAmq.getConnection()
        .createSession(TRANSACTED_SESSION, Session.AUTO_ACKNOWLEDGE);
  }

  public void close() throws JMSException {
    this.session.close();
    this.connectionAmq.close();
  }


  public void createQueque(String destinationQueque) throws JMSException {
    this.destination = session.createQueue(destinationQueque);
    messageProducer = session.createProducer(destination);
    messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
  }

  public void sendMessage(String message) throws JMSException {
    for (int i = 0; i < 20; i++) {
      final TextMessage textMessage = session.createTextMessage(message);
      messageProducer.send(textMessage);
      System.out.println("Se envio el mensaje");
    }
    session.commit();
  }
}
