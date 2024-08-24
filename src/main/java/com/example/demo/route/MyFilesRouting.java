package com.example.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFilesRouting extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("timer:/timer1?repeatCount=1").setBody(constant("select * from persons")).to("jdbc:dataSource")
				.process(ex -> {
					String s = ex.getMessage().getBody(String.class);
					System.out.println(s);
					ex.getMessage().setBody(s);

				})

				.to("file:D:/dest?fileName=info.txt");

	}

}
