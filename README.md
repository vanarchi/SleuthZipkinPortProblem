## Problem description

It seems that in WebEnvironment.MOCK server.port property of an application is changed to -1 in spring test.
It causes the following exception in zipkin: java.lang.IllegalArgumentException: invalid port -1

If zipkin is disabled in test class by using annotation: @TestPropertySource(properties = { "spring.zipkin.enabled: false" }) as it is in RestServiceWithoutZipkinTest - everything goes fine. 

## Configuration

The application by default will be running using default port 8080
If the port changes in application.properties the port have to be changed in com.example.sleuthzipkin.RestService.PORT constant

the zipking port can be changed in application.properties. By default spring.zipkin.base-url=http://localhost:9411/

## Test results

the test com.example.sleuthzipkin.RestServiceWithoutZipkinTest passes

the test com.example.sleuthzipkin.RestServiceWithZipkinTest fails with the following exception:

```
org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.IllegalArgumentException: invalid port -1

	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:982)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:648)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)
	at org.springframework.test.web.servlet.TestDispatcherServlet.service(TestDispatcherServlet.java:65)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:729)
	at org.springframework.mock.web.MockFilterChain$ServletFilterProxy.doFilter(MockFilterChain.java:167)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.boot.web.filter.ApplicationContextHeaderFilter.doFilterInternal(ApplicationContextHeaderFilter.java:55)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.boot.actuate.trace.WebRequestTraceFilter.doFilterInternal(WebRequestTraceFilter.java:108)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.cloud.sleuth.instrument.web.TraceFilter.doFilter(TraceFilter.java:145)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.boot.actuate.autoconfigure.MetricsFilter.doFilterInternal(MetricsFilter.java:106)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
	at org.springframework.mock.web.MockFilterChain.doFilter(MockFilterChain.java:134)
	at org.springframework.test.web.servlet.MockMvc.perform(MockMvc.java:155)
	at com.example.sleuthzipkin.RestServiceWithZipkinTest.someTest(RestServiceWithZipkinTest.java:48)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:252)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:94)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
	at com.github.tomakehurst.wiremock.junit.WireMockClassRule$1.evaluate(WireMockClassRule.java:70)
	at org.junit.rules.RunRules.evaluate(RunRules.java:20)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)
	at org.junit.runners.Suite.runChild(Suite.java:128)
	at org.junit.runners.Suite.runChild(Suite.java:27)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:51)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:237)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
Caused by: java.lang.IllegalArgumentException: invalid port -1
	at zipkin.internal.Util.checkArgument(Util.java:50)
	at zipkin.Endpoint$Builder.port(Endpoint.java:185)
	at org.springframework.cloud.sleuth.zipkin.ServerPropertiesEndpointLocator.local(ServerPropertiesEndpointLocator.java:83)
	at org.springframework.cloud.sleuth.zipkin.ZipkinSpanListener.convert(ZipkinSpanListener.java:93)
	at org.springframework.cloud.sleuth.zipkin.ZipkinSpanListener.report(ZipkinSpanListener.java:246)
	at org.springframework.cloud.sleuth.trace.DefaultTracer.close(DefaultTracer.java:150)
	at org.springframework.cloud.sleuth.instrument.web.client.AbstractTraceHttpRequestInterceptor.finish(AbstractTraceHttpRequestInterceptor.java:90)
	at org.springframework.cloud.sleuth.instrument.web.client.TraceRestTemplateInterceptor.finish(TraceRestTemplateInterceptor.java:41)
	at org.springframework.cloud.sleuth.instrument.web.client.TraceHttpResponse.close(TraceHttpResponse.java:76)
	at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:670)
	at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:613)
	at org.springframework.web.client.RestTemplate.postForObject(RestTemplate.java:380)
	at com.example.sleuthzipkin.RestService.doHello(RestService.java:28)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:116)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)
	... 75 more
```
