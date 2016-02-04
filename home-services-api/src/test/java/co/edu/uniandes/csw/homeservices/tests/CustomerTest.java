package co.edu.uniandes.csw.homeservices.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.dtos.ServiceRequestDTO;
import co.edu.uniandes.csw.homeservices.services.CustomerService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class CustomerTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String customerPath = "customers";
    private final static List<CustomerDTO> oraculo = new ArrayList<>();
    private final String serviceRequestsPath = "serviceRequests";
    private final static List<ServiceRequestDTO> oraculoServiceRequests = new ArrayList<>();
    private WebTarget target;
    private final String apiPath = "api";
    private final String username = System.getenv("USERNAME_USER");
    private final String password = System.getenv("PASSWORD_USER");

    @ArquillianResource
    private URL deploymentURL;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw.homeservices:home-services-logic:0.1.0")
                        .withTransitivity().asFile())
                .addAsLibraries(Maven.resolver()
                        .resolve("co.edu.uniandes.csw:auth-utils:0.1.0")
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(CustomerService.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        return ClientBuilder.newClient(config).target(deploymentURL.toString()).path(apiPath);
    }

    @BeforeClass
    public static void setUp() {
        insertData();
    }

    public static void insertData() {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            CustomerDTO customer = factory.manufacturePojo(CustomerDTO.class);
            customer.setId(i + 1L);

            oraculo.add(customer);

            ServiceRequestDTO serviceRequests = factory.manufacturePojo(ServiceRequestDTO.class);
            serviceRequests.setId(i + 1L);
            oraculoServiceRequests.add(serviceRequests);
        }
    }

    public Cookie login(String username, String password) {
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        user.setRememberMe(true);
        Response response = target.path("users").path("login").request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Ok) {
            return response.getCookies().get(JWT.cookieName);
        } else {
            return null;
        }
    }

    @Before
    public void setUpTest() {
        target = createWebTarget();
    }

    @Test
    @InSequence(1)
    public void createCustomerTest() throws IOException {
        CustomerDTO customer = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(customerPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));
        CustomerDTO  customerTest = (CustomerDTO) response.readEntity(CustomerDTO.class);
        Assert.assertEquals(customer.getId(), customerTest.getId());
        Assert.assertEquals(customer.getName(), customerTest.getName());
        Assert.assertEquals(customer.getLastName(), customerTest.getLastName());
        Assert.assertEquals(customer.getDocument(), customerTest.getDocument());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getCustomerById() {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customerTest = target.path(customerPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(CustomerDTO.class);
        Assert.assertEquals(customerTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(customerTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(customerTest.getLastName(), oraculo.get(0).getLastName());
        Assert.assertEquals(customerTest.getDocument(), oraculo.get(0).getDocument());
    }

    @Test
    @InSequence(3)
    public void listCustomerTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(customerPath)
                .request().cookie(cookieSessionId).get();
        String listCustomer = response.readEntity(String.class);
        List<CustomerDTO> listCustomerTest = new ObjectMapper().readValue(listCustomer, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listCustomerTest.size());
    }

    @Test
    @InSequence(4)
    public void updateCustomerTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customer = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CustomerDTO customerChanged = factory.manufacturePojo(CustomerDTO.class);
        customer.setName(customerChanged.getName());
        customer.setLastName(customerChanged.getLastName());
        customer.setDocument(customerChanged.getDocument());
        Response response = target.path(customerPath).path(customer.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(customer, MediaType.APPLICATION_JSON));
        CustomerDTO customerTest = (CustomerDTO) response.readEntity(CustomerDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(customer.getName(), customerTest.getName());
        Assert.assertEquals(customer.getLastName(), customerTest.getLastName());
        Assert.assertEquals(customer.getDocument(), customerTest.getDocument());
    }

    @Test
    @InSequence(9)
    public void deleteCustomerTest() {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customer = oraculo.get(0);
        Response response = target.path(customerPath).path(customer.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void addServiceRequestsTest() {
        Cookie cookieSessionId = login(username, password);

        ServiceRequestDTO serviceRequests = oraculoServiceRequests.get(0);
        CustomerDTO customer = oraculo.get(0);


        Response response = target.path("serviceRequests")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(serviceRequests, MediaType.APPLICATION_JSON));

        ServiceRequestDTO servicerequestsTest = (ServiceRequestDTO) response.readEntity(ServiceRequestDTO.class);
        Assert.assertEquals(serviceRequests.getId(), servicerequestsTest.getId());
        Assert.assertEquals(serviceRequests.getName(), servicerequestsTest.getName());
        Assert.assertEquals(serviceRequests.getPrice(), servicerequestsTest.getPrice());
        Assert.assertEquals(serviceRequests.getRecommendedTime(), servicerequestsTest.getRecommendedTime());
        Assert.assertEquals(serviceRequests.getCreationDate(), servicerequestsTest.getCreationDate());
        Assert.assertEquals(serviceRequests.getDueDate(), servicerequestsTest.getDueDate());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(customerPath).path(customer.getId().toString())
                .path(serviceRequestsPath).path(serviceRequests.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(serviceRequests, MediaType.APPLICATION_JSON));

        servicerequestsTest = (ServiceRequestDTO) response.readEntity(ServiceRequestDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(serviceRequests.getId(), servicerequestsTest.getId());
    }

    @Test
    @InSequence(6)
    public void listServiceRequestsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        CustomerDTO customer = oraculo.get(0);

        Response response = target.path(customerPath)
                .path(customer.getId().toString())
                .path(serviceRequestsPath)
                .request().cookie(cookieSessionId).get();

        String serviceRequestsList = response.readEntity(String.class);
        List<ServiceRequestDTO> serviceRequestsListTest = new ObjectMapper().readValue(serviceRequestsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, serviceRequestsListTest.size());
    }

    @Test
    @InSequence(7)
    public void getServiceRequestsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ServiceRequestDTO serviceRequests = oraculoServiceRequests.get(0);
        CustomerDTO customer = oraculo.get(0);

        ServiceRequestDTO servicerequestsTest = target.path(customerPath)
                .path(customer.getId().toString()).path(serviceRequestsPath)
                .path(serviceRequests.getId().toString())
                .request().cookie(cookieSessionId).get(ServiceRequestDTO.class);

        Assert.assertEquals(serviceRequests.getId(), servicerequestsTest.getId());
        Assert.assertEquals(serviceRequests.getName(), servicerequestsTest.getName());
        Assert.assertEquals(serviceRequests.getPrice(), servicerequestsTest.getPrice());
        Assert.assertEquals(serviceRequests.getRecommendedTime(), servicerequestsTest.getRecommendedTime());
        Assert.assertEquals(serviceRequests.getCreationDate(), servicerequestsTest.getCreationDate());
        Assert.assertEquals(serviceRequests.getDueDate(), servicerequestsTest.getDueDate());
    }

    @Test
    @InSequence(8)
    public void removeServiceRequestsTest() {
        Cookie cookieSessionId = login(username, password);

        ServiceRequestDTO serviceRequests = oraculoServiceRequests.get(0);
        CustomerDTO customer = oraculo.get(0);

        Response response = target.path(customerPath).path(customer.getId().toString())
                .path(serviceRequestsPath).path(serviceRequests.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
