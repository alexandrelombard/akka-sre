package io.sarl.akka.eventbus;

public class Message {

    private final String channel;
    private final String content;

    public Message(String channel, String content) {
        this.channel = channel;
        this.content = content;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getContent() {
        return this.content;
    }

}
