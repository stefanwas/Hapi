package com.hapi.invoice;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

//    @POST
//    @Path("/generate-pdf")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Invoice generatePdf(Invoice source) {
//        return source;
//    }

    @GET
    @Path("/generate-pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/pdf")
    public Invoice generatePdf(Invoice source) throws Exception {

        Invoice invoice = this.invoiceGenerator.createFakeInvoice();
        OutputStream pdf = this.invoiceGenerator.generateInvoiceAsPdf(invoice);

        return source;
    }



    public void setInvoiceGenerator(InvoiceGenerator invoiceGenerator) {
        this.invoiceGenerator = invoiceGenerator;
    }
}
