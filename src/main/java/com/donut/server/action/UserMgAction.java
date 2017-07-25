/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * @Title: UserMgAction.java 
 * @Package com.donut.server.admin 
 * @Description:
 * @author 李华洞  
 * @date 2016年6月13日 下午2:24:48 
 * @version V1.0   
 */
package com.donut.server.action;

import com.donut.server.common.AdminUrl;
import com.donut.server.common.BaseRest;
import com.donut.server.common.StringUtil;
import com.donut.server.model.UserMgModel;
import com.donut.server.service.UserMgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class UserMgAction extends BaseRest
{

    @Autowired
    private UserMgService userMgService;



    @RequestMapping(value = "selectUserList")
    public String selectUserList(@ModelAttribute("model")
                                         UserMgModel userMgModel, Model model, HttpSession session,
                                 HttpServletRequest request)
    {
        String retUrl = AdminUrl.USER_LIST;
        long startIndex = userMgModel.getStartIndex();
        // 选择页跳转
        String flg = userMgModel.getFlg();
        // 页面起始位置初期化
        userMgModel.setPageIndex(StringUtil.isEmpty(flg) ? startIndex
                : userMgModel.getPageIndex() - 1);
        String pageIndex1 = request.getParameter("pageIndex1");
        if (!StringUtil.isEmpty(pageIndex1))
        {
            userMgModel.setPageIndex(Integer.parseInt(pageIndex1));
        }
        userMgService.selectUserList(userMgModel);
        return retUrl;
    }

}
