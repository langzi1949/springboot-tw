#### 说明

```text
本项目是基于SpringBoot 2.x构建的项目。
多环境的配置信息如何使用：
    一般来说，会在resource文件夹下建 application-dev application-test applicaiton-prod等相关的
    properties文件，在application.properties中 使用  spring.profiles.active = dev 进行标志

```

这个项目遇到的一个坑就是使用了lombok插件，简化了Getter/Setter等相关的代码。
需要再Gradle配置文件中添加相关的依赖，具体的请参考`build.gradle`

PS：如果遇到读取配置文件乱码的话，请在配置文件中添加编码配置。


