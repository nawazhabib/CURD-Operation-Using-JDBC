package com.project.task01.controller;

import com.project.task01.service.EmployeeService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/task01/reports")
public class ReportController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee-pdf")
    public void generateEmployeeReport(HttpServletResponse response) {
        try {
            // Generate JasperPrint
            JasperPrint jasperPrint = generateEmployeeJasperPrint();

            // Set the content type and header for the response
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=employee-report.pdf");

            // Export to PDF and write to the response output stream
            OutputStream outputStream = response.getOutputStream();
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            pdfExporter.exportReport();

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JasperPrint generateEmployeeJasperPrint() {
        try {
            // Load the JasperReport template
            InputStream reportTemplate = getClass().getResourceAsStream("/reports/employee_report.jrxml");

            // parameters to the report, for example, employee data
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("employeeData", employeeService.getAllEmployees());

            // Compile the JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            return jasperPrint;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
