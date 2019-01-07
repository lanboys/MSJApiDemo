package com.bing.lan.msj;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://blog.csdn.net/weixin_41187876/article/details/79156969
 */
public class GeneratePDFUtil {

    // 利用模板生成pdf
    public static void interviewReportPDF(Map<String, String> map) {

        // 模板路径
        //String templatePath = UtilPath.getWEB_INF() + "pdf/面试报告模板.pdf";
        String templatePath = "D:\\workspace\\IDEA_workspace\\MSJApiDemo\\src\\main\\resources\\bbn_wrapper.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:\\workspace\\IDEA_workspace\\MSJApiDemo\\src\\main\\resources\\bbnaqqqq.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            // 给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
            //BaseFont bf = BaseFont.createFont(UtilPath.getRootPath() + "fonts/simsun.ttc,0", BaseFont.IDENTITY_H,
            //        BaseFont.EMBEDDED);
            //form.addSubstitutionFont(bf);
            BaseFont baseFont = BaseFont.createFont("D:\\workspace\\IDEA_workspace\\MSJApiDemo\\src\\main\\resources\\simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            form.addSubstitutionFont(baseFont);

            //遍历map装入数据
            for (Entry<String, String> entry : map.entrySet()) {
                form.setField(entry.getKey(), entry.getValue());
                System.out.println("插入PDF数据---->  key= " + entry.getKey() + " and value= " + entry.getValue());
            }

            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfReader reader1 = new PdfReader(bos.toByteArray());
            int numberOfPages = reader1.getNumberOfPages();
            for (int i = 2; i < numberOfPages + 1; i++) {
                PdfImportedPage importPage = copy.getImportedPage(reader1, i);
                copy.addPage(importPage);
            }
            doc.close();
            out.close();

            //Document document1 = new Document();
            //String destPath1 = "E:\\workspace\\IDEA_workspace\\MSJApiDemo\\src\\main\\resources\\dd.pdf";
            //
            //FileOutputStream outputStream = new FileOutputStream(destPath1);
            //PdfWriter.getInstance(document1, outputStream);
            //
            //document1.open();
            //document1.add(new Paragraph("Hello World"));
            //document1.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    //测试
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "蓝兵");
        map.put("idNo", "哈哈哈");

        System.out.println(map);
        interviewReportPDF(map);
    }
}
