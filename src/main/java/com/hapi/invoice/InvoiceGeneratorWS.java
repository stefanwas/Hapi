package com.hapi.invoice;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Path("/invoice")
public class InvoiceGeneratorWS {

    private InvoiceGenerator invoiceGenerator;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice generate0Pdf(Invoice source) {
        return source;
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



    public void setInvoiceGenerator(InvoiceGenerator invoiceGenerator) {
        this.invoiceGenerator = invoiceGenerator;
    }
}
