package com.priyasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JdbcExecute {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		BufferedReader br=null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
			st=con.createStatement();
			System.out.println("SQL QUERY:");
			br=new BufferedReader(new InputStreamReader(System.in));
			String query=br.readLine();
			boolean b=st.execute(query);
			if(b==true)
			{
				rs=st.getResultSet();
				ResultSetMetaData md=rs.getMetaData();
				int count=md.getColumnCount();
				for(int i=1;i<=count;i++)
				{
					System.out.println(md.getColumnName(i)+"\t");
				}
				System.out.println();
				while(rs.next())
				{
					for(int i=1;i<=count;i++)
					{
					System.out.print(rs.getString(i)+"\t");
				    }
					System.out.println();
				}
				
			}else{
				int rowCount=st.getUpdateCount();
				System.out.println("RowCount:"+rowCount);
			}
			}catch(Exception e)
		    {
			e.printStackTrace();
		   }finally{
			   try{
				  br.close();
				  con.close();
			   }catch(Exception e)
			   {
				   e.printStackTrace();
			   }
			   
			
		}
		

	}

}
