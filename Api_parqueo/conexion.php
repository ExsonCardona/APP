<?php 
	require("data.class.php");

	$server="localhost";
	$user="root";
	$db="bd_parqueo";
	$pass="";


	$link = mysql_connect($server, $user, $pass);
	mysql_set_charset('utf8');
		mysql_select_db($db, $link);
			if (!$link) {
    				echo 'No pudo conectarse: ' . mysql_error();
			}

			$db = new mysqli($server,$user,$pass,$db);
			if(mysqli_connect_errno()){
    				echo 'No pudo conectarse: Improved:' .mysqli_connect_error();
			}
		
 
 
?>
