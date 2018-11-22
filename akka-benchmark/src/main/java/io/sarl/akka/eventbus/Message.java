package io.sarl.akka.eventbus;

public class Message {

    private final String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

}
