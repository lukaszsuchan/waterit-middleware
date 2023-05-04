package agh.iss.wateritmiddleware.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class UriBuilder {

    public URI requestUriWithId(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    public URI requestUriWithPathAndId(String path, Object id) {
        return ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path(path)
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
