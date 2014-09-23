package com.hapi.invoice;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Path("/invoice")
public class InvoiceGeneratorWS {

    private static final String ACCESS_CONTROL = "Access-Control-Allow-Origin";
    private static final String ALL_LOCATIONS = "*";

    private InvoiceGenerator invoiceGenerator;
    private InvoiceUnmarshaller invoiceUnmarshaller;

//    public InvoiceGeneratorWS() {
//        this.jacksonMapper = new ObjectMapper();
//        this.jacksonMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        this.jacksonMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
//        this.jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    }

    @GET
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    public String isAlive() {
        return "I'm fine, thanks.";
    }

    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice generate() {
        return this.invoiceGenerator.createFakeInvoice();
    }

    @POST
    @Path("/generate-pdf")
//    @Consumes(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Produces(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/pdf")
    public Response generate0Pdf(@FormParam("invoice") String invoiceContent) throws Exception {

        Invoice invoice = this.invoiceUnmarshaller.unmarshall(invoiceContent);

        byte[] pdf = this.invoiceGenerator.generateInvoiceAsPdf(invoice);

        Response.ResponseBuilder responseBuilder = Response.ok(pdf);

        responseBuilder.header("Content-disposition","attachment; filename=" + "faktura.pdf");

        Response response = responseBuilder.build();

        return response;
    }

    @GET
    @Path("/generate-pdf")
    @Produces("application/pdf")
    public Response generatePdf() throws Exception {

        Invoice invoice = this.invoiceGenerator.createFakeInvoice();
        byte[] pdf = this.invoiceGenerator.generateInvoiceAsPdf(invoice);

        Response.ResponseBuilder responseBuilder = Response.ok(pdf);

        responseBuilder.header("Content-disposition","attachment; filename=" + "faktura.pdf");

        Response response = responseBuilder.build();

        return response;
    }

//    private Invoice unmarshall(String reportRequestText) throws Exception {
//
//        InputStream stream = new ByteArrayInputStream(reportRequestText.getBytes("UTF-8"));
//        Invoice result = this.jacksonMapper.readValue(stream, Invoice.class);
//
//        return result;
//    }

    private void addAccessControlHeader(HttpServletResponse response) {
        response.addHeader(ACCESS_CONTROL, ALL_LOCATIONS);
        response.setCharacterEncoding("UTF-8");
    }
    @Required
    public void setInvoiceGenerator(InvoiceGenerator invoiceGenerator) {
        this.invoiceGenerator = invoiceGenerator;
    }

    @Required
    public void setInvoiceUnmarshaller(InvoiceUnmarshaller invoiceUnmarshaller) {
        this.invoiceUnmarshaller = invoiceUnmarshaller;
    }
}
