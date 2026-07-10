<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Record" %>
<%@ page import="dao.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student register Form</title>
    <link rel="stylesheet" href="css/styles.css">
    
    <style>
body{
    margin:0;
    padding:30px;
    font-family: Arial, Helvetica, sans-serif;
    background:#f5f7fa;
}

input
{

     width:300px;
     height: 30px;
     /* border:rgb(106, 201, 207) 1px solid; */
}

label
{
    display:block;
}
.form_group
{
    display: block;
}

.form_group.success input
{
    border:rgb(21, 192, 15) 1px solid;
     
}

.form_group.error input
{
     border:rgb(235, 20, 31) 1px solid;
     background-color: coral;

}

.bovien
{
    border: 1px solid #1013be;
    
}

table.bovien {
    border-collapse: collapse;
}

th.bovien,
td.bovien{
    padding:12px;
    text-align:center;
}

th.bovien{
    background:#3498db;
    color:white;
}



    </style>
</head>
<body>
    

    <div id="container" style="width:100%;display:flex;justify-content:center;margin-top:30px;"   ">

     

        <div class="container-box">

            <h1>Student Regiter Form</h1>

            <form id="register_form" action="student" method="post">

                <div class="form_group">
                    <label for="sname">Student name</label>
                    <input
                        type="text"
                        id="sname"
                        name="studentName"
                        required>
                </div>

                <div class="form_group">
                    <label for="course">Course</label>
                    <input
                        type="text"
                        id="course"
                        name="course"
                        required>
                </div>

                <div class="form_group">
                    <label for="fee">Fee</label>
                    <input
                        type="number"
                        id="fee"
                        name="fee"
                        required>
                </div>

                <button type="submit">Submit</button>

            </form>

        </div>


       

    </div>


<div id="container" style="width: 100%;  display: flex; justify-content: center;   ">
     <div class="container-box" style="margin-top:30px;">

            <table class="bovien">
                <tr class="bovien">
                    <th class="bovien" style="width:300px">Student Name</th>
                    <th class="bovien" style="width:250px">Course</th>
                    <th class="bovien" style="width:100px">Fee</th>
                    <th class="bovien" style="width:100px">Edit</th>
                    <th class="bovien" style="width:100px">Delete</th>
                </tr>
                <%
            RecordDAO dao=new RecordDAO();
            List<Record> list = dao.getAllRecords();

            if(list != null){
                for(Record p : list){
            %>

             <tr class="bovien">
                <td class="bovien" style="width:300px"><%=p.GetStname()%></td>
                    <td class="bovien" style="width:250px"><%=p.GetCourse()%></td>
                    <td class="bovien" style="width:100px"><%=p.GetFee()%></td>
                    <td class="bovien" style="width:100px">Edit</td>
                    <td class="bovien" style="width:100px">Delete</td>

             </tr>



            <%} }%>


            </table>

        </div>

</div>

    

</body>
</html>
