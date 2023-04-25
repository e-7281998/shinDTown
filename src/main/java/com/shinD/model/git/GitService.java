package com.shinD.model.git;

public class GitService {
	GitDAO dao = new GitDAO();
	public int registerGit(int user_code, String git_id) {
		return dao.registerGit(user_code, git_id);
	}
	public String getGitId(String user_code) {
		return dao.getGitId(user_code);
	}
	public int updateGiId(int user_code, String git_id) {
		return dao.updateGiId(user_code, git_id);
	}

}
