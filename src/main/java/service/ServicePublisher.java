package service;
import jakarta.xml.ws.Endpoint;

public class ServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/EmployerService", new EmployerServiceImp());
        System.out.println("This SOA Api is published at http://localhost:8080/EmployerService");
    }
}
