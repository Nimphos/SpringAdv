/*
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.26 at 02:20:50 PM CEST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


*/
/**
 * <p>Java class for event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="event"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="basePrice" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element ref="{}auditorium"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 *//*

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "event", propOrder = {
    "id",
    "name",
    "rate",
    "basePrice",
    "dateTime",
    "auditorium"
})
public class Event {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String rate;
    protected double basePrice;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTime;
    @XmlElement(required = true)
    protected Auditorium auditorium;

    */
/**
     * Gets the value of the id property.
     * 
     *//*

    public long getId() {
        return id;
    }

    */
/**
     * Sets the value of the id property.
     * 
     *//*

    public void setId(long value) {
        this.id = value;
    }

    */
/**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     *//*

    public String getName() {
        return name;
    }

    */
/**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     *//*

    public void setName(String value) {
        this.name = value;
    }

    */
/**
     * Gets the value of the rate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     *//*

    public String getRate() {
        return rate;
    }

    */
/**
     * Sets the value of the rate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     *//*

    public void setRate(String value) {
        this.rate = value;
    }

    */
/**
     * Gets the value of the basePrice property.
     * 
     *//*

    public double getBasePrice() {
        return basePrice;
    }

    */
/**
     * Sets the value of the basePrice property.
     * 
     *//*

    public void setBasePrice(double value) {
        this.basePrice = value;
    }

    */
/**
     * Gets the value of the dateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     *//*

    public XMLGregorianCalendar getDateTime() {
        return dateTime;
    }

    */
/**
     * Sets the value of the dateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     *//*

    public void setDateTime(XMLGregorianCalendar value) {
        this.dateTime = value;
    }

    */
/**
     * Gets the value of the auditorium property.
     * 
     * @return
     *     possible object is
     *     {@link Auditorium }
     *     
     *//*

    public Auditorium getAuditorium() {
        return auditorium;
    }

    */
/**
     * Sets the value of the auditorium property.
     * 
     * @param value
     *     allowed object is
     *     {@link Auditorium }
     *     
     *//*

    public void setAuditorium(Auditorium value) {
        this.auditorium = value;
    }

}
*/