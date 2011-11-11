/**
 * DightStorageService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package se.sics.dight.storage.webservice;

public interface DightStorageService extends javax.xml.rpc.Service {
    public java.lang.String getDightStorageServiceEndpointPortAddress();

    public se.sics.dight.storage.webservice.DightStorageServiceEndpoint getDightStorageServiceEndpointPort() throws javax.xml.rpc.ServiceException;

    public se.sics.dight.storage.webservice.DightStorageServiceEndpoint getDightStorageServiceEndpointPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
