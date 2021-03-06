package jp.co.freemind.calico.servlet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.freemind.calico.core.config.SystemSetting;
import jp.co.freemind.calico.core.endpoint.aop.EndpointInterceptor;
import jp.co.freemind.calico.core.endpoint.aop.EndpointInvocation;
import jp.co.freemind.calico.core.zone.Zone;
import jp.co.freemind.calico.servlet.Keys;

public class VersioningInterceptor implements EndpointInterceptor {
  @Override
  public Object invoke(EndpointInvocation invocation) throws Throwable {
    HttpServletRequest req = Zone.getCurrent().getInstance(Keys.SERVLET_REQUEST);
    HttpServletResponse res = Zone.getCurrent().getInstance(Keys.SERVLET_RESPONSE);
    setVersionTag(req, res);
    return invocation.proceed();
  }

  private void setVersionTag(HttpServletRequest req, HttpServletResponse res) {
    res.setHeader(getVersionTag(), getVersion());
  }

  private String getVersion() {
    return String.valueOf(Zone.getCurrent().getInstance(SystemSetting.class).version());
  }

  private String getVersionTag() {
    return Zone.getCurrent().getInstance(SystemSetting.class).getVersionTag();
  }
}
