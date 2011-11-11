package se.sics.dight.storage.webservice;

public class DightStorageServiceEndpointProxy implements se.sics.dight.storage.webservice.DightStorageServiceEndpoint {
  private String _endpoint = null;
  private se.sics.dight.storage.webservice.DightStorageServiceEndpoint dightStorageServiceEndpoint = null;
  
  public DightStorageServiceEndpointProxy() {
    _initDightStorageServiceEndpointProxy();
  }
  
  public DightStorageServiceEndpointProxy(String endpoint) {
    _endpoint = endpoint;
    _initDightStorageServiceEndpointProxy();
  }
  
  private void _initDightStorageServiceEndpointProxy() {
    try {
      dightStorageServiceEndpoint = (new se.sics.dight.storage.webservice.DightStorageServiceLocator()).getDightStorageServiceEndpointPort();
      if (dightStorageServiceEndpoint != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dightStorageServiceEndpoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dightStorageServiceEndpoint)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dightStorageServiceEndpoint != null)
      ((javax.xml.rpc.Stub)dightStorageServiceEndpoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public se.sics.dight.storage.webservice.DightStorageServiceEndpoint getDightStorageServiceEndpoint() {
    if (dightStorageServiceEndpoint == null)
      _initDightStorageServiceEndpointProxy();
    return dightStorageServiceEndpoint;
  }
  
  public byte[] performOperation(byte[] arg0, byte[] arg1) throws java.rmi.RemoteException{
    if (dightStorageServiceEndpoint == null)
      _initDightStorageServiceEndpointProxy();
    return dightStorageServiceEndpoint.performOperation(arg0, arg1);
  }
  
  
}