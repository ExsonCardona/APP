<?PHP
 
class datos{
	
	public function startTransaction(){
		mysql_query("SET AUTOCOMMIT=0");
		mysql_query("START TRANSACTION");
	}
	public function commit(){
		mysql_query("COMMIT");
	}
	public function rollback(){
		mysql_query("ROLLBACK");
	}
	public function retornaJson($return){
		header('Content-type: application/json');
		echo json_encode($return);
	}
 
	 
}
	
?>