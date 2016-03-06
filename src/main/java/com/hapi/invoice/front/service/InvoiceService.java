package com.hapi.invoice.front.service;


import com.hapi.invoice.front.model.Invoice;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/invoice")
public class InvoiceService {

    private static final String ACCESS_CONTROL = "Access-Control-Allow-Origin";
    private static final String ALL_LOCATIONS = "*";

    private InvoiceGenerator invoiceGenerator;
    private InvoiceUnmarshaller invoiceUnmarshaller;

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
