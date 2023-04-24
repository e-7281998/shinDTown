package com.shinD.model.git;

public class GitService {
	GitDAO dao = new GitDAO();
	public int registerGit(int user_code, String git_id) {
		return dao.registerGit(user_code, git_id);
	}

}
