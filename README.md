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
  start 仅有一个transition
  state(状态活动) 流程处于中断等待的状态，可以对应多个transaition,
		signalExecutionXxxx触发流程的执行
