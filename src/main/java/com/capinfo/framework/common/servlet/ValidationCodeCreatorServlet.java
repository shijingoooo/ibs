package com.capinfo.framework.common.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.capinfo.framework.common.util.ValidationCodeHelper;

@WebServlet(urlPatterns="/validationCode",name="validationCode")
public class ValidationCodeCreatorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(ValidationCodeCreatorServlet.class);

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        boolean needSetSystemPropertyForAwt = Boolean.parseBoolean(config.getInitParameter("needSetSystemPropertyForAwt"));
        if (needSetSystemPropertyForAwt) {
            try {
                logger.info("@@@--->即将设置系统AWT属性");
                System.setProperty("java.awt.headless", "true");
                logger.info("@@@--->成功设置了系统AWT属性");
            } catch (Throwable t) {
                logger.error("@@@--->设置系统AWT属性异常,验证码模块有可能失效", t);
            }
        }
    }

    // -------------------------------------------------//
    // ------------ Supernatural Separator -------------//
    // -------------------------------------------------//

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        byte[] bytes = ValidationCodeHelper.setValidationCodeIntoSession(session);

        if (bytes != null) {
            try {
                ServletOutputStream os = response.getOutputStream();
                IOUtils.write(bytes, os);
                os.flush();
            } catch (Throwable t) {
                logger.error("@@@--->将验证码图片写入response异常", t);
            }
        } else {
            logger.error("@@@--->创建验证码不成功");
        }
    }

}
