
package com.x.ws.generated;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "BookMyFlightService", targetNamespace = "http://com.x.service/BookMyFlightService/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BookMyFlightService {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.x.ws.generated.ResponseFlight
     */
    @WebMethod(operationName = "BookFlight", action = "http://com.x.service/BookMyFlightService/BookFlight")
    @WebResult(name = "ResponseFlight", targetNamespace = "http://com.x.service/ResponseFlight", partName = "parameters")
    public ResponseFlight bookFlight(
        @WebParam(name = "RequestFlight", targetNamespace = "http://com.x.service/RequestFlight", partName = "parameters")
        RequestFlight parameters);

}