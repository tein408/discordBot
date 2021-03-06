import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import message.Rainbow;
import reactor.core.publisher.Mono;
import token.Token;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        DiscordClient client = DiscordClient.create(Token.getToken());

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(MessageCreateEvent.class, event -> {
                    Message message = event.getMessage();
                    
                    if(message.getContent().startsWith("$avatar ")) {
                        List<User> list = event.getMessage().getUserMentions();
                        String url = list.get(0).getAvatarUrl();
                        if(url != null) {
                            return message.getChannel()
                            .flatMap(channel -> channel.createMessage(url + "?size=1024"));
                        }
                    }

                    if(message.getContent().startsWith("$rainbow ")) {
                        String content = event.getMessage().getContent();
                        int cnt = Integer.parseInt(content.split(" ")[1]);
                        Rainbow rainbow = new Rainbow();

                        String result;
                        if(cnt == 0)
                            result = "zero? :thinking:";
                        else
                            result = rainbow.printRainbow(cnt);

                        return message.getChannel()
                                .flatMap(channel -> channel.createMessage(result));
                    }

                    if(message.getContent().startsWith("$rainbowArt ")) {
                        String content = event.getMessage().getContent();
                        int cnt = Integer.parseInt(content.split(" ")[1]);
                        Rainbow rainbow = new Rainbow();

                        String result;
                        if(cnt == 0)
                            result = "zero? :thinking::sick:";
                        else
                            result = rainbow.rainbowArt(cnt);

                        return message.getChannel()
                                .flatMap(channel -> channel.createMessage(result));
                    }

                    return Mono.empty();
                }));

        login.block();

    }

}
