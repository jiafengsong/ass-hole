下面是一个流程模板的配置



&lt;process name="order\_card"&gt;


> 

&lt;start name="start" &gt;


> > 

&lt;transition to="order\_create"/&gt;



> 

&lt;/start&gt;



> 

&lt;node name="order\_create"  classname="com.tmall.asshole.mock.ep.TestEvent1" hashNum="1"&gt;


> > 

&lt;transitions&gt;


> > > 

&lt;transition to="order\_execute" /&gt;



> > 

&lt;/transitions&gt;



> 

&lt;/node&gt;



> 

&lt;node name="order\_execute" classname="com.tmall.asshole.mock.ep.TestEvent1"&gt;


> > 

&lt;transitions&gt;


> > > 

&lt;transition to="order\_end"/&gt;



> > 

&lt;/transitions&gt;



> 

&lt;/node&gt;



> 

&lt;node foreach ="true"  name="order\_end"    classname="com.tmall.asshole.mock.ep.TestEvent1"&gt;


> > 

&lt;transitions&gt;


> > > 

&lt;transition to="end"/&gt;



> > 

&lt;/transitions&gt;



> 

&lt;/node&gt;




> 

&lt;end name="end"/&gt;




&lt;/process&gt;



对于


&lt;node name="order\_create"  classname="com.tmall.asshole.mock.ep.TestEvent1" hashNum="1"&gt;


> 

&lt;transitions&gt;


> > 

&lt;transition to="order\_execute" /&gt;



> 

&lt;/transitions&gt;


> 

&lt;/node&gt;



### node name 表示流程节点的名称   --- 必选 ###
### classname 表示该节点的事件类型  --- 必选 ###
### hashNum   用来指定执行该节点的执行hash值，如果不填则随机分配，如果指定则会指定=到某台机器的某个线程执行（对于有并发问题的可以考虑用此法解决） ###
### foreach  用来指定该节点处理后可能会有多个节点并行处理 详见foreach的用法 ###
