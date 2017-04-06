<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<sx:head/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息导入</title>
</head>
<body>
	<s:form action="infoWirte">
		<s:textfield label="学生姓名" name="student.name"/>
		<s:textfield label="年龄" name="student.age"/>
		<s:radio list="#{'0':'男','1':'女' }" label="性别" name="student.sex"/>
		<s:checkboxlist name="student.interesters" list="{'足球','篮球','游泳'}" label="兴趣爱好"></s:checkboxlist>
		<s:textarea label="自我介绍" name="student.jieSao" cols="10" rows="5"/>
		<sx:datetimepicker adjustWeeks="true" displayFormat="yyyy-MM-dd" toggleType="explode" name="student.date"/>
		<s:select list="#{'0':'巫导','1':'周老师','2':'巫老师' }" label="辅导员" name="student.fuDaoYuan" listKey="key" listValue="value"/>
		<s:submit value="提交"/>
	</s:form>
</body>
</html>