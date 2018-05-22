<?php 
      require("conexion.php");

       $datos = new datos();
       $User = $_GET["Username"];
       $Password = $_GET["Password"];



        $consulta = "SELECT * FROM ts_usuario WHERE Username = '".$User."'  and password = '".$Password."' ;";                
        $sql = mysql_query($consulta);

            
            $total = mysql_num_rows($sql);

            if($total > 0){
                while ($row = mysql_fetch_array($sql)){
                   
                    $return[] = array(
                            'strError' => '1',
                            "Id"=>$row['id_user'],
                            "Username"=>$row['Username']
                    );

                
                }
                
                $datos->retornaJson(array ("User" =>$return)); 


            }   

            else{
                            $return[] = array('strError' => '-1', "value" =>   $consulta , "type" => 'error', "posicion" => 'topRight');
                            $datos->retornaJson(array ("User" =>$return)); 

            }          

        
		
 
 
?>