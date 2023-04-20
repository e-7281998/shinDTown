package com.shinD.model.calendar;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarVO {
	
	private int plan_code;
	private String plan_title;
	private String plan_content;
	private Date start_date;
	private Date end_date;

}
