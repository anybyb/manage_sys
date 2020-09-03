package com.marsmob.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;  

/**
 * 消息队列MQ
 * 手动集成 类似springmvc的xml文件
 * springboot默认的集成那套无法支持事件回调
 * 所以我们只能手动集成！
 * @describe 
 * @author wcc
 * @data 2018年1月19日
 * @version v1.0
 * @copyright LH.GROUP
 * @Configuration
 */
public class RabbitMQConfig {
    
	public static final String EXCHANGE   = "directExchange";//交换机
	public static final String   QUEUE= "sysMsg";//队列
	public static final String ROUTINGKEY = "sysMsgKey";//路由key

	@Value("${rabbit.host}")
	private String host;
	
	@Value("${rabbit.port}")
	private Integer port;
	
	@Value("${rabbit.username}")
	private String username;
	
	@Value("${rabbit.password}")
	private String password;
	//连接工厂
	@Bean  
    public ConnectionFactory connectionFactory() {  
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();  
        connectionFactory.setAddresses(host);  
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);  
        connectionFactory.setPassword(password);  
        connectionFactory.setVirtualHost("/");  
        connectionFactory.setPublisherConfirms(true); //设置为true  即表示支持事件回调！  
        return connectionFactory;  
    }  
	
	//RabbitTemplate模板
	@Bean  
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  //必须是prototype类型
	public RabbitTemplate rabbitTemplate() {  
	    RabbitTemplate template = new RabbitTemplate(connectionFactory());  
	    return template;  
	} 
	
	
	
	//声明交换机 可由消费者一申明【生产者和消费者都可以申明】
    @Bean  
    public DirectExchange defaultExchange() {  
        return new DirectExchange(EXCHANGE);  
    } 
	
	//声明队列 可由消费者一申明【生产者和消费者都可以申明队列】
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true); // true表示持久化该队列
    }
    
   
  
    //绑定 可由消费者一端绑定【生产者和消费者都可以绑定】
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTINGKEY);
    }
    
    /**
     * 消费者监听消息 消费消息
     * 用消费端的     @RabbitHandler  注解来替代这段代码
     * @return
  
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
                byte[] body = message.getBody();
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });
        return container;
    }
    */
    
    
    
    
}
