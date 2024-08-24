package com.scm.ContactManager.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Message {
    private String content;

    @Builder.Default
    private MessageType type= MessageType.blue;

}
