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
public class RoleSettingTreeTag extends TagSupport {
    private String roleId;
    @Override
    public int doStartTag() throws JspException {
        Map map = new HashMap();
        map.put("config_RoleId",roleId);
//        map.put("org_type", RedisUtil.getObjectFromSession("org_type"));
//        map.put("user_id",XRUtil.getObjectFromSession("user_id"));
//        List<Map<String,Object>> res =  MenuService.qryAllMenusByUserId(map);
        List<Map<String,Object>> res = null;
        StringBuffer sb = new StringBuffer();
        sb.append(" <ul class=\"tree treeFolder treeCheck expand\">");
       // printTree(sb,res);
        for(Map<String,Object> menu: res){
            sb.append("<li><a tname=\"menus\" tvalue=\"").append(menu.get("menu_id")).append("\"  ")
                    .append(menu.get("isChecked") == null ? "" : "checked=\"true\"").append(" >").append(menu.get("menu_name")).append("</a></li>");
        }
        sb.append("</ul>");
        try {
            pageContext.getOut().print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return super.doStartTag();
    }
    /**
     * 迭代打印菜单树 dwz tree
     * @param sb
     * @param res
     */
    private void printTree(StringBuffer sb,List<Map<String,Object>> res){
        if(res==null || res.isEmpty()){
            return ;
        }
        for(Map<String,Object> map : res){
            if(LocalStringUtils.isEquals("1", map.get("menu_level"))){
                sb.append("<div class=\"accordionHeader\">\n" +
                        "\t\t\t\t\t\t\t<h2><span>Folder</span>").append(map.get("menu_name")).append("</h2>\n" +
                        "\t\t\t\t\t\t</div>");
                sb.append("<div class=\"accordionContent\">\n" +
                        "\t\t\t\t\t\t\t<ul class=\"tree treeFolder treeCheck expand\">\n");
                if(map.get("child")!=null){
                    printTree(sb,(List)map.get("child"));
                }
                sb.append("\t</ul>\n" +
                        "\t\t\t\t\t</div>");
            } else {
                sb.append("<li><a ").append("tname=\"").append("menus").append("\"")
                        .append(" tvalue=\"").append(map.get("menu_id")).append("\"")
                        .append(map.get("isChecked") == null ? "" : "checked=\"true\"").append(">")
                        .append(map.get("menu_name")).append("</a>");
                if(map.get("child")!=null){
                    sb.append("<ul>");
                    printTree(sb, (List) map.get("child"));
                    sb.append("</ul>");
                }
                sb.append("</li>");

            }
        }
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
