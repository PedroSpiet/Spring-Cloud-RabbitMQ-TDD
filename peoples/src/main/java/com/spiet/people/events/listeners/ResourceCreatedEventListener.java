package com.spiet.people.events.listeners;

import com.spiet.people.events.ResourceCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class ResourceCreatedEventListener implements ApplicationListener<ResourceCreatedEvent> {

    @Override
    public void onApplicationEvent(ResourceCreatedEvent resourceCreatedEvent) {
        HttpServletResponse response = resourceCreatedEvent.getResponse();
        Long id = resourceCreatedEvent.getId();

        locationHeader(response, id);
    }

    public void locationHeader(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toString());
    }

}
