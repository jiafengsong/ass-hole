package com.tmall.asshole.mock.ep;

import com.tmall.asshole.common.Event;
import com.tmall.asshole.common.EventContext;
import com.tmall.asshole.engine.AbstractHandler;

public class TestHandler1 extends AbstractHandler {

	public boolean handle(Event event, EventContext context) throws Exception {
		
		context.putData("a", 121212);
		
		context.putData("b", 121212);
		return true;
	}

}