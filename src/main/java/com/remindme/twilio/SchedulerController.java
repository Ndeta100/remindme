package com.remindme.twilio;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
    private  static final  String SCHEDULE_TASKS="scheduledTasks";
    private ScheduledAnnotationBeanPostProcessor postProcessor;
    private SmsScheduler scheduler;
    @GetMapping(value = "/stop")
    public String stopSchedule(){
        postProcessor.postProcessBeforeDestruction(scheduler, SCHEDULE_TASKS);
        return "OK";
    }
    @GetMapping(value = "start")
    public String startSchedule(){
        postProcessor.postProcessAfterInitialization(scheduler,SCHEDULE_TASKS);
        return "OK";
    }
    @GetMapping(value = "/list")
    public String listSchedules() throws JsonProcessingException{
        Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
        if(!setTasks.isEmpty()){
            return setTasks.toString();
        }else {
            return  "Currently no scheduler tasks are running";
        }
    }

}
