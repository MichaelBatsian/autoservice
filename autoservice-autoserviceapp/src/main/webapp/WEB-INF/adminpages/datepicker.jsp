<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/bootstrap-clockpicker.min.css">
<link rel="stylesheet" type="text/css" href="css/jquery-clockpicker.min.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery.min.js" type="text/javascript" ></script>
     <script src="js/bootstrap.js"></script>
<script type="text/javascript">
    $(function() {
        $( "#dateTimeTable" ).datepicker({
            placement:'top',
            align: 'left'
        });
    });
</script>
<script type="text/javascript" src="js/bootstrap-clockpicker.min.js"></script>
<script type="text/javascript">
    $('.timeTimeTable').clockpicker({
        placement: 'bottom',
        align: 'left',
        donetext: 'Готово'
    });
</script>