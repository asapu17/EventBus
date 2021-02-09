import io.vertx.core.AbstractVerticle;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

public class HelloVerticle extends AbstractVerticle {
    public void start()
    {
      /*  vertx.eventBus().consumer("hello.vertx.addr",msg -> {
            msg.reply("Hello Vett.x World");
        });
        vertx.eventBus().consumer("hello.named.addr",msg ->{
            String name= (String) msg.body();
            msg.reply(String.format("Hello %s!",name));
        }); */

    }

}
