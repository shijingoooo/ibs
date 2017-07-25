package com.capinfo.framework.common.interceptor;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

	
	//进入 Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//获取请求的url
		String url = request.getRequestURI();
		if(url.indexOf("/listByPage")>=0){
			Map<String,String[]> params = request.getParameterMap();
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){
				String param = it.next();
				if(params.get(param)[0].indexOf("%")>-1){
					JSONObject jo = new JSONObject();
					jo.put("statusCode", "300");
					jo.put("message", "输入信息不能含有特殊字符!");
					try {
						response = initResponseHeader("text/html", response);
						response.getWriter().write(jo.toString());
						response.getWriter().flush();
					} catch (IOException e) {
						throw new RuntimeException(e.getMessage(), e);
					}
					return false;
				}
			}
		}
		//判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
		//这里公开地址是登陆提交的地址
		if(url.indexOf("/search")>=0 || url.indexOf("/login")>=0){
			//如果进行登陆提交，放行
			return true;
		}
		
		//判断session
		HttpSession session  = request.getSession();
		//从session中取出用户身份信息
		String username = (String) session.getAttribute("username");
		
		if(username != null){
			//身份存在，放行
			return true;
		}
		
		JSONObject jo = new JSONObject();
		jo.put("statusCode", "301");
		jo.put("message", "会话失效,请重新登录");
		try {
			response = initResponseHeader("text/html", response);
			response.getWriter().write(jo.toString());
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return false;
	}

	//进入Handler方法之后，返回modelAndView之前执行
	//应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("HandlerInterceptor1...postHandle");
		
	}

	//执行Handler完成执行此方法
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("HandlerInterceptor1...afterCompletion");
	}
	
	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(final String contentType, HttpServletResponse response) {
		//分析headers参数
		String encoding = "UTF-8";
		boolean noCache = true;
		//设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			//Http 1.0 header
			response.setDateHeader("Expires", 0);
			response.addHeader("Pragma", "no-cache");
			//Http 1.1 header
			response.setHeader("Cache-Control", "no-cache");
		}

		return response;
	}

}
