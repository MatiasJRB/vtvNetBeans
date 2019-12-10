/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author mjrca
 */
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author david
 */
public class Impresor
{

    private final static Logger LOGGER = Logger.getLogger("mx.hash.impresionpdf.Impresor");
    
    /***
     * Manda un documento a imprimir a la impresora que se indique en el dialogo
     * @throws PrinterException
     * @throws IOException 
     */
    public void imprimir(String dir) throws PrinterException, IOException 
    {
        // Indicamos el nombre del archivo Pdf que deseamos imprimir
        PDDocument document = PDDocument.load(new File(dir));
        PrinterJob job = PrinterJob.getPrinterJob();        
        LOGGER.log(Level.INFO, "Mostrando el dialogo de impresion");
        if (job.printDialog() == true) 
        {            
            job.setPageable(new PDFPageable(document));
            LOGGER.log(Level.INFO, "Imprimiendo documento");            
            //job.print();
            System.out.println("Imprimiendo, linea comentada");
        }
    }
}
