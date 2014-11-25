spring mybatis hibernate3 jbpm4 deomo.


JBPM
开始一个流程（启动流程）	-	完成流程

完成流程操作taskService.completeTask
completeTask(taskId)
completeTask(taskId, variables)
completeTask(taskId, outcome)
completeTask(taskId, outcome, variables)
根据指定任务的ID，指定下一步的转移路径， 设入变量，完成操作任务

Process
  start： 仅有一个transition
  
  state(状态活动)： 流程处于中断等待的状态，可以对应多个transaition,
		signalExecutionXxxx触发流程的执行
		
  decision” 使用decision活动判断流向哪个转移
  (decision活动具有更加严格的条件判断特性，如不定义默认路径，则无条件满足，即报错，
    但是state将流向state活动定义的第一条流出转移，从而进行下去)
	
  fork-join: 流程并发执行
  
  task(人工任务活动): 
    assignee属性引用了一个用户，即负责完成任务的人。
  
