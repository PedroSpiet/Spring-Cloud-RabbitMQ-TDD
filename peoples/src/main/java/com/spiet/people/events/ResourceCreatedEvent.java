package com.spiet.people.events;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class ResourceCreatedEvent extends ApplicationEvent {

    private  Long id;
    private  HttpServletResponse response;

    public ResourceCreatedEvent(Object source, HttpServletResponse response, Long id) {
        super(source);

        this.response = response;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
