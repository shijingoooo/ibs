package com.capinfo.framework.web.controller.monitoringCompany;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.MonitoringCompany;
import com.capinfo.framework.web.service.MonitoringCompanyService;
import com.capinfo.framework.web.vo.MonitoringCompanyQueryBean;
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/monitoringCompany")
public class MonitoringCompanyController extends BaseController {

    @Resource
    private MonitoringCompanyService monitoringCompanyService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(Model model, MonitoringCompanyQueryBean companyQueryBean) throws Exception {

        List<MonitoringCompany> companys = monitoringCompanyService.findMonitoringCompanyList(companyQueryBean);
        model.addAttribute("companys", companys);

        return "monitoringCompany/list";
    }

    @RequestMapping(value = "/listByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String listByPage(Model model, MonitoringCompanyQueryBean companyQueryBean, Page<MonitoringCompany> page, Integer pageNum) throws Exception {

        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        monitoringCompanyService.findMonitoringCompanyPage(page, companyQueryBean);
        model.addAttribute("page", page);
        model.addAttribute("companyQueryBean", companyQueryBean);

        return "monitoringCompany/listByPage";
    }

    @RequestMapping(value = "/selectlist", method = {RequestMethod.POST, RequestMethod.GET})
    public String selectlist(Model model, MonitoringCompanyQueryBean companyQueryBean, Integer comId) throws Exception {
        //获得设备供应商
        companyQueryBean.setType("0");
        List<MonitoringCompany> companys = monitoringCompanyService.findMonitoringCompanyList(companyQueryBean);
        model.addAttribute("companys", companys);
        model.addAttribute("companyQueryBean", companyQueryBean);
        model.addAttribute("id", comId);

        return "monitoringCompany/selectlist";
    }

    @RequestMapping(value = "/selectplatformlist", method = {RequestMethod.POST, RequestMethod.GET})
    public String selectplatformlist(Model model, MonitoringCompanyQueryBean companyQueryBean, String companyIds, String companyNames) throws Exception {
        //获得转发平台
        companyQueryBean.setType("1,2");
        List<MonitoringCompany> companys = monitoringCompanyService.findMonitoringCompanyList(companyQueryBean);
        model.addAttribute("companys", companys);
        model.addAttribute("companyQueryBean", companyQueryBean);
        model.addAttribute("companyIds", companyIds);
        model.addAttribute("companyNames", companyNames);

        return "monitoringCompany/selectplatformlist";
    }
    @RequestMapping(value = "/savePage", method = {RequestMethod.GET})
    public String savePage(Model model, Integer companyId) throws Exception {

        MonitoringCompany company = monitoringCompanyService.findMonitoringCompanyById(companyId);
        model.addAttribute("company", company);

        return "monitoringCompany/save";
    }

    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public void save(Model model, HttpServletResponse response, HttpSession session, MonitoringCompany company, String navTabId, String callbackType, String rel) throws Exception {

        Integer userId = (Integer) session.getAttribute("userid");
        MonitoringCompanyQueryBean mc = new MonitoringCompanyQueryBean();
        mc.setCompanyName(company.getCompanyName());
        MonitoringCompany monitoringCompany = monitoringCompanyService.findMonitoringCompanyUnique(mc);
        if (monitoringCompany != null) {
            if (company.getId() == null || (monitoringCompany.getId().intValue() != company.getId().intValue())) {
                JSONObject jo = new JSONObject();
                jo.put("navTabId", rel);
                jo.put("callbackType", callbackType);
                jo.put("rel", rel);
                jo.put("statusCode", "300");
                jo.put("message", "厂商名称已存在");
                super.rendText(response, jo.toString());
                return;
            }
        }
        mc.setCompanyName(null);
        mc.setCompanyCode(company.getCompanyCode());
        MonitoringCompany mCompany = monitoringCompanyService.findMonitoringCompanyUnique(mc);
        if (mCompany != null) {
            if (company.getId() == null || (mCompany.getId().intValue() != company.getId().intValue())) {
                JSONObject jo = new JSONObject();
                jo.put("navTabId", rel);
                jo.put("callbackType", callbackType);
                jo.put("rel", rel);
                jo.put("statusCode", "300");
                jo.put("message", "厂商编码已存在");
                super.rendText(response, jo.toString());
                return;
            }
        }

        if (company.getId() != null && company.getId() != 0) {
            company.setUpdater(userId);
            monitoringCompanyService.updateMonitoringCompany(company.getId(), company);
            JSONObject jo = new JSONObject();
            jo.put("navTabId", rel);
            jo.put("callbackType", callbackType);
            jo.put("rel", rel);
            jo.put("statusCode", "200");
            jo.put("message", "操作成功");
            super.rendText(response, jo.toString());
        } else {
            company.setCreater(userId);
            company = monitoringCompanyService.saveMonitoringCompany(company);
//			if(company==null){
//				JSONObject jo = new JSONObject();
//				jo.put("navTabId", rel);
//				jo.put("callbackType", callbackType);
//				jo.put("rel", rel);
//				jo.put("statusCode", "300");
//				jo.put("message", "操作失败，厂商信息在上海库中不存在");
//				super.rendText(response, jo.toString());
//			}else{
            JSONObject jo = new JSONObject();
            jo.put("navTabId", rel);
            jo.put("callbackType", callbackType);
            jo.put("rel", rel);
            jo.put("statusCode", "200");
            jo.put("message", "操作成功");
            super.rendText(response, jo.toString());
//			}
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public void delete(Model model, HttpServletResponse response, String companyIds, String navTabId, String callbackType, String rel) throws Exception {

        if (StringUtils.isEmpty(companyIds)) {
            JSONObject jo = new JSONObject();
            jo.put("navTabId", rel);
            jo.put("callbackType", callbackType);
            jo.put("rel", rel);
            jo.put("statusCode", "300");
            jo.put("message", "请选择一条信息");
            super.rendText(response, jo.toString());
            return;
        }
        List<String> ids = new ArrayList<String>();
        ids.addAll(Arrays.asList(companyIds.split(",")));
        monitoringCompanyService.deleteMonitoringCompanyBatch(ids);

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }

    @RequestMapping(value = "/showMap", method = {RequestMethod.GET})
    public String showMap(Model model, HttpServletResponse response) throws Exception {

        return "monitoringCompany/baiduMap";
    }

    @RequestMapping(value = "/upFile", method = {RequestMethod.POST})
    public void uploadFile(Model model, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws Exception {

        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();

        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        //保存  
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);
    }

}
