/**
 * DightStorageServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package se.sics.dight.storage.webservice;

public class DightStorageServiceLocator extends org.apache.axis.client.Service implements se.sics.dight.storage.webservice.DightStorageService {

    public DightStorageServiceLocator() {
    }


    public DightStorageServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DightStorageServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DightStorageServiceEndpointPort
    private java.lang.String DightStorageServiceEndpointPort_address = "http://lucan.sics.se:8080/dight-storage-webservice-0.0.1-SNAPSHOT/dightStorageService";

    public java.lang.String getDightStorageServiceEndpointPortAddress() {
        return DightStorageServiceEndpointPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DightStorageServiceEndpointPortWSDDServiceName = "DightStorageServiceEndpointPort";

    public java.lang.String getDightStorageServiceEndpointPortWSDDServiceName() {
        return DightStorageServiceEndpointPortWSDDServiceName;
    }

    public void setDightStorageServiceEndpointPortWSDDServiceName(java.lang.String name) {
        DightStorageServiceEndpointPortWSDDServiceName = name;
    }

    public se.sics.dight.storage.webservice.DightStorageServiceEndpoint getDightStorageServiceEndpointPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DightStorageServiceEndpointPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDightStorageServiceEndpointPort(endpoint);
    }

    public se.sics.dight.storage.webservice.DightStorageServiceEndpoint getDightStorageServiceEndpointPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            se.sics.dight.storage.webservice.DightStorageServiceEndpointPortBindingStub _stub = new se.sics.dight.storage.webservice.DightStorageServiceEndpointPortBindingStub(portAddress, this);
            _stub.setPortName(getDightStorageServiceEndpointPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDightStorageServiceEndpointPortEndpointAddress(java.lang.String address) {
        DightStorageServiceEndpointPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (se.sics.dight.storage.webservice.DightStorageServiceEndpoint.class.isAssignableFrom(serviceEndpointInterface)) {
                se.sics.dight.storage.webservice.DightStorageServiceEndpointPortBindingStub _stub = new se.sics.dight.storage.webservice.DightStorageServiceEndpointPortBindingStub(new java.net.URL(DightStorageServiceEndpointPort_address), this);
                _stub.setPortName(getDightStorageServiceEndpointPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DightStorageServiceEndpointPort".equals(inputPortName)) {
            return getDightStorageServiceEndpointPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.storage.dight.sics.se/", "dightStorageService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.storage.dight.sics.se/", "DightStorageServiceEndpointPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DightStorageServiceEndpointPort".equals(portName)) {
            setDightStorageServiceEndpointPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
