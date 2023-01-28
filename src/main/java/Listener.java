import connection.ConnectionAmq;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Listener {

  private static final int TIMEOUT = 1000;

  private static final boolean TRANSACTED_SESSION = false;
  private final ConnectionAmq connectionAmq;

  private final Session session;

  private Destination destination;

  private MessageConsumer messageConsumer;

  public Listener() throws JMSException {
    this.connectionAmq = new ConnectionAmq();
    this.connectionAmq.start();
    this.session = connectionAmq.getConnection()
        .createSession(TRANSACTED_SESSION, Session.AUTO_ACKNOWLEDGE);
  }

  public void subscribir(String destinationQueque) throws JMSException {
    this.destination = session.createQueue(destinationQueque);
    this.messageConsumer = session.createConsumer(destination);
  }

  public void listenerMessageQueque() throws JMSException {
    Message message;
    while ((message = this.messageConsumer.receive(TIMEOUT)) != null) {
      if (message instanceof TextMessage) {
        final TextMessage textMessage = (TextMessage) message;
        final String text = textMessage.getText();
        System.out.println(text);
      }
    }

  }

  public void close() throws JMSException {
    messageConsumer.close();
    session.close();
    connectionAmq.close();
  }

}
