package com.wolf.tags;

import com.wolf.util.LocalStringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wolf
 */
public class MenuTreeTag extends TagSupport {


    private String serverURL;

    private String parentId;

    @Override
    public int doStartTag() throws JspException {
//        String userId = XRUtil.getObjectFromSession("user_id");
//        String orgType = XRUtil.getObjectFromSession("org_type");
        Map param = new HashMap();
//        param.put("user_id",userId);
//        param.put("org_type",orgType);
        if(parentId == null){
            parentId = "root";
        }
        param.put("parent_menu_id",parentId);
//        List res = MenuService.qryMenusByUserId(param);
        List res = null;
        HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        serverURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  request.getContextPath() ;
        StringBuffer sb = new StringBuffer();
        sb.append("<div class=\"accordion\" fillSpace=\"sidebar\">");
        printTree(sb,res);
        sb.append("</div>");
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
        String href = "";
        for(Map<String,Object> map : res){
            if(LocalStringUtils.isEquals("1", map.get("menu_level"))){
                sb.append("<div class=\"accordionHeader\">\n" +
                        "\t\t\t\t\t\t\t<h2><span>Folder</span>").append(map.get("menu_name")).append("</h2>\n" +
                        "\t\t\t\t\t\t</div>");
                sb.append("<div class=\"accordionContent\">\n" +
                        "\t\t\t\t\t\t\t<ul class=\"tree treeFolder expand\">\n");
                if(map.get("child")!=null){
                    printTree(sb,(List)map.get("child"));
                }
                sb.append("\t</ul>\n" +
                        "\t\t\t\t\t</div>");
            } else {
                if(LocalStringUtils.isNotEmptyOfObject(map.get("menu_url"))){
                    href = "href=\""+serverURL +  map.get("menu_url").toString() +"\" ";
                    sb.append("<li><a ").append(href).append("target=\"navTab\"").append("rel=\"").append(map.get("menu_id")).append("\"").append(">")
                            .append(map.get("menu_name")).append("</a>");
                }else{
                    sb.append("<li><a>").append(map.get("menu_name")).append("</a>");
                }

                if(map.get("child")!=null){
                    sb.append("<ul>");
                    printTree(sb, (List) map.get("child"));
                    sb.append("</ul>");
                }
                sb.append("</li>");

            }
        }
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
