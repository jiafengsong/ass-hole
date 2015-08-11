# Asshole是啥？ #
Asshole 是具有流程编排能力的分布式事件处理框架，如图
![http://i.imgbox.com/adfl5NuX.jpg](http://i.imgbox.com/adfl5NuX.jpg)

# Asshole能解决的问题 #
## 1可扩展的事件异步处理 ##
![http://i.imgbox.com/abgZfFEk.jpg](http://i.imgbox.com/abgZfFEk.jpg)
### 使用场景：需要异步处理解耦的地方 ###
### PS：asshole对新增加一个事件及处理十分简单，只需要继承Event类和AbstractHandler类即可 ###

## 2复用基本事件进行流程编排 ##
![http://i.imgbox.com/ackXt2xE.jpg](http://i.imgbox.com/ackXt2xE.jpg)
### 使用场景：将碎片化的事件串联起来，复用基础事件，如流程1和流程2都使用了事件1，事件4，事件2。 ###
### PS：一般都认为事件框架和流程引擎是两套东东，但是本人在做品牌特卖业务的工程中发现事件处理同样需要流程编排 ###


## 3事件延迟处理 ##
![http://i.imgbox.com/acwsuHuh.jpg](http://i.imgbox.com/acwsuHuh.jpg)
### 使用场景：如活动的时候，需要延迟打标之类的，就可以定义延迟事件满足此类需求 ###
### PS:由于asshole可以使用多队列，使得延迟事件在特定的队列上，与实时处理的队列区分从而不相互影响 ###


## 4分布式事件处理 ##
![http://i.imgbox.com/abzbbJ0J.jpg](http://i.imgbox.com/abzbbJ0J.jpg)
### 使用场景：需要多台机器做负载均衡处理时 ###
### PS：这里简单的使用zookeeper协调了各个机器对任务分配 ###

## 5小数据的推送，如变量等 ##
![http://i.imgbox.com/acbmcJim.jpg](http://i.imgbox.com/acbmcJim.jpg)
### 使用场景：小数据如变量的推送同步 ###
### PS：由于数据的推送功能是依赖zookeeper自带的，因此对较大数据的推送建议还是走configserver之类 ###


# 基本运用的原理如图： #
![http://i.imgbox.com/admeS1ts.jpg](http://i.imgbox.com/admeS1ts.jpg)


# 类结构图 #
![http://i.imgbox.com/abb55OJ7.jpg](http://i.imgbox.com/abb55OJ7.jpg)

### 一步步写hello world ###
https://code.google.com/p/ass-hole/wiki/Hello_World