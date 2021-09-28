# InterconnectedChat-Backend

Share the chat messages across Minecraft Servers via HTTP backend powered by Spring Boot, this is the backend part of
the project.

**English** [简体中文](README-CN.md)

## 特点

- 无需依赖 BungeeCord,Velocity 等代理端即可跨服共享聊天记录
- 部署方便，即开即用

## 编译

1. 运行 `gradle build`
2. 编译成功后，文件将会生成于 `build/libs/InterconnectedChat-0.0.1-SNAPSHOT.jar`

## 部署

- 运行 `java -jar InterconnectedChat-0.0.1-SNAPSHOT.jar`

## 配置

1. 前往 InterconnectedChat-0.0.1-SNAPSHOT.jar 内部，寻找 `BOOT-INF/classes/application.properties` 文件，该文件内容大致如下

```properties
spring.mvc.converters.preferred-json-mapper=gson
server.port=8080
interconnectedChat.enableServerNameWhitelist=false
interconnectedChat.allowedServerMame=allow1,allow2
```

2.`server.port` 代表 HTTP 端口，默认值为 8080，如无特殊需求请不要改动

3.`interconnectedChat.enableServerNameWhitelist` 代表是否开启服务器白名单，开启后，只有设置了指定服务器名称的服务器才被允许上传聊天记录信息

4.`interconnectedChat.allowedServerMame` 代表允许的服务器名单，仅当 `interconnectedChat.enableServerNameWhitelist` 开启时生效，使用 `,`
作为服务器名称分隔符

5. [部署和配置 Bukkit 插件](https://github.com/shaokeyibb/InterConnectedChatPlugin/README.md)

## 一些不足

- 使用了明文，未加密的手段进行不安全的 HTTP 传输，因此可能导致数据泄露，请在使用时注意尽量不要进行广域网传输（除非您认为您的数据并不重要）

## 开源许可

本项目使用 [GPLv3](LICENSE) 许可协议授权