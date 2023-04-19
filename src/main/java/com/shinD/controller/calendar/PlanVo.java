package com.shinD.controller.calendar;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlanVo {
	 private int PLAN_CODE;
	 private String PLAN_TITLE;
	 private String PLAN_CONTENT;
	 private Date START_DATE;
	 private Date END_DATE;
}
