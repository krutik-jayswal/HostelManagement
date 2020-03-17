<!DOCTYPE html>
<html lang="en">
<head>
  <title>Reports</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="Header.jsp" %>
<hr/>
<div class="container">
  
  <h2>Report </h2>
  
  <table class="table table-striped" width="800px">
    <thead>
      <tr>
        <th>Report Link</th>
        <th style="float:right;">Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><a href="#">Link 1</a> </td>
        <td style="float:right;">
        <button type="button" class="btn btn-success btn-sm ">Save</button>
        <button type="button" class="btn btn-info btn-sm ">Print</button>
        </td>
      </tr>
      <tr>
        <td><a href="#">Link 2 </a></td>
        <td style="float:right;">
        <button type="button" class="btn btn-success btn-sm">Save</button>
        <button type="button" class="btn btn-info btn-sm">Print</button>
        </td>
      </tr>
      <tr>
        <td><a href="#">Link 3 </a></td>
        <td style="float:right;">
        <button type="button" class="btn btn-success btn-sm">Save</button>
        <button type="button" class="btn btn-info btn-sm">Print</button>
        </td>
      </tr>
    </tbody>
  </table>
</div>

<div style="margin-top:200px;">
	<hr/>
	<%@ include file="Footer.jsp" %>
</div>
</body>
</html>
