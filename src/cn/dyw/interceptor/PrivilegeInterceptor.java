package cn.dyw.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.dyw.entity.User;

public class PrivilegeInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext context = ActionContext.getContext();
		
		User user = (User) context.getSession().get("user");
		
		if(user != null){
			return arg0.invoke();
		}
		context.put("msg", "ÇëÏÈµÇÂ¼");
		return Action.LOGIN;
	}

}
