package Managers;

import java.util.List;

import beans.PictureBean;

public interface IDbManager {
	
	void AddPost(PictureBean post);
	void AddPosts(List<PictureBean> post);

}
