<%@page import="com.hostel.mgt.controller.MyProfileCtl"%>

<!DOCTYPE html>
<html>
<head>
	<title>About Us</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
 </head>
<body>
<%@ include file="Header.jsp" %>

<hr/>

<div class="container">
  <h2>About Us :</h2>
  <hr/>
  
  <div class="row">
	  <div class="col-sm-7">
	  	<h2 class="text-center">My Hostel</h2>
	  	
	  	<p>
	  		A hostel is a budget accommodation with a minimum of 1 dormitory and a common area!

			Simple as that. Period!
			
			We decided to dedicate a full article to the topic of what is a hostel. This article is part of our ultimate guide to hostels.
			
			Important also, make sure you check out the Hostel Packing List (including 23 items to pack).
			
			There we are keen to walk you through the different hostel definitions out there. We will walk you through the process to understand what is the meaning of hosteling. We will be starting with the very basic definition of a hostel.
			
			Then we will jump into the details to see what Hosteling actually means – for travelers, for the owners.

	  	</p>
	  </div>
	  <div class="col-sm-5">
	  	<div class="img-fluid">
	  		<img src="<%=HMSView.APP_CONTEXT%>/image/hostel.jpg" height="300px" width="500px" alt="hostel_image">
	  	</div>
	  </div>
  </div>
  
</div>

<hr/>
<div style="margin-top:100px;">
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>
