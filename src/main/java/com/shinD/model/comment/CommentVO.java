package com.shinD.model.comment;

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
public class CommentVO {
	 private int COM_CODE;
	 private int POST_CODE;
	 private int USER_CODE;
	 private String COM_COMMENT;
	 private Date COM_CREATE;
	public int getCOM_CODE() {
		return COM_CODE;
	}
	public void setCOM_CODE(int cOM_CODE) {
		COM_CODE = cOM_CODE;
	}
	public int getPOST_CODE() {
		return POST_CODE;
	}
	public void setPOST_CODE(int pOST_CODE) {
		POST_CODE = pOST_CODE;
	}
	public int getUSER_CODE() {
		return USER_CODE;
	}
	public void setUSER_CODE(int uSER_CODE) {
		USER_CODE = uSER_CODE;
	}
	public String getCOM_COMMENT() {
		return COM_COMMENT;
	}
	public void setCOM_COMMENT(String cOM_COMMENT) {
		COM_COMMENT = cOM_COMMENT;
	}
	public Date getCOM_CREATE() {
		return COM_CREATE;
	}
	public void setCOM_CREATE(Date cOM_CREATE) {
		COM_CREATE = cOM_CREATE;
	}
	@Override
	public String toString() {
		return "CommentVO [COM_CODE=" + COM_CODE + ", POST_CODE=" + POST_CODE + ", USER_CODE=" + USER_CODE
				+ ", COM_COMMENT=" + COM_COMMENT + ", COM_CREATE=" + COM_CREATE + "]";
	}
	 
	 
}
