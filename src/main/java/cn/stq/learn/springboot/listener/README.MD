spring boot在启动过程中增加事件监听机制，为用户功能拓展提供极大的便利。
支持的事件类型四种
```
ApplicationStartedEvent - spring boot启动开始时执行的事件
ApplicationEnvironmentPreparedEvent - spring boot对应Enviroment已经准备完毕，但此时上下文context还没有创建。
ApplicationPreparedEvent - spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
ApplicationFailedEvent - spring boot启动异常时执行事件 

```