package com.example.springreact.model;


import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
public class EmployeePDFExporter {
	 Employee employee;

	public EmployeePDFExporter(Employee employee) {
		this.employee = employee;
	}
	
	
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		//Create layout and set background & borders
        Rectangle layout = new Rectangle(350,290);
        layout.setBackgroundColor(Color.getHSBColor(10, 2, 250)); //Background color
        layout.setBorderColor(Color.BLACK);  //Border color
        layout.setBorderWidth(6);      //Border width  
        layout.setBorder(Rectangle.BOX);  //Border on 4 sides
        
        Document document = new Document(layout);
        PdfWriter.getInstance(document, response.getOutputStream());
//        System.out.println("inside export method :  " + response);
        
        document.open();
     
          
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(13);
        font.setColor(Color.BLACK);
        
      
   
         
        Paragraph p = new Paragraph("Employee Details", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        
        try {
        	Image img = Image.getInstance(employee.getProfileImage());
            img.setAlignment(Image.ALIGN_CENTER);
            img.scaleAbsolute(80,80);
            document.add(img);	
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
        
  
        
        Paragraph firstName = new Paragraph("First Name : ");
        firstName.setFont(font);
        firstName.add(employee.getFirstName());
        firstName.setAlignment(Paragraph.ALIGN_CENTER);
        firstName.setSpacingAfter(1);
       
        
        Paragraph lastName = new Paragraph ("Last Name : " );
        lastName.setFont(font);
        lastName.add(employee.getLastName());
        lastName.setAlignment(Paragraph.ALIGN_CENTER);
        lastName.setIndentationRight(8);
        lastName.setSpacingAfter(1);;
        
        
        Paragraph email = new Paragraph ("Email : ");
        email.setFont(font);
        email.add(employee.getEmailId());
        email.setAlignment(Paragraph.ALIGN_CENTER);
        email.setSpacingAfter(1);
        
        Paragraph designation = new Paragraph("Designation : ");
        designation.setFont(font);
        designation.add( employee.getDesignation());
        designation.setAlignment(Paragraph.ALIGN_CENTER);
       
        
  
        document.add(firstName);
        document.add(lastName);
        document.add(email);
        document.add(designation);
        

         
        document.close();
         
    }
	
}
