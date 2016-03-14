<%@ page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{
"statusCode":"200",
"message":"\u64cd\u4f5c\u6210\u529f",
"navTabId":"${navTabId}",
"rel":"",
"callbackType":<c:if test="${callBackType == null}">"closeCurrent"</c:if><c:if test="${callBackType != null}">"${callBackType}"</c:if>,
"forwardUrl":"${home}${forwardUrl}",
"confirmMsg":""
}
