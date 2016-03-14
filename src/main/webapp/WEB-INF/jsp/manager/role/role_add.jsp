<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<form action="/role/add" id="role_addForm"
      class="pageForm required-validate" method="post" onsubmit="return validateCallback(this, dialogAjaxDone);">
    <div class="pageFormContent" layoutH="30">
        <fieldset>
            <legend>新增角色</legend>
            <dl>
                <dt>角色ID：</dt>
                <dd>
                    <input class="required" id="roleId" name="roleId" type="text">
                </dd>
            </dl>
            <dl>
                <dt>角色名称：</dt>
                <dd>
                    <input class="required" id="roleName" name="roleName" type="text">
                </dd>
            </dl>
            <dl>
                <dt>角色描述：</dt>
                <dd>
                    <input class="required" id="roleDesc" name="roleDesc" type="text">
                </dd>
            </dl>
        </fieldset>

        <div class="formBar">
            <ul>
                <li><div class="buttonActive">
                    <div class="buttonContent">
                        <button  type="submit"  >保存</button>
                    </div>
                </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</form>
