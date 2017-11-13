/*package util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Clientes;

public class Reporte {

    public void clientes(List<Clientes> lista,String ruta){
        try {
            FileOutputStream archivo =new FileOutputStream(ruta + "/doc.pdf");
            Document doc= new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            doc.add(new Paragraph("Reporte de clientes\n\n",FontFactory.getFont("arial",22, Font.ITALIC, BaseColor.RED)));
            PdfPTable tabla = new PdfPTable(8);
            String[] columnas = {"idclientes","nombres","apellidos","cedula","email","direccion","fecha_nacimiento","celular"};
            int i = 0;
            for(i = 0;i < columnas.length;i++) {
                PdfPCell celda = new PdfPCell(new Paragraph(columnas[i]));
                celda.setBackgroundColor(BaseColor.CYAN);
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(celda);
            }
            for (i = 0; i < lista.size();i++) {
                Clientes next = lista.get(i);
                tabla.addCell("" + next.getIdclientes());
                tabla.addCell(next.getNombres());
                tabla.addCell(next.getApellidos());
                tabla.addCell(next.getCedula());
                tabla.addCell(next.getEmail());
                tabla.addCell(next.getDireccion());
                tabla.addCell(Utilidad.convertir_fecha(next.getFecha_nacimiento()));
                tabla.addCell(next.getCelular());
            }
            doc.add(tabla);
            doc.close();
            archivo.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.ALL.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}*/
