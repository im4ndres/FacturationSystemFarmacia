package com.programacion.control;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.programacion.database.Conexion;
import com.programacion.forms.FrmFacturar;

public class VentaPDF 
{
	private String nombreCliente;
	private String cedulaCliente;
	private String celularCliente;
	
	private String fechaActual = "";
	private String archivoPDF = "";
	
	//obtener datos de los clientes
	public void datosCliente(int id_cliente)
	{
		Connection c = Conexion.conectar();
		String sql = "SELECT * FROM CLIENTES WHERE id_cliente = '" + id_cliente + "'";
		Statement st;
		
		try {
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido");
				cedulaCliente = rs.getString("cedula");
				celularCliente = rs.getString("celular");
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener datos del cliente: " + e);
		}
	}
	
	public void generarFactura() {
	    try {
	        // fecha actual
	        Date date = new Date();
	        fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
	        
	        // cambiar formato de fecha
	        String fechaNueva = fechaActual.replace("/", "_");
	        
	        // nombre del archivo
	        archivoPDF = "Factura_" + nombreCliente + "_" + fechaNueva + ".pdf";
	        
	        FileOutputStream archivo;
	        File file = new File("src/pdf/" + archivoPDF);
	        archivo = new FileOutputStream(file);
	        
	        Document doc = new Document();
	        PdfWriter.getInstance(doc, archivo);
	        doc.open();
	        
	        Image img = Image.getInstance("src/resources/facturapdf.png");
	        Paragraph fecha = new Paragraph();
	        Font negrita = new Font(Font.FontFamily.COURIER, 11, Font.BOLD, BaseColor.RED);
	        fecha.add(Chunk.NEWLINE);
	        fecha.add("Factura: 001" + "\nFecha: " + fechaActual + "\n\n");
	        
	        PdfPTable encabezado = new PdfPTable(4);
	        encabezado.setWidthPercentage(100);
	        encabezado.getDefaultCell().setBorder(0); // quitar el borde de la tabla
	        
	        // tamaño de las celdas
	        float[] columnaEncabezado = new float[]{20f, 30f, 70f, 40f};
	        encabezado.setWidths(columnaEncabezado);
	        encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
	        
	        // agregar celdas
	        encabezado.addCell(img);
	        
	        String ci = "5346564654";
	        String nombre = "Farmacorp";
	        String celular = "77251387";
	        String razon = "MEDBOL"; 
	        
	        encabezado.addCell("");
	        encabezado.addCell("CI: " + ci + "\nNombre: " + nombre + "\nCelular: " + celular + "\nRazón Social: " + razon);
	        encabezado.addCell(fecha);
	        
	        doc.add(encabezado);
	        
	        // cuerpo
	        Paragraph cliente = new Paragraph();
	        cliente.add(Chunk.NEWLINE);
	        cliente.add("Datos del cliente: " + "\n\n");
	        doc.add(cliente);
	        
	        // datos del cliente
	        PdfPTable tablaCliente = new PdfPTable(2);
	        tablaCliente.setWidthPercentage(100);
	        tablaCliente.getDefaultCell().setBorder(0);
	        
	        // tamaño de celdas
	        float[] columnaCliente = new float[]{25f, 75f};
	        tablaCliente.setWidths(columnaCliente);
	        tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
	        
	        PdfPCell cliente1 = new PdfPCell(new Phrase("CI: ", negrita));
	        PdfPCell cliente2 = new PdfPCell(new Phrase(cedulaCliente));
	        PdfPCell cliente3 = new PdfPCell(new Phrase("Nombre: ", negrita));
	        PdfPCell cliente4 = new PdfPCell(new Phrase(nombreCliente));
	        PdfPCell cliente5 = new PdfPCell(new Phrase("Celular: ", negrita));
	        PdfPCell cliente6 = new PdfPCell(new Phrase(celularCliente));
	        
	        // quitar bordes
	        cliente1.setBorder(0);
	        cliente2.setBorder(0);
	        cliente3.setBorder(0);
	        cliente4.setBorder(0);
	        cliente5.setBorder(0);
	        cliente6.setBorder(0);
	        
	        // agregar celdas a la tabla
	        tablaCliente.addCell(cliente1);
	        tablaCliente.addCell(cliente2);
	        tablaCliente.addCell(cliente3);
	        tablaCliente.addCell(cliente4);
	        tablaCliente.addCell(cliente5);
	        tablaCliente.addCell(cliente6);
	        
	        doc.add(tablaCliente);
	        
	        // productos
	        PdfPTable tablaProducto = new PdfPTable(4);
	        tablaProducto.setWidthPercentage(100);
	        tablaProducto.getDefaultCell().setBorder(0);
	        
	        float[] columnaProducto = new float[]{15f, 50f, 15f, 20f};
	        tablaProducto.setWidths(columnaProducto);
	        tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
	        
	        PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", negrita));
	        PdfPCell producto2 = new PdfPCell(new Phrase("Descripción: ", negrita));
	        PdfPCell producto3 = new PdfPCell(new Phrase("Precio unitario: ", negrita));
	        PdfPCell producto4 = new PdfPCell(new Phrase("Precio total: ", negrita));
	        
	        producto1.setBorder(0);
	        producto2.setBorder(0);
	        producto3.setBorder(0);
	        producto4.setBorder(0);
	        
	        producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        
	        tablaProducto.addCell(producto1);
	        tablaProducto.addCell(producto2);
	        tablaProducto.addCell(producto3);
	        tablaProducto.addCell(producto4);
	        
	        for (int i = 0; i < FrmFacturar.model.getRowCount(); i++) {
	            String producto = FrmFacturar.model.getValueAt(i, 1).toString();
	            String cantidad = FrmFacturar.model.getValueAt(i, 2).toString();
	            String precio = FrmFacturar.model.getValueAt(i, 3).toString();
	            String total = FrmFacturar.model.getValueAt(i, 5).toString();
	            
	            tablaProducto.addCell(cantidad);
	            tablaProducto.addCell(producto);
	            tablaProducto.addCell(precio);
	            tablaProducto.addCell(total);
	        }
	        
	        doc.add(tablaProducto);
	        
	        // total a pagar
	        Paragraph info = new Paragraph();
	        info.add(Chunk.NEWLINE);
	        info.add("Total a pagar: " + FrmFacturar.txtTotal.getText());
	        info.setAlignment(Element.ALIGN_RIGHT);
	        doc.add(info);
	        
	        // cerrar el doc y el archivo
	        doc.close();
	        archivo.close();
	        
	        // abrir el documento al ser generado
	        Desktop.getDesktop().open(file);
	        
	    } catch (DocumentException | IOException e) {
	        System.out.println("Error en: " + e);
	    }
	}

}



