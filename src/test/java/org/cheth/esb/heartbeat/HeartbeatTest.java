package org.cheth.esb.heartbeat;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HeartbeatTest extends CamelSpringTestSupport {
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.camel.test.spring.CamelSpringTestSupport#createApplicationContext()
	 * 
	 * Load camel context
	 */
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"classpath*:/META-INF/spring/test-camel-context.xml");
	}
	
	/**
	 * 
	 * Test the timer
	 * For the purpose of the test, the timer is set to fire off every 2 secs. 
	 * The interval can be set in test.properties.
	 * The test case will run for 4 sec, expecting 2 events to be fired.
	 */
	@Test 
	public void testHeartbeatTimer() throws Exception {
	
		while (true) {
			Thread.sleep(4000);
			break;
		}

		assertEquals ("Invalid count", "2", Integer.toString(getMockEndpoint("{{hq.queue}}").getReceivedCounter()));
	}
	
}
