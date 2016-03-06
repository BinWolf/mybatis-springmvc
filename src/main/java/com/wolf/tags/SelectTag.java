package com.wolf.tags;

import com.wolf.util.LocalStringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wolf on 16/3/6.
 */
public class SelectTag extends TagSupport {
    private String groupId;
    private String dictId;
    private String business;
    private String name;
    @Override
    public int doStartTag() throws JspException {

        Map<String,String> params = new HashMap<String,String>();
        params.put("groupId",groupId);

        if(LocalStringUtils.isNotEmpty(business)){
            params.put("business",business);
        }

        List<Map<String, Object>> maps = null; //DictItemService.qryDictItemList(params);
        StringBuffer sb = new StringBuffer();
        sb.append("<select name=\"").append(name)
                .append("\" id=\"").append(getId()).append("\" >");
        for(Map<String,Object> map : maps){
            String Id = (String)map.get("dictId");
            sb.append("<option value=\"").append(Id).append("\"");
            if(LocalStringUtils.isEquals(dictId,Id)){
                sb.append(" selected ");
            }
            sb.append(">").append(map.get("dictName")).append("</option>");

        }
        sb.append("</select>");
        try {
            this.pageContext.getOut().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
