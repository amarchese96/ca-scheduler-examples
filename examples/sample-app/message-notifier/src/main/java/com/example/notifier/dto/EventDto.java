package com.example.notifier.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class EventDto implements Serializable {

    private String type;

    private Map<String, Object> body;
}
