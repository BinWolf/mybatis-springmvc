/**
 * Created by wolf on 15/11/23.
 */

/**
 * 登录
 */
$(function(){
    $("#okBtn").click(function(){
        var loginName = $.trim($("#loginName").val());
        if(loginName==null || loginName==""){
            alert("用户名不能为空!");
            return false;
        }
        var passwd = $.trim($("#passwd").val());
        if(passwd==null || passwd==""){
            alert("用户名不能为空!");
            return false;
        }

        var password = HMAC_SHA256_MAC(loginName, passwd);
        $("#passwd").val(password);

        $("#loginForm").submit();

    });

    $.post();
});