package com.emalug.apparqueos.Constantes;

/**
 * Created by Janeth on 20/05/2018.
 */

public class Constantes {
    public static final String  ip_base = "http://192.168.43.107/";
    public static final String  direccion = "Api_Parqueo/";

    public static final String  Url_base_post = "Ws_Post.php";
    public static final String  Url_base_get = "Ws_get.php";

    public static final String  Url_base_get_final = ip_base  + direccion +  Url_base_get;
    public static final String  Url_base_post_final = ip_base  + direccion +  Url_base_post;




    /* PARAMETROS LOGIN USUARIO */
    public static final String op = "op";
    public static final String Username = "Username";
    public static final String Password = "Password";
    public static final String Tipo = "Tipo";
}
