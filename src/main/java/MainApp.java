import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public class MainApp {

    public static void main(String[] args) {

        String token = "";
        DiscordClient client = DiscordClient.create(token);

        client.login().flatMapMany(gateway -> gateway.on(MessageCreateEvent.class))
                .map(MessageCreateEvent::getMessage)
                .filter(message -> "!ping".equals(message.getContent()))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Pong!"))
                .blockLast();

    }

}
