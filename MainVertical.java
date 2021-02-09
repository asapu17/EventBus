import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVertical extends AbstractVerticle
{
    public void start() {
      /*  vertx.deployVerticle(new HelloVerticle());
        Router router=Router.router(vertx);
        router.get("/ghost").handler(this::helloVertx);
        router.get("/bhoot/neha/:id").handler(this::helloName);
                vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    }
    void helloVertx(RoutingContext rc)
    {
        vertx.eventBus().send("hello.vertx.addr"," ", reply ->
        {
            rc.request().response().end((String) reply.result().body());
        });
    }
    void helloName(RoutingContext rc)
    {
        String id = rc.pathParam("id");
        vertx.eventBus().send("hello.named.addr",id,reply->{
            rc.request().response().end((String)reply.result().body());
        });
    }*/
        ConfigStoreOptions fileStore = new ConfigStoreOptions()
                .setType("file")
                .setOptional(true)
                .setConfig(new JsonObject().put("path", "my-config.hocon"));
        ConfigStoreOptions sysPropsStore = new ConfigStoreOptions().setType("sys");
        ConfigRetrieverOptions options = new ConfigRetrieverOptions().addStore(fileStore).addStore(sysPropsStore);
        ConfigRetriever retriever = ConfigRetriever.create(vertx, options);
        retriever.getConfig(json -> {
            JsonObject configs = json.result();
            int numInstances = configs.getInteger("verticle-instances");
            DeploymentOptions doptions =  new DeploymentOptions();
            doptions.setInstances(numInstances);
            doptions.setWorker(false);
            vertx.deployVerticle(ConsumerVerticle.class.getName(),doptions);
            vertx.deployVerticle(ProducerVerticle.class.getName(),doptions);
        });
    }
}

