package cn.scb.sample.gateway.service;

import java.util.Map;

import org.apache.servicecomb.edge.core.AbstractEdgeDispatcher;
import org.apache.servicecomb.edge.core.CompatiblePathVersionMapper;
import org.apache.servicecomb.edge.core.EdgeInvocation;
import org.apache.servicecomb.serviceregistry.definition.DefinitionConst;

import com.netflix.config.DynamicPropertyFactory;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CookieHandler;

public class EdgeDispatcher extends AbstractEdgeDispatcher {

  @Override
  public int getOrder() {
    return 10000;
  }

/*
  @Override
  public void init(Router router) {
    String regex = "/([^\\\\/]+)/(.*)";
    router.routeWithRegex(regex).handler(CookieHandler.create());
    router.routeWithRegex(regex).handler(createBodyHandler());
    router.routeWithRegex(regex).failureHandler(this::onFailure).handler(this::onRequest);
  }

  private void onRequest(RoutingContext context) {
    Map<String, String> pathParams = context.pathParams();
    final String service = pathParams.get("param0");
    String path = "/" + pathParams.get("param1");

    final String serviceName = DynamicPropertyFactory.getInstance()
        .getStringProperty("edge.routing-short-path." + service, service).get();

    EdgeInvocation edgeInvocation = new EdgeInvocation();
    edgeInvocation.setVersionRule(DefinitionConst.VERSION_RULE_ALL);
    edgeInvocation.init(serviceName, context, path, httpServerFilters);
    edgeInvocation.edgeInvoke();
  }
*/

  private CompatiblePathVersionMapper versionMapper = new CompatiblePathVersionMapper();

  public void init(Router router) {
    String regex = "/([^\\\\/]+)/(.*)";
    router.routeWithRegex(regex).handler(CookieHandler.create());
    router.routeWithRegex(regex).handler(createBodyHandler());
    router.routeWithRegex(regex).failureHandler(this::onFailure).handler(this::onRequest);
  }

  protected void onRequest(RoutingContext context) {
    Map<String, String> pathParams = context.pathParams();
    String microserviceName = pathParams.get("param0");
    String microserviceApi = pathParams.get("param1"); //path:"v1/xxx/yyy/..."

    String path = "/" + microserviceApi;
    String pathVersion = microserviceApi.split("/")[0];


    EdgeInvocation edgeInvocation = new EdgeInvocation();
    edgeInvocation.setVersionRule(versionMapper.getOrCreate(pathVersion).getVersionRule());

    edgeInvocation.init(microserviceName, context, path, httpServerFilters);
    edgeInvocation.edgeInvoke();
  }
}
