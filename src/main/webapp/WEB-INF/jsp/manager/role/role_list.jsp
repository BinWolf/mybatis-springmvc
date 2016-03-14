<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pagerForm" method="post" action="/role/list">
    <input type="hidden" name="pageNum" value="${pageNum}" />
    <input type="hidden" name="pageSize" value="${pageSize}" />
</form>

<div  class="pageHeader">
    <form id="salesManForm" onsubmit="return navTabSearch(this);"  method="post" action="/role/list">

        <div class="searchBar">
            <ul class="searchContent">
                <li >
                    <label style="width:45px">角 色：</label>
                    <input name="roleName" id="roleName" type="text" size="20" value="${roleName}"/>
                </li>
                <li style="width:100px">
                    <div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>

<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="/page/manager/role/role_add" target="dialog" rel="role_add"><span>添加</span></a></li>
            <li><a class="delete" href="/role/deleteRole?roleId={roleId}" target="ajaxTodo" title="确定要删除吗？" warn="请选择一个角色"><span>删除</span></a></li>
            <li><a class="delete" href="/role/deleteRoles" rel="roleIds" title="确实要删除这些记录吗?" target="selectedTodo"><span>批量删除</span></a></li>
            <li><a class="edit" href="/role/toUpdateRole?roleId={roleId}" target="navTab" rel="role_update" warn="请选择一个角色"><span>修改</span></a></li>
        </ul>
    </div>

    <div id="w_list_print">
        <table class="table" width="100%" nowrapTD="false" targetType="navTab" desc="desc" layoutH="115">
            <thead >
            <tr>
                <th width="20px;"><input type="checkbox" class="checkboxCtrl" group="roleIds">全选</th>
                <th align=center>角色名称</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}">
                <tr target="roleId" rel="${item.roleId }">
                    <td width="20px;"><input name="roleIds" value="${item.roleId}" type="checkbox"></td>
                    <td align=center>
                        ${item.roleName }
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

    <div class="panelBar" >
        <div class="pages">
            <span>显示</span>
            <select name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20">20</option>
            </select>
            <span>条，共${totalCount}条</span>
        </div>
        <div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="20" pageNumShown="10" currentPage="1"></div>
    </div>
</div>