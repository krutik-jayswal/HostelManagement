<!DOCTYPE html>
<html>
<head>
	<title>Contact Us</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<hr/>

<div class="container">
  <h2>Contact Us :</h2>
  <hr/>
  
   <div class="form-group">
      <label for="name">Full Name:</label>
      <input type="text" required class="form-control" id="name" placeholder="Enter Full Name">
    </div>
    
    <div class="form-group">
      <label for="contact">Contact:</label>
      <input type="text" class="form-control" required id="contact" placeholder="Enter contact">
    </div>
    
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" required id="email" placeholder="Enter email">
    </div>
   
    <div class="form-group">
      <label for="message">Message:</label>
      <textarea class="form-control" style="resize: none;" id="message"></textarea>
    </div>
    
    <input type="submit" id="submitBtn" class="btn btn-success"/>
</div>

<hr/>
<%@ include file="Footer.jsp" %>
</body>
<script>
	$('#submitBtn').click(function(){
		alert('Your query has been submitted.!');
		$('#name').val(null);
		$('#contact').val(null);
		$('#email').val(null);
		$('#message').val(null);
	})
</script>
</html>
