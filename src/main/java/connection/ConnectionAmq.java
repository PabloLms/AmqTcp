package connection;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.Getter;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

@Getter
public class ConnectionAmq {

  private static final String URL = "tcp://localhost:61616";

  private static final String USER = ActiveMQConnection.DEFAULT_USER;

  private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

  private final Connection connection;


  public ConnectionAmq() throws JMSException {
    final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER,
        PASSWORD, URL);
    this.connection = connectionFactory.createConnection();
  }

  public void start() throws JMSException {
    this.connection.start();
  }

  public void close() throws JMSException {
    this.connection.close();
  }

}
