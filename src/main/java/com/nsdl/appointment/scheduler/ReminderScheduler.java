package com.nsdl.appointment.scheduler;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import com.nsdl.appointment.service.AppointmentService;

@Component
@EnableScheduling
public class ReminderScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(ReminderScheduler.class);

	@Autowired
	AppointmentService appointmentService;
	
	//@Scheduled(cron = "*/3 * * * * *")
	/*public  void smsReminderSchedulerCron() {
		logger.info("inside into sceduler");
	    SimpleDateFormat timeFormatter= new SimpleDateFormat("HH:mm");
		String apptFromTime = timeFormatter.format(getAfterTime(new Date(),25));
		String apptToTime = timeFormatter.format(getAfterTime(new Date(),25+(25-1)));
		logger.info("calling send nitification method");
		appointmentService.sendNotificationBeforeTodaysAppointmentStart(apptFromTime,apptToTime,new Date());
	}*/
	
	public static Date getAfterTime(Date date, int blockTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, blockTime);
		Date dateTime = cal.getTime();
		return dateTime;
	}

}
