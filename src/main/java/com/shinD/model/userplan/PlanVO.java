package com.shinD.model.userplan;

import java.sql.Date;

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
public class PlanVO {
	 private int plan_code;
	 private String plan_title;
	 private String plan_content;
	 private Date start_date;
	 private Date end_date;
	 private String color;
}
