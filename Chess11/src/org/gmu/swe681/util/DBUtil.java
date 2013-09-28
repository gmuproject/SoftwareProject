/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmu.swe681.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nafiseh
 */
public class DBUtil {
    	private Connection conn = null;
        //https://apollo.ite.gmu.edu:4443/isqlplus
    	
    	private static String url = "jdbc:mysql://localhost/chess";
    	private static String userName = "dbuser";
    	private static String pwd = "dbpass";
    	private PreparedStatement stmt1 = null;
        private PreparedStatement stmt2 = null;
        private PreparedStatement stmt3 = null;
        private PreparedStatement stmt = null;
        private ResultSet rs = null;
        private Object[] pass;
        
        protected Connection createConnection() throws Exception 
        {
			try 
			{
					Class.forName("com.mysql.jdbc.Driver");
					// Get a connection
					conn = DriverManager.getConnection(url, userName, pwd);
			} catch (Exception except) {
				except.printStackTrace();
				throw except;
			}
			return conn;
        }
                
        public void RegisterUser(UserInfoVO userVO) 
        {
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                // Add Hash
                try 
                {
                     pass=getHash(userVO.getpassWord());
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
                }

                try 
                {
					createConnection();
					System.out.println("after connection");
					if(conn != null) 
					{
		                 //insert record in password_table
						//stmt1 = conn.prepareStatement("insert into password_table (login_id,password,created_ts,last_updated_ts,salt) values (?,?,?,?,?)");
						stmt1 = conn.prepareStatement("insert into password_table (login_id,password,salt) values (?,?,?)");
		                stmt1.setString(1, userVO.getuserName());
			            stmt1.setString(2, (String)pass[1]);
		               // stmt1.setTimestamp(3, ts);
					   // stmt1.setTimestamp(4, ts);
		               //prepStatement.setBytes(5, (byte[])password[0]);
			            stmt1.setBytes(3, (byte[])pass[0]);
		               // stmt1.setBytes(5, (byte[])pass[0]);
		                System.out.println("before update");
						stmt1.executeUpdate();
						System.out.println("AddUser ------ SUCCESSFUL");
						stmt1.close();
		//              insert record in user_details
		//              stmt2=conn.prepareStatement("Insert into user_details (login_id,f_name,l_name,email,created_ts,last_updated_ts)VALUES (?,?,?,?,?,?)");
						stmt2=conn.prepareStatement("Insert into user_details (login_id,f_name,l_name,email)VALUES (?,?,?,?)");
		                stmt2.setString(1, userVO.getuserName());
		                System.out.println("firdt nsm"+ userVO.getfirstName());
			            stmt2.setString(2, userVO.getfirstName());
		                stmt2.setString(3, userVO.getlname());
					    stmt2.setString(4, userVO.getemail());
		//              stmt2.setTimestamp(5, ts);
		//              stmt2.setTimestamp(6, ts);
						stmt2.executeUpdate();
			            System.out.println("AddDetailsUser ------ SUCCESSFUL");
		                                
					} else {
						System.out.println("AddUser ------ FAILED 1");
						throw new Exception();
					}
			} catch (Exception e) {
				System.out.println("AddUser ------ FAILED 2");
				e.printStackTrace();
			} 
				
//               
		}


                // End of RegisterUser
    public static Object[] getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("inside hash"+password);
        Object arr[] = new Object[2];
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        //adding random salt to password
        Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        arr[0] = salt;
        
        md.update(salt);
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        arr[1] = sb.toString();
        return arr;
    }               
     public boolean LoginUser(UserInfoVO userVO) 
     {    
         String userid=userVO.getuserName(); 
         String password=userVO.getpassWord();
         boolean flag=false;
         
         byte[] salt1 = new byte[32];	
		 try {
				createConnection();
				if(conn != null) 
				{
					stmt = conn.prepareStatement("select password,salt from password_table where login_id ="+"'"+userid+"'");
                    rs = stmt.executeQuery();
				    while (rs.next()) 
				    {
                                    
				    	String password1=rs.getString(1);
                        salt1=rs.getBytes(2);
                        flag= isMatch(salt1,password, password1);
				    }
				   
				} else {
					throw new Exception();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(stmt != null )stmt.close();
					if(rs !=null)rs.close();
					conn.close();
				} catch (SQLException se) {
					System.out.println("Cannot close Cursor");
				}
			}
			return flag;
         
         
         
         
         
         
         
         
//          	try {
//			createConnection();
//			if(conn != null) {
//				   stmt3 = conn.prepareStatement("select * from password_table where login_id = ?");
//                                   stmt3.setString(1, userid); 
//                                 
//				   rs=stmt3.executeQuery();
//                                   System.out.println("login  ------ SUCCESSFUL1111");
//                                //   System.out.println(rs.first());
//                                   if (rs.next())
//                                   {
//                                       //System.out.println(rs.getString(1));
//                                       System.out.println("User Exists!");
//                                   }
//				System.out.println("login  ------ SUCCESSFUL");                              
//                                
//			} else {
//				System.out.println("login ------ FAILED 1");
//				throw new Exception();
//			}
//                                //rs.close();
//				stmt3.close();
//				conn.close();
//		} catch (Exception e) {
//			System.out.println("login ------ FAILED 2");
//			e.printStackTrace();
//		}
//
//		System.out.println("login ------ END");
     }
    public static boolean isMatch(byte[] salt,String password, String DBpassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest m = MessageDigest.getInstance("SHA-256");
        m.update(salt);
        m.update(password.getBytes());
        System.out.println("password: "+password);
        System.out.println("DBpassword :"+DBpassword);
        byte s[] = m.digest();
        String result = "";
        for (int i = 0; i < s.length; i++) {
            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
        }
        
        if(result.equals(DBpassword)){
            return true;
        }
        return false;
    }
               
}
