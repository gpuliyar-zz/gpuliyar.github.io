//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.24 at 03:06:07 PM IST 
//

package com.metricstream.systemi.rga.rdc.pojo.rib;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.rdc.batch.rdc_rib package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RibRequest_QNAME = new QName("http://www.rdc.com/batch/rdc_rib.xsd", "RibRequest");
    private final static QName _RibResponse_QNAME = new QName("http://www.rdc.com/batch/rdc_rib.xsd", "RibResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.rdc.batch.rdc_rib
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RibResponse }
     * 
     */
    public RibResponse createRibResponse() {
        return new RibResponse();
    }

    /**
     * Create an instance of {@link RibRequest }
     * 
     */
    public RibRequest createRibRequest() {
        return new RibRequest();
    }

    /**
     * Create an instance of {@link AlertEntityAddresses }
     * 
     */
    public AlertEntityAddresses createAlertEntityAddresses() {
        return new AlertEntityAddresses();
    }

    /**
     * Create an instance of {@link RequestHeader }
     * 
     */
    public RequestHeader createRequestHeader() {
        return new RequestHeader();
    }

    /**
     * Create an instance of {@link ResponseHeader }
     * 
     */
    public ResponseHeader createResponseHeader() {
        return new ResponseHeader();
    }

    /**
     * Create an instance of {@link Inquiries }
     * 
     */
    public Inquiries createInquiries() {
        return new Inquiries();
    }

    /**
     * Create an instance of {@link AlertEntityAttribute }
     * 
     */
    public AlertEntityAttribute createAlertEntityAttribute() {
        return new AlertEntityAttribute();
    }

    /**
     * Create an instance of {@link AlertEntityAttributes }
     * 
     */
    public AlertEntityAttributes createAlertEntityAttributes() {
        return new AlertEntityAttributes();
    }

    /**
     * Create an instance of {@link AlertEntity }
     * 
     */
    public AlertEntity createAlertEntity() {
        return new AlertEntity();
    }

    /**
     * Create an instance of {@link AlertEntityAddress }
     * 
     */
    public AlertEntityAddress createAlertEntityAddress() {
        return new AlertEntityAddress();
    }

    /**
     * Create an instance of {@link AlertEntityEvents }
     * 
     */
    public AlertEntityEvents createAlertEntityEvents() {
        return new AlertEntityEvents();
    }

    /**
     * Create an instance of {@link Inquiry }
     * 
     */
    public Inquiry createInquiry() {
        return new Inquiry();
    }

    /**
     * Create an instance of {@link AlertEntityEvent }
     * 
     */
    public AlertEntityEvent createAlertEntityEvent() {
        return new AlertEntityEvent();
    }

    /**
     * Create an instance of {@link AlertEntityAliases }
     * 
     */
    public AlertEntityAliases createAlertEntityAliases() {
        return new AlertEntityAliases();
    }

    /**
     * Create an instance of {@link InquiryDisposition }
     * 
     */
    public InquiryDisposition createInquiryDisposition() {
        return new InquiryDisposition();
    }

    /**
     * Create an instance of {@link AlertEntityDob }
     * 
     */
    public AlertEntityDob createAlertEntityDob() {
        return new AlertEntityDob();
    }

    /**
     * Create an instance of {@link AlertEntityAlias }
     * 
     */
    public AlertEntityAlias createAlertEntityAlias() {
        return new AlertEntityAlias();
    }

    /**
     * Create an instance of {@link Alert }
     * 
     */
    public Alert createAlert() {
        return new Alert();
    }

    /**
     * Create an instance of {@link AlertEntityDobs }
     * 
     */
    public AlertEntityDobs createAlertEntityDobs() {
        return new AlertEntityDobs();
    }

    /**
     * Create an instance of {@link AlertEntityReferenceSource }
     * 
     */
    public AlertEntityReferenceSource createAlertEntityReferenceSource() {
        return new AlertEntityReferenceSource();
    }

    /**
     * Create an instance of {@link AlertEntities }
     * 
     */
    public AlertEntities createAlertEntities() {
        return new AlertEntities();
    }

    /**
     * Create an instance of {@link InquiryDispositions }
     * 
     */
    public InquiryDispositions createInquiryDispositions() {
        return new InquiryDispositions();
    }

    /**
     * Create an instance of {@link AlertEntitySource }
     * 
     */
    public AlertEntitySource createAlertEntitySource() {
        return new AlertEntitySource();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RibRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.rdc.com/batch/rdc_rib.xsd", name = "RibRequest")
    public JAXBElement<RibRequest> createRibRequest(RibRequest value) {
        return new JAXBElement<RibRequest>(_RibRequest_QNAME, RibRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RibResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.rdc.com/batch/rdc_rib.xsd", name = "RibResponse")
    public JAXBElement<RibResponse> createRibResponse(RibResponse value) {
        return new JAXBElement<RibResponse>(_RibResponse_QNAME, RibResponse.class, null, value);
    }

}
