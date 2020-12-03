package com.userservice.mail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Mail {
    final String to;
    final String from;
    final String content;
}
