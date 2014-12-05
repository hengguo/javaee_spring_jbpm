package demo;

import java.util.HashSet;
import java.util.Set;

import org.jbpm.api.ProcessInstance;
import org.junit.Assert;
import org.junit.Test;

public class ConcurrencyGraphBasedTest extends BaseTestCase {

	@Override
	protected String getResourcePath() {
		return "jpdl/ConcurrencyGraphBased.jpdl.xml";
	}
	
	@Test
	public void test(){
		//发起流程实例
		ProcessInstance processInstance = executionService .startProcessInstanceByKey("ConcurrencyGraphBased");
		String pid = processInstance.getId();
		Set<String> expectedActivityNames = new HashSet<String>();
		expectedActivityNames.add("send invoice");
		expectedActivityNames.add("load truck");
		expectedActivityNames.add("print shipping documents");
		//断言当前活动即为产生的3个分支
		Assert.assertEquals(expectedActivityNames, processInstance.findActiveActivityNames());
		//发出执行信号通过"send invoice"活动，这时候 流程会在final join上等待其它分支的到来
		String sendInvoiceExecutionId = processInstance.findActiveExecutionIn("send invoice").getId();
		
		processInstance = executionService.signalExecutionById(sendInvoiceExecutionId);
		expectedActivityNames.remove("send invoice");
		Assert.assertNotNull(processInstance.findActiveExecutionIn("load truck"));
		Assert.assertNotNull(processInstance.findActiveExecutionIn("print shipping documents"));
		//发出执行信号通过剩下的第一个分支-load truck分支
		String loadTruckExecutinId = processInstance.findActiveExecutionIn("load truck").getId();
		processInstance = executionService.signalExecutionById(loadTruckExecutinId);
		expectedActivityNames.remove("load truck");
		//发出执行信号通过剩下的第一个分支-print shipping documents分支
		String printShippingDocumentsId = processInstance.findActiveExecutionIn("print shipping documents").getId();
		processInstance = executionService.signalExecutionById(printShippingDocumentsId);
		expectedActivityNames.remove("print shipping documents");
		expectedActivityNames.add("drive truck to destination");
		Assert.assertEquals(expectedActivityNames, processInstance.findActiveActivityNames());
		Assert.assertNotNull(processInstance.findActiveExecutionIn("drive truck to destination"));
		//发出执行信号通过 drive truck to destinamtion活动
		String driveTruckExecutionId = processInstance.findActiveExecutionIn("drive truck to destination").getId();
		processInstance = executionService.signalExecutionById(driveTruckExecutionId);
		
		//最终的聚合活动final join 等到了它的最后一个注入转移后，流向了end活动，流程实例结束
		Assert.assertTrue(processInstance.isEnded());
		Assert.assertNull("execution	"+pid+" should not exist", executionService.findExecutionById(pid));
		
	}
	

}
