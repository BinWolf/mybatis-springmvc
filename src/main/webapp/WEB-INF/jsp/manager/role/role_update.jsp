<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="pageContent">

    <form method="post" action="/role/updateRole" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
        <div class="pageFormContent nowrap" layoutH="97">
            <fieldset>
            <legend>修改角色</legend>
            <dl>
                <dt>角色ID：</dt>
                <dd>
                    <input type="text" readonly="readonly" name="roleId" maxlength="20" value="${role.roleId}" />
                </dd>
            </dl>
            <dl>
                <dt>角色名称：</dt>
                <dd>
                    <input type="text" name="roleName" maxlength="20"  value="${role.roleName}" />
                </dd>
            </dl>
            <dl>
                <dt>角色描述：</dt>
                <dd>
                    <input type="text" name="roleDesc" maxlength="120" value="${role.roleDesc}" />
                </dd>
            </dl>
            </fieldset>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>

</div>
￼