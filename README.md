# interface-utils

#### 更新日志
    0.4.9 缺失接口添加
    0.4.8 增加合并uw-task异常处理,强化task接口的异常分类处理
    0.4.7 增加Http Status code 不在[200,300)之间的请求同时抛出响应码和响应内容
    0.4.6 去掉uw-task引用
    0.4.5 主要对一些有多值[能解析不同的Vo]的接口添加支持,并通过NoReturnClass.class标记是否需要uw-httpclient处理
    0.4.4 转换失败时输出response内容
    0.4.3 增加对TypeReference形式的方法支持,方便支持返回集合类型的接口。
    0.4.1 XmlInterfaceHelper默认构造参数写错Bug。
    0.4.0 主要增加了传入一个VO作为POST方法请求体[内部使用ObjectMapper进行转换操作]的一些方法。
    0.3.0 主要增加了HttpMessageCallBack接口,对一些接口的内容进行定制转换操作。

#### 项目简介

本项目主要是用来解决之前各项目的Http接口调用不统一问题，以及接口调用配置不灵活，无法统一配置Http客户端，导致长期Hold连接没有释放，没有复用已
有的连接等问题。本项目采用OkHttp3做为Http客户端，OkHttp3性能优异，现在已经被大量的Android应用做为Apache HttpClient的替代。OkHttp有着
良好的封装,整个项目对外开放的配置均使用final类型和Builder模式，极容易配置，并可配置Http连接池，日志记录，多路interceptors,各种业务逻辑的
添加非常方便,这是这个项目要解决的问题和目的，之后可能会与uw-task组合在一起供其他项目使用。

#### 项目特性
1. HttpHelper对OkHttp的自底向上封装接口供上层接口调用调用；
2. 各层接口完全分离，方便之后对项目的调整；
3. 完全的仿Spring RestTemplate的Http接口，并提供了一些自定义配置参数，自带默认的接口转换器；
4. 统一的请求返回包装，方便业务层做日志记录，并提供OkHttp的Response,Request对象。

#### 快速入门

##### 添加maven dependency
```xml
<dependency>
    <groupId>com.umtone</groupId>
    <artifactId>uw-httpclient</artifactId>
    <version>${version}</version>
</dependency>
```
##### 使用例程
Http接口的大部分实现均位于AbstractHttpInterface,而接口工具类JsonInterfaceHelper和XmlInterfaceHelper只是向它们提供HttpHelper和
ObjectMapper,即屏蔽了HttpInterface的实现,又实现了对代码的复用,方便重构和修改。使用上也是很方便的:
```java
// 使用默认HttpConfig配置请求接口,即默认的[全局的]okhttp配置的OkHtt,InterfaceHelper默认使用jackson做为JSON解析器,调用者也可以传入一
// 个实作于ObjectMapper的接口的参数,构造特殊的InterfaceHelper

// new instance or JsonInterfaceHelper.getInstance()
HttpInterface httpInterface = new JsonInterfaceHelper();

// invoke json http interface
ResponseWrapper<ProductVo> responseWrapper = 
    httpInterface.getForEntity("http://localhost:8080/product/{infoId}", ProductVo.class,1L);

// or just you want the Object
    ProductVo product = httpInterface.getForObject("http://localhost:8080/product/{infoId}", ProductVo.class,1L);

```
##### 自定义你的Http接口参数
项目目前提供了自定义配置HttpHelper的参数的配置类HttpConfig,有的Http接口可能对接口的消费有时间限制，超过指定时间可能直接判定为失败,并对接口调用
进行日志记录，导致下次调用可能会被降低排名等等，影响接口调用效率。
例如，配置连接超时参数(connectTimeout)，读超时参数(readTimeout)，写超时参数(writeTimeout),以及对这些连接的的连接新池参数配置,最大空闲连
接数(maxIdleConnections,默认5个),最大存活时间(keepAliveDuration,默认5分钟).
```java
HttpInterface httpInterface = 
    new JsonInterfaceHelper(
            new HttpConfig.Builder().connectTimeout(3000)
                                    .readTimeout(3000).writeTimeout(3000)
                                    .maxIdleConnections(5)
                                    .keepAliveDuration(300000).build());
```

##### FAQ
  
  Q: 多次new OkHttpClient会不会带来效率问题？
  
  A: 不会，因为OkHttpClient 对Java 底层的Connection是共用的,并于OkHttpClient的静态代码块中加载，加之外层的ConnectionPool使用了一个专门线
程(cleanupRunnable)去自动判定连接是否达到池的存活时间(keepAliveDuration)和空闲连接(idleConnections)数量的连接进行清理，减少长时间对底层
Socket句柄的占用而带来的各种问题。
  
  Q: ObjectMapper是否能处理不加注解的Vo类？
  
  A: 目前interface-utils采用jackson作为json和xml的序列化和反序列化库,没有添加jackson支持的注解能否转换成功取决于jackson对json或者xml处理的
默认的规则,建议使用json-xml-codegen maven plugin生成相关的Java Vo实体类。