[TOC]
# Web接口客户端开发指南
## 1.概述
### 1.1.什么是Web接口
Web接口，就是一个能通过Web进行调用的API，采用的是远程调用技术。通过Web接口我们可以实现不同端的数据的交互。

### 1.2.为什么有这个Web接口客户端开发指南
* 实现Web接口客户端开发的方法有很多种的，但是不同方法的所带来的性能问题也是有很大差异的。之前项目普遍采用HttpClient库开发，性能差（KeepAlive设置不当和管理连接超时等问题），而且每个项目对于json\xml格式的响应数据的没有统一解析的方法，这里管理起来特费劲，从而导致效率也变得很低。
* 为了从根本上解决以上出现的两方面的问题，针对性地开发了json-xml-codegen库和uw-httpclient库，使得Web接口客户端开发从一开始就趋向简单、统一化和高性能的方向去进行。
* 本指南也将通过对这两个库的使用来一步步引导开发者进行Web接口客户端开发。

## 2.Web接口客户端快速开发
### 2.1.首先我们得获取到即将调用的API所响应回来的数据格式与数据结构。
一般的Web接口都会提供一份详细的接口文档，我们可以从中获取数据返回格式与返回结果的结构。比如我们可以从下面两个不同的文档获取我们所要的信息：

|  | 描述 |
| :-: | :-: |
| url | http://localhost:8080/json/product |
| 请求类型 | get |
| 需要参数 | 无 |
| 返回格式 | JSON |
| 返回结果 |{"id":1,"name":"test","price":23.01} |

从上面获取到返回格式为JSON，返回结果为{"id":1,"name":"test","price":23.01}

|  | 描述 |
| :-: | :-: |
| url | http://localhost:8080/xml/product |
| 请求类型 | get |
| 需要参数 | XML |
| 返回格式 | xml |
| 返回结果 |<produce user="test"><price>20</price><name>产品1号</name><id>1</id></produce> |
从上面获取到返回格式为XML，返回结果为<produce user="test"><price>20</price><name>产品1号</name><id>1</id></produce>

### 2.2.其次在项目中使用[json-xml-codegen](http://192.168.88.88:10080/uw/json-xml-codegen)库生成VO类
* [json-xml-codegen](http://192.168.88.88:10080/uw/json-xml-codegen)库的作用就是根据数据是JSON或者XML类型转化成Java实体类，从而使得在使用uw-httpclient库时方便操作JSON/XML的返回数据，我们在使用uw-httpclient库前必须得使用json-xml-codegen库进行JSON\XML生成VO类操作。
* 拿到返回结果的数据
    * 如果是JSON数据类型，建议放到src/main/resources/json下，名为xx_vo.json(当然你也可以采用驼峰式命名)。
    * 如果是XML数据类型，建议放到src/main/resources/xml下，名为xx_vo.xml(当然你也可以采用驼峰式命名)。
    * 以下第一个是根据上面接口文档设计的一个JSON的例子（后面也以这个为例子去转化生成JSON相关类），本库支持指定生成字段的类型，你可以看到id的值显示是“long$1”，$符号前为字段类型，$符号后为字段的注释，目前支持Java原生类型,String,Date，如果没有指定类型则默认为String。
    * 第二个是根据上面接口文档设计的一个XML的例子（后面也以这个为例子去转化生成XML相关类）
```xml
{"id":"long$id","name":"String$名称","price":"double$价格"}
```
```xml
<!--因为默认是String类型，所以可以省略String$，直接写注释-->
<produce user="用户名">
    <id>id</id>
    <name>产品名字</name>
    <price>Integer$产品价格</price>
</produce>

```
    
* 新建一个Spring Boot项目，然后在src/main/resources/json新建一个product_vo.json，把上面的JSON数据复制进去。在src/main/resources/xml新建一个product_vo.xml，把上面的XML数据复制进去
* 配置pom.xml，在<build></build>的<plugins></plugins>中加入以下代码，根据自己的需求修改参数，代码注释已很清楚.
```xml
                    <plugin>
                        <groupId>com.zwy.plugin</groupId>
                        <artifactId>json-xml-codegen</artifactId>
                        <version>2.1.4</version>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <executions>
                           <execution>
                        <id>xml-test</id>
                        <configuration>
                            <!-- 生成类型 XML或者 JSON -->
                            <generationType>XML</generationType>
                            <!-- 注解类型 JACKSON2,FASTJSON,XSTREAM,JAXB Annotation -->
                            <annotationStyle>JACKSON2</annotationStyle>
                            <!-- 作者，默认为空 -->
                            <author>huangjuguan</author>
                            <!-- 生成类注释，默认为空 -->
                            <comment>xx_vo.xml的生成vo类</comment>
                            <!--是否为骆峰式命名-->
                            <camel>true</camel>
                            <!-- xml or json 数据文件所在位置,默认取src/main/resources/xml or src/main/resources/xml的*.xml和*.json文件 -->
                            <sourceDirectory>${project.basedir}/src/main/resources/xml</sourceDirectory>
                            <!-- 生成Vo输出目录,默认在 target/generated-sources/zwy 目录下面 -->
                            <outDirectory>${project.basedir}/src/main/java</outDirectory>
                            <!-- 生成文件的 packageName,会按照sourceDirectory/packageName 展开生成 -->
                            <packageName>com.example.demo.xml.vo</packageName>
                            <!-- 包含文件,默认包含所有,如果指定，则只包含指定的file -->
                            <includeFiles>
                                <file>product_vo.xml</file>
                            </includeFiles>
                        </configuration>
                    </execution>
                    <execution>
                        <id>json-test</id>
                        <configuration>
                            <!-- 生成类型 XML或者 JSON -->
                            <generationType>JSON</generationType>
                            <!-- 注解类型 JACKSON2,FASTJSON,XSTREAM,JAXB Annotation -->
                            <annotationStyle>JACKSON2</annotationStyle>
                            <!-- 作者，默认为空 -->
                            <author>huangjuguan</author>
                            <!-- 生成类注释，默认为空 -->
                            <comment>xx_vo.json的生成vo类</comment>
                            <!--是否为骆峰式命名-->
                            <camel>true</camel>
                            <!-- xml or json 数据文件所在位置,默认取src/main/resources/xml or src/main/resources/xml的*.xml和*.json文件 -->
                            <sourceDirectory>${project.basedir}/src/main/resources/json</sourceDirectory>
                            <!-- 生成Vo输出目录,默认在 target/generated-sources/zwy 目录下面 -->
                            <outDirectory>${project.basedir}/src/main/java</outDirectory>
                            <!-- 生成文件的 packageName,会按照sourceDirectory/packageName 展开生成 -->
                            <packageName>com.example.demo.json.vo</packageName>
                            <!-- 包含文件,默认包含所有,如果指定，则只包含指定的file -->
                            <includeFiles>
                                <file>product_vo.json</file>
                            </includeFiles>
                        </configuration>
                    </execution>
                        </executions>
                    </plugin>                       
```
* 这里有2个插件，使用maven命令： mvn&nbsp;  json-xml-codegen:generate@json-test（@后面加你刚刚配置的maven的id）&nbsp;&nbsp;&nbsp;&nbsp;以及mvn&nbsp;  json-xml-codegen:generate@xml-test（@后面加你刚刚配置的maven的id）
    * IDEA的maven命令快速操作，直接弹出Maven projects窗口，点击在右上方的带m的图标，然后在Command line中输入json-xml-codegen:generate@json-test，点击Execute即可运行。（然后你再重新操作一遍，把@后面的id改成xml-test，点击Execute运行）
* 控制台输出类似以下信息便代表生成成功。
```xml
[INFO] --- json-xml-codegen:2.1.4:generate (json-test) @ demo1011-v1 ---
generate for product_vo.json... start
generate for product_vo.json... end
all generate java file is ok.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.075 s

```
然后也可以看到在com.example.demo.json.vo下生成了product_vo.json的相关生成类ProductVo类，其中字段的类型跟注释都跟我们设定的一样的。
```java
package com.example.demo.json.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * This class is auto generated by tools.
 * xx_vo.json的生成vo类 
 * @author huangjuguan 
 * @since 2017-10-19 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVo {

    /**
     * id
     */
    private long id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品价格
     */
    private double price;

    public void setid(long id) {
        this.id = id;
    }

    @JsonProperty("id")
    public long getid (){
        return this.id;
    }

    public void setname(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getname (){
        return this.name;
    }

    public void setprice(double price) {
        this.price = price;
    }

    @JsonProperty("price")
    public double getprice (){
        return this.price;
    }


}
```
* 在ProductVo类中重写toString方法，即加入以下代码（注意：ProductVo类的全部内部类都要添加以下代码。）
```java
 @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.JSON_STYLE);
        return ReflectionToStringBuilder.toString(this);
    }
```

* 然后再去com.example.demo.xml.vo下，可以看到生成了product_vo.xml的相关生成类product类（目前XML生成类对于里面所有命名都是直接以xml标签的名称生成对应的对象名称，所有命名的规范性需要自己手动去修改）
```JAVA
package com.example.demo.xml.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

/**
 * This class is auto generated by tools.
 * xx_vo.xml的生成vo类 
 * @author huangjuguan 
 * @since 2017-10-19 
 */
@JacksonXmlRootElement(localName = "produce")
public class produce {

    /**
     * 产品价格
     */
    private Integer price;

    /**
     * 产品名字
     */
    private String name;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String user;

    public void setprice(Integer price) {
        this.price = price;
    }

    @JacksonXmlProperty(localName="price")
    public Integer getprice (){
        return this.price;
    }

    public void setname(String name) {
        this.name = name;
    }

    @JacksonXmlProperty(localName="name")
    public String getname (){
        return this.name;
    }

    public void setid(String id) {
        this.id = id;
    }

    @JacksonXmlProperty(localName="id")
    public String getid (){
        return this.id;
    }

    public void setuser(String user) {
        this.user = user;
    }

    @JacksonXmlProperty(localName="user")
    public String getuser (){
        return this.user;
    }


}
```
* 然后要在所有元素拥有的属性上（比如<produce user="用户名">的user）添加,isAttribute = true，如下
```JAVA
@JacksonXmlProperty(localName="user", isAttribute = true)
    public String getuser (){
        return this.user;
    }
```

* 使用时需要特别特别注意的是，生成VO类的地址如果有Java文件会被清空。


### 2.3.最后在项目中引入uw-httpclient库并处理对应的HTTP请求
* 在pom.xml引入uw-httpclient。
```xml
    <dependency>
            <groupId>com.umtone</groupId>
            <artifactId>uw-httpclient</artifactId>
            <version>0.5.8</version>
    </dependency>
```
* 已经使用json-xml-codegen生成指定的VO类，比如上面提到的ProductVo类。
* uw-httpclient提供了三种请求方式（每一种请求方式也有多种不同方法实现，具体可以去看源码）
```java
/**
     * GET请求
     * @param url 请求地址
     * @param responseType 响应类型
     * @param uriVariables 请求参数
     * @param <T> 类型
     * @return
     * @throws InterfaceException
     */
    public <T> ResponseWrapper<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
            throws InterfaceException 
```
```java
/**
     * POST请求
     * 
     * @param url 请求地址 
     * @param responseType 响应类型
     * @param requestBody 请求体
     * @param <T> 类型
     * @return
     * @throws InterfaceException
     */
    public <T> ResponseWrapper<T> postForEntity(String url, Class<T> responseType, RequestBody requestBody)
            throws InterfaceException
```
```java
/**
     * 自定义请求
     * 
     * @param request 请求
     * @param responseType 响应类型
     * @param <T> 类型
     * @return
     * @throws InterfaceException
     */
    public <T> ResponseWrapper<T> requestForEntity(Request request, Class<T> responseType)
```
* 这里展示一个json的get请求的例子。我们在之前新建的项目中再新建一个ProductJsonController类，然后模拟上面的接口文档来写一个Web接口服务端：
```java
@RestController
@RequestMapping("/json")
public class ProductJsonController {
    @GetMapping("/product")
    public String  getService() {
        public String  getTest() {
        return "{\"id\":1,\"name\":\"test\",\"price\":23.01}";
    }
    }
```
然后再写一个Web接口的客户端去调用这个/produce接口，在ProductJsonController类中加入(注意到我们是可以直接用生成类ProductVo去接收对应的JSON数据的哦)：
```java
 @GetMapping("/get")
    public String getTest() throws InterfaceException {
        HttpInterface httpInterface = new JsonInterfaceHelper();
        ProductVo product = httpInterface.getForObject("http://localhost:8080/json/product", ProductVo.class,"");
        return product.toString();
    }
```

启动项目，访问http://localhost:8080/json/get，然后就可以在浏览器中成功看到
```java
{id=1, name='test', price=23.01}
```

* 这里也展示一个post请求的例子
```java
   public String postTest() throws InterfaceException {
        HttpInterface httpInterface = JsonInterfaceHelper.getInstance();
        ProductVo productVo = new ProductVo();
        productVo.setid(100);
        productVo.setname("postTest");
        ProductVo product = httpInterface.postForObject("http://localhost:8080/json/post", ProductVo.class,productVo,HttpHelper.JSON_UTF8);
        return product.toString();
    }
```
* 当然，你也能根据具体需求去自定义HttpInterface的参数配置
```java
    HttpInterface httpInterface = new JsonInterfaceHelper(
        new HttpConfig.Builder()
            .connectTimeout(3000) //配置连接超时参数,默认10000
            .readTimeout(3000) //读超时参数,默认10000
            .writeTimeout(3000) //写超时参数,默认10000
            //以及对这些连接的的连接新池参数配置
            .maxIdleConnections(5) //最大空闲连接数,默认5个
            .keepAliveDuration(300000).build()); //,最大存活时间,默认300000
```
* 这里再展示一个xml的get请求的例子。我们在之前新建的项目中再新建一个ProductXmlController类，然后模拟上面的接口文档来写一个Web接口服务端。
```java
@RestController
@RequestMapping("/xml")
public class ProductXmlController {
    @GetMapping("/product")
    public String  getService() {
       return "<produce user=\"test\"><price>20</price><name>产品1号</name><id>1</id></produce>";
    }
   
}
```

* 然后再写一个Web接口的客户端去调用这个/produce接口(注意xml是要new  XmlInterfaceHelper对象的，跟json的不一样。)
```java
@GetMapping("/get")
    public String getTest1() throws InterfaceException {
        HttpInterface httpInterface = new XmlInterfaceHelper();
        produce product = httpInterface.getForObject("http://localhost:8080/xml/product", produce.class,"");
        return product.toString();
    }
```
启动项目，访问http://localhost:8080/xml/get，然后就可以在浏览器中成功看到
```java
<produce user="test"><price>20</price><name>产品1号</name><id>1</id></produce>
```
* post的跟json差不多的，自定义HttpInterface的参数配置也跟json差不多。

* 这样便迅速完成了Web接口客户端开发。

## 3.附录

## 3.1.json-xml-codegen库

### 3.1.1.版本更新记录
* 2.1.4 更新:

主要添加@JsonIgnoreProperties(ignoreUnknown = true)注解,防止Vo字段与json/xml数据不一致而引发错误
* 2.1.3 更新:
主要解决非Camel时getter setter大小写问题


* 2.1.2 更新:

Data类型引用问题

* 2.1.1 更新:

支持对象默认以new对象形式初始化(原始类型和String除外)

* 2.1.0 更新:

注解默认生成在Vo的getter器上,避免非驼峰式属性Vo序列化时生成两个不同的属性;
XML类型生成Vo时默认使用jackson-dataformat-xm,支持XML属性(需要手动修改注解添加 isAttribute = true),XML CDATA标签(需要手动添加注解);
默认数组生成Vo带有JacksonXmlElementWrapper注解,并设定了useWrapping=false,详情见wiki;


* 2.0.0 更新feature:

支持指定生成字段的类型,指定方式于json或者xml指定值为{propName: "Date$这是一个日期"},$符号前为字段类型,目前支持
Java原生类型,String,Date;
默认生成JavaDoc注释

