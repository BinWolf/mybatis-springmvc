package com.wolf.tags;

import org.apache.commons.lang.StringUtils;
import platform.utils.IDateUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 *  提供日期格式化，JSTL的日期格式化不能传String参数
 * Created by Seven on 15/12/1.
 */
public class FormatDateTag extends TagSupport {

    private String value;
    private String pattern;//原字符串日期格式
    private String target;//要格式化成的格式

    @Override
    public int doStartTag() throws JspException {

        if(StringUtils.isEmpty(value)){
            return super.doStartTag();
        }
        if(StringUtils.isEmpty(pattern)){
            pattern = "yyyyMMddHHmmss";
        }
        if(StringUtils.isEmpty(target)){
            target = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            Date date = IDateUtils.parse(value, pattern);
            String str = IDateUtils.format(date,target);
            pageContext.getOut().print(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (IOException io){
            throw new JspException(io);
        }
        return super.doStartTag();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
