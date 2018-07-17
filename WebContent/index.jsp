<%@page import="user.User" %>
<%@include file="include/header.jsp" %>
<strong>Fantastic content maker and fabulous presenters</strong>

<p/>We are a team of dedicated people who perform intense research, 
pragmatic planning and come up with easily understandable and quality courses for student around the world. 
<p/>We follow an ongoing process of quality analysis by meticulously considering and improving our work by 
taking the feedback from the users. 
<p/>We are fantastic content maker and fabulous presenters. 
<strong>we are Simplilearn!</strong>
<%@include file="include/footer.jsp" %>


<%
User user = new User("test","testing");
String name = user.getName();
String email = user.getName();
String query = "insert into users" +
        "(name,email)"+
        "values ("+name+","+email+")";

%>
<%=query %>