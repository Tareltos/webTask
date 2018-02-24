<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>XML List</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>

    <h3>Product List</h3>


    <table border="1" cellpadding="5" cellspacing="1"style="border-color: blue; box-shadow: azure; margin:auto"  >

       <tr style="background-color: aquamarine; background-position: center" >
          <th>Name</th>
         <th>Production</th>
         <th>Calories</th>
         <th>EV_Proteins</th>
         <th>EV_fats</th>
         <th>EV_carbohydrates</th>
           <th>ChocoleteType</th>
         <th>Water</th>
         <th>Sugar</th>
         <th>Fructose</th>
        <th>Vanillin</th>
        <th>Type</th>
         <th>Date</th>
          <th>Description</th>

        </tr>
       <c:forEach items="${list}" var="item" >
          <tr>
             <td>${item.name}</td>
             <td>${item.production}</td>
             <td>${item.energy}</td>
             <td>${item.energyvalue.proteins}</td>
             <td>${item.energyvalue.fats}</td>
             <td>${item.energyvalue.carbohydrates}</td>
              <td>${item.ingredients.chocolatetype}</td>
             <td>${item.ingredients.water}</td>
             <td>${item.ingredients.sugar}</td>
             <td>${item.ingredients.fructose}</td>
             <td>${item.ingredients.vanillin}</td>
             <td>${item.type}</td>
             <th>${item.date}</th>
             <th>${item.description}</th>
            </tr>
       </c:forEach>
    </table>

 </body>
</html>