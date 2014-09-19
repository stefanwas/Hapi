package com.hapi.invoice;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/invoice")
public class InvoiceGeneratorWS {

    private InvoiceProvider invoiceProvider;

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
        return createFakeInvoice();
    }

    @POST
    @Path("/generate-pdf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice generatePdf(Invoice source) {
        return source;
    }
    private Invoice createFakeInvoice() {
        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber("FV/2014/09");
        invoice.setIssueDate("2014.09.15");
        invoice.setSellDate("2014.09.10");
        invoice.setPaymentForm("przelew");
        invoice.setPaymentPeriod("14 dni");
        invoice.setIssuer("Wojciech Krzysztofik");

        for (int i = 0; i < 3; i++) {
            invoice.getItems().add(createFakeItem());
        }

        return invoice;
    }

    private Item createFakeItem() {
        Item item = new Item();

        item.setName("Ziemniaki");
        item.setAmount(100);
        item.setPrice(4.5f);
        item.setValue(450);
        item.setVatPercent(23);
        item.setVatValue(135);
        item.setTotalValue(650.5f);

        return item;
    }

    public void setInvoiceProvider(InvoiceProvider invoiceProvider) {
        this.invoiceProvider = invoiceProvider;
    }
}
