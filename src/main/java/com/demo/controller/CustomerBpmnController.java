package com.demo.controller;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import java.util.List;



import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.MyNode;
import com.demo.entity.MyNodeRelation;
import com.demo.service.CustomerBpmnService;
import com.demo.until.Result;
import com.demo.until.ResultUtil;

@RestController
@RequestMapping("/customer")
public class CustomerBpmnController {

	
	@Autowired
    private CustomerBpmnService customerBpmnService;
		
	
	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/CustomerBpmnDeployment")
	public Result CustomerBpmnDeployment() throws UnsupportedEncodingException
	{
		int process_id=1;
	    List<MyNode> myNodes=customerBpmnService.selectAllNodeByProcessId(process_id);
		
	      //生成一个模型
	  		 BpmnModel bpmnModel=new BpmnModel();
	  		//生成process
	  		Process process=new Process();
	  		process.setId("process1");//如果这个相同则，部署多次只会增加版本(启动要用这个名字进行启动)
	  		process.setName("My process");
	  		process.setExecutable(true);
		
		
		   for(MyNode myNode:myNodes)
		   {			   
			   if(myNode.getNode_type().equals("开始"))
			   {
				 //生成开始节点
					StartEvent startEvent=new StartEvent();
					startEvent.setId(myNode.getNode_id_name());
					startEvent.setName(myNode.getNode_name());
					//将开始节点添加到process中
					process.addFlowElement(startEvent);
			   }
			   else if(myNode.getNode_type().equals("过程"))
			   {
				   UserTask userTask = generateUserTask(myNode.getNode_id_name(), myNode.getNode_name());
				   process.addFlowElement(userTask);
			   }
			   
			   else if(myNode.getNode_type().equals("结束"))
			   {
				   EndEvent endEvent=generateEndEvent(myNode.getNode_id_name(), myNode.getNode_name());
				   process.addFlowElement(endEvent);
			   }
			   
		   }
		
		   //查找到该流程所有节点id的集合
		   List<Integer> ids=new ArrayList<>();
		for(MyNode myNode:myNodes)
		{
			ids.add(myNode.getNode_id());
		}
		//去关联表查找这个流程所有的节点之间的关系对象
	  List<MyNodeRelation> myNodeRelations=customerBpmnService.selectNodeRelation(ids);
		  //加在连线后面的数字
	      int count=1;		
	      //遍历所有的关系生成连线
		  for(MyNodeRelation myNodeRelation:myNodeRelations)
		  {
			  //如果关系之间没有表达式
			  if(myNodeRelation.getCondition_name()==null||myNodeRelation.getCondition_name().equals(""))
			  {
				 
				  //节点关系表的当前节点和下一节点去节点边查找这个节点的信息
				String  sourceRef=customerBpmnService.selectNodeById(myNodeRelation.getCurrentcode());
				  String targetRef=customerBpmnService.selectNodeById(myNodeRelation.getNext_code_id());
						  SequenceFlow flow1 = generateSequence("flow"+String.valueOf(count), null, sourceRef, targetRef, null, process);
						  process.addFlowElement(flow1);
												  		  
				  	count++;	 
			  }
			  
			  else
			  {
				  String  sourceRef=customerBpmnService.selectNodeById(myNodeRelation.getCurrentcode());
				  String targetRef=customerBpmnService.selectNodeById(myNodeRelation.getNext_code_id());
				  SequenceFlow flow2 = generateSequence("flow"+String.valueOf(count), myNodeRelation.getCondition_name(), sourceRef, targetRef, myNodeRelation.getCondition_expression(), process);
				  process.addFlowElement(flow2);
					  				  
				  count++;
			  }
		  }
		   
//		// 生成连线节点
//			SequenceFlow flow1 = generateSequence("flow1", null, "startevent1", "usertask1", null, process);
//			SequenceFlow flow2 = generateSequence("flow2", "小于三天", "usertask1", "usertask2", "${day<3}", process);
//			SequenceFlow flow3 = generateSequence("flow3", "大于三天", "usertask1", "usertask3", "${day>3}", process);
//			SequenceFlow flow4 = generateSequence("flow4", null, "usertask2", "endevent1", null, process);
//			SequenceFlow flow5 = generateSequence("flow5", null, "usertask3", "endevent2", null, process);
//		   
		   
		//将process添加到模型中
			bpmnModel.addProcess(process);
			//将模型转化为xml输出
			BpmnXMLConverter bpmnXMLConverter=new BpmnXMLConverter();
			byte[] convertToXml=bpmnXMLConverter.convertToXML(bpmnModel);
			String string=new String(convertToXml,"utf-8");
			System.out.println(string);
	
	
			//模型校验
			ProcessValidatorFactory processValidatorFactory=new ProcessValidatorFactory();
			ProcessValidator processValidator=processValidatorFactory.createDefaultProcessValidator();
			List<ValidationError> validate=processValidator.validate(bpmnModel);
			
			System.out.println(validate.size());
			for(ValidationError validationError:validate)
			{
				System.out.println(validationError.getProblem());
				System.out.println(validationError.isWarning());
			}
		
			
			
			
//			//部署
//			String resourceName="customer5.bpmn";
//			Deployment deployment=repositoryService
//					.createDeployment()
//					.addBpmnModel(resourceName, bpmnModel)
//					.deploy();
//			System.out.println(deployment);
			
			
		
		return ResultUtil.success();
	}
	
	
	
	
	//将生成任务封装为一个方法
    public UserTask generateUserTask(String id,String name)
    {
    	UserTask userTask=new UserTask();
    	userTask.setId(id);
    	userTask.setName(name);
    	
    	//3
    	userTask.setAssignee("${"+"user"+"}");//user默认为lists集合里面的人，不需要传递参数
        //设置固定人数
    //1	multiInstanceLoopCharacteristics.setLoopCardinality("3");
    	//设置候选人员
    //2 userTask.setCandidateUsers(lists);参数为集合
    //1,2为组合1设置固定的人数，不能动态变化
    	MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics=new MultiInstanceLoopCharacteristics();
    	//设置串行还是并行
    	multiInstanceLoopCharacteristics.setSequential(false);
    	
    	
   
    	//设置动态人数完成的人
    	//4
    	multiInstanceLoopCharacteristics.setInputDataItem("lists");
    	//5
    	//3,4,5为设置动态的人数，根据流程变量来确定
    	multiInstanceLoopCharacteristics.setElementVariable("user");//这个依赖于userTask.setAssignee("${"+"user"+"}");
    	//设置完成条件
    	multiInstanceLoopCharacteristics.setCompletionCondition("${nrOfCompletedInstances==nrOfInstances}");
    	//设置任务是多实例
    	userTask.setLoopCharacteristics(multiInstanceLoopCharacteristics);
    	  	
    	return userTask;
    }
	
    
    //生成一个结束节点
    public EndEvent generateEndEvent(String id,String name)
    {
    	EndEvent endEvent=new EndEvent();
    	endEvent.setId(id);
    	endEvent.setName(name);
    	
    	return endEvent;
    }
    
    
    
 //生成连线
    
    public SequenceFlow generateSequence(String id,String name,String sourceRef,String targetRef,String conditionExpression,Process process)
    {
    	SequenceFlow sequenceFlow=new SequenceFlow();
    	sequenceFlow.setId(id);
    	sequenceFlow.setName(name==null?"":name);
    	sequenceFlow.setSourceFlowElement(process.getFlowElement(sourceRef));
    	sequenceFlow.setTargetFlowElement(process.getFlowElement(targetRef));
    	
    	sequenceFlow.setSourceRef(sourceRef);
    	sequenceFlow.setTargetRef(targetRef);
    	
    	sequenceFlow.setConditionExpression(conditionExpression==null?"":conditionExpression);
    	return sequenceFlow;
    }
    
    
    
    
    
//    /**启动流程实例*/
//   	@RequestMapping("/startProcessInstance")
//   	public void startProcessInstance(){
//   		
//   		List<String> lists=new ArrayList<>();
//       	lists.add("三");
//       	lists.add("四");
//       	lists.add("五");
//       	Map<String, Object> map=new HashMap<>();
//       	map.put("lists", lists);   	
//   		//流程定义的key
//   		String processDefinitionKey = "process6";
//   		ProcessInstance pi = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service
//   						.startProcessInstanceByKey(processDefinitionKey,map);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
//   		System.out.println("流程实例ID:"+pi.getId());//流程实例ID    101
//   		System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   helloworld:1:4
//   		 
//   	}
//       
//       
//   	/**完成我的任务*/
//   	@RequestMapping("/completeMyPersonalTask")
//   	public void completeMyPersonalTask(){
//   		//任务ID
//   		String taskId = "2523";
//   		Map<String, Object> map=new HashMap<>();
//       	map.put("day", 4);
//   		processEngine.getTaskService()//与正在执行的任务管理相关的Service
//   					.complete(taskId,map);
//   		System.out.println("完成任务：任务ID："+taskId);
//   	}
//       
    
    
    
    
}
