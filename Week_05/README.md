# 作业：实现Spring Bean的装配
## 在xml中进行显示配置
```$xslt
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                            http://www.springframework.org/schema/util
                            http://www.springframework.org/schema/util/spring-util.xsd">

    <!--简单装配-->
    <!--id属性是Spring找到的这个Bean的编号， 不过id不是一个必需的属性，如果没有-->
    <!--声明它，那么 Spring将会采用“全限定名叫number}"的格式生成编号。-->
    <!--如果只声明一个这样的类，而没有声明id="role1"， -->
    <!--那么 Spring 为其生成的编号就是 "Role#0"。-->
    <!--当它第二次声 明没有 id属性的 Bean 时，编号就是 "Role#1"-->
    <bean id="role1" class="com.string.test.xml.Role">
        <property name="id" value="1"/>
        <property name="name" value="张三"/>
    </bean>

    <!--如果要注入自定义的类，则需要引用-->
    <bean id="source" class="com.string.test.xml.Source">
        <property name="fruit" value="苹果"/>
        <property name="sugar" value="少糖"/>
        <property name="size" value="大杯"/>
    </bean>

    <bean id="juice" class="com.string.test.xml.MakeJuice">
        <property name="name" value="喜茶"/>
        <property name="source" ref="source"/>
    </bean>

    <!--集合类型-->
    <bean id="list1" class="com.string.test.xml.ListParam">
        <property name="list">
            <list>
                <value>111</value>
                <value>222</value>
                <value>333</value>
            </list>
        </property>
    </bean>

    <!--命名空间-->
    <bean id="role2" class="com.string.test.xml.Role" p:id="2" p:name="王五"/>
    <bean id="role3" class="com.string.test.xml.Role" p:id="2" p:name="王五"/>
    <util:list id="list2">
        <ref bean="role2"/>
        <ref bean="role3"/>
    </util:list>

    <bean id="userList" class="com.string.test.xml.UserList" p:roleList-ref="list2" />
    
</beans>
```

## 在java的接口和类中进行显示配置
* @Configuration
* 组建扫描@Component&@ComponentScan

## 隐式的bean发现机制和自动装配
* @Autowired
* 注意自动装配的歧义性：@Qualifier和@Primary

# 作业：实现自动配置和starter
## 自定义starter条件
* 根据条件检查classpath下对应的类，也就是说需要提供对应可检查的类；
* 当满足条件时能够生成定义的Bean，并注册到容器中去；
* 能够自动配置项目所需要的配置；

## 第0步：在pom.xml中引入SpringBoot自动化配置依赖spring-boot-autoconfigure：
```$xslt
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-autoconfigure</artifactId>
    <version>2.1.5.RELEASE</version>
</dependency>
```
## 第一步：创建Student对象

## 第二步：创建该对象的配置类StudentProperties,用于封装application.properties或application.yml中的基础配置
* @ConfigurationProperties(prefix = "student")
* 配置前缀以student开头
* 通过在自动化配置类中的@ConfigurationProperties注解来进行对应的属性的装配
## 第三步：创建自动化配置类StudentAutoConfiguration
* @Configuration用来声明该类为一个配置类
* @ConditionalOnClass注解说明只有当Student类存在于classpath中时才会进行相应的实例化
* @EnableConfigurationProperties将application.properties中对应的属性配置设置于StudentProperties对象中
* @Bean表明该方法实例化的对象会被加载到容器当中
* @ConditionalOnMissingBean指明当容器中不存在Student的对象时再进行实例化
* @ConditionalOnProperty指定了配置文件中student.enabled=true时才进行相应的实例化。
## 第五步：在resources中创建META-INF/spring.factories
* 注册StudentAutoConfiguration类：org.springframework.boot.autoconfigure.EnableAutoConfiguration=自动配置类路径
* 如果有多个自动配置类，用逗号分隔换行即可。

## 第六步：在其他项目中引用此自定义的starter
```$xslt
<dependency>
   <groupId>com.geek</groupId>
   <artifactId>spring-boot-starter-student</artifactId>
   <version>1.0-SNAPSHOT</version>
</dependency>
```
## 第七步：在配置文件中设置参数
```$xslt
student.enabled=true
student.id=1
student.name=111
```

## 第八步：访问并输出结果
```$xslt
Student(id=1, name=111)
```

# 总结Starter的工作流程：
* Spring Boot在启动时扫描项目所依赖的JAR包，寻找包含spring.factories文件的JAR包；
* 根据spring.factories配置加载AutoConfiguration类；
* 根据@Conditional注解的条件，进行自动配置并将Bean注入Spring容器。

# 作业：JDBC
## springboot2.0及以上采用的默认数据库连接池就是Hikari。
