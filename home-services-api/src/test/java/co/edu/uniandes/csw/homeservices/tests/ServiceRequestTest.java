package co.edu.uniandes.csw.homeservices.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.dtos.PriceListItemDTO;
import co.edu.uniandes.csw.homeservices.dtos.ServiceRequestDTO;
import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import co.edu.uniandes.csw.homeservices.services.ServiceRequestService;
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
public class ServiceRequestTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int NotFound = Status.NOT_FOUND.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String serviceRequestPath = "serviceRequests";
    private final static List<ServiceRequestDTO> oraculo = new ArrayList<>();
    private final String expectedskillsPath = "expectedskills";
    private final String priceListPath = "priceList";
    private final static List<SkillDTO> oraculoExpectedskills = new ArrayList<>();
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
                .addPackage(ServiceRequestService.class.getPackage())
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
            ServiceRequestDTO serviceRequest = factory.manufacturePojo(ServiceRequestDTO.class);
            serviceRequest.setId(i + 1L);

            oraculo.add(serviceRequest);

            SkillDTO expectedskills = factory.manufacturePojo(SkillDTO.class);
            expectedskills.setId(i + 1L);
            oraculoExpectedskills.add(expectedskills);
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
    public void createServiceRequestTest() throws IOException {
        ServiceRequestDTO serviceRequest = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);

        CustomerDTO customer = new PodamFactoryImpl().manufacturePojo(CustomerDTO.class);
        target.path("customers").request().cookie(cookieSessionId)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));

        Response response = target.path(serviceRequestPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(serviceRequest, MediaType.APPLICATION_JSON));
        ServiceRequestDTO servicerequestTest = (ServiceRequestDTO) response.readEntity(ServiceRequestDTO.class);
        Assert.assertEquals(serviceRequest.getId(), servicerequestTest.getId());
        Assert.assertEquals(serviceRequest.getName(), servicerequestTest.getName());
        Assert.assertEquals(serviceRequest.getPrice(), servicerequestTest.getPrice());
        Assert.assertEquals(serviceRequest.getRecommendedTime(), servicerequestTest.getRecommendedTime());
        Assert.assertEquals(serviceRequest.getCreationDate(), servicerequestTest.getCreationDate());
        Assert.assertEquals(serviceRequest.getDueDate(), servicerequestTest.getDueDate());
        Assert.assertEquals(serviceRequest.getDescription(), servicerequestTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getServiceRequestById() {
        Cookie cookieSessionId = login(username, password);
        ServiceRequestDTO servicerequestTest = target.path(serviceRequestPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(ServiceRequestDTO.class);
        Assert.assertEquals(servicerequestTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(servicerequestTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(servicerequestTest.getPrice(), oraculo.get(0).getPrice());
        Assert.assertEquals(servicerequestTest.getRecommendedTime(), oraculo.get(0).getRecommendedTime());
        Assert.assertEquals(servicerequestTest.getCreationDate(), oraculo.get(0).getCreationDate());
        Assert.assertEquals(servicerequestTest.getDueDate(), oraculo.get(0).getDueDate());
        Assert.assertEquals(servicerequestTest.getDescription() , oraculo.get(0).getDescription() );
    }

    @Test
    @InSequence(3)
    public void listServiceRequestTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(serviceRequestPath)
                .request().cookie(cookieSessionId).get();
        String listServiceRequest = response.readEntity(String.class);
        List<ServiceRequestDTO> listServiceRequestTest = new ObjectMapper().readValue(listServiceRequest, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listServiceRequestTest.size());
    }

    @Test
    @InSequence(4)
    public void updateServiceRequestTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ServiceRequestDTO serviceRequest = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ServiceRequestDTO serviceRequestChanged = factory.manufacturePojo(ServiceRequestDTO.class);
        serviceRequest.setName(serviceRequestChanged.getName());
        serviceRequest.setPrice(serviceRequestChanged.getPrice());
        serviceRequest.setRecommendedTime(serviceRequestChanged.getRecommendedTime());
        serviceRequest.setCreationDate(serviceRequestChanged.getCreationDate());
        serviceRequest.setDueDate(serviceRequestChanged.getDueDate());
        serviceRequest.setDescription(serviceRequestChanged.getDescription());
        Response response = target.path(serviceRequestPath).path(serviceRequest.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(serviceRequest, MediaType.APPLICATION_JSON));
        ServiceRequestDTO servicerequestTest = (ServiceRequestDTO) response.readEntity(ServiceRequestDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(serviceRequest.getName(), servicerequestTest.getName());
        Assert.assertEquals(serviceRequest.getPrice(), servicerequestTest.getPrice());
        Assert.assertEquals(serviceRequest.getRecommendedTime(), servicerequestTest.getRecommendedTime());
        Assert.assertEquals(serviceRequest.getCreationDate(), servicerequestTest.getCreationDate());
        Assert.assertEquals(serviceRequest.getDueDate(), servicerequestTest.getDueDate());
        Assert.assertEquals(serviceRequest.getDescription(), servicerequestTest.getDescription());
    }

    @Test
    @InSequence(9)
    public void deleteServiceRequestTest() {
        Cookie cookieSessionId = login(username, password);
        ServiceRequestDTO serviceRequest = oraculo.get(0);
        Response response = target.path(serviceRequestPath).path(serviceRequest.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void addExpectedskillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO expectedskills = oraculoExpectedskills.get(0);
        ServiceRequestDTO serviceRequest = oraculo.get(0);

        Response response = target.path("skills")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(expectedskills, MediaType.APPLICATION_JSON));

        SkillDTO expectedskillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(expectedskills.getId(), expectedskillsTest.getId());
        Assert.assertEquals(expectedskills.getName(), expectedskillsTest.getName());
        Assert.assertEquals(expectedskills.getDescription(), expectedskillsTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(serviceRequestPath).path(serviceRequest.getId().toString())
                .path(expectedskillsPath).path(expectedskills.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(expectedskills, MediaType.APPLICATION_JSON));

        expectedskillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(expectedskills.getId(), expectedskillsTest.getId());
    }

    @Test
    @InSequence(6)
    public void listExpectedskillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ServiceRequestDTO serviceRequest = oraculo.get(0);

        Response response = target.path(serviceRequestPath)
                .path(serviceRequest.getId().toString())
                .path(expectedskillsPath)
                .request().cookie(cookieSessionId).get();

        String expectedskillsList = response.readEntity(String.class);
        List<SkillDTO> expectedskillsListTest = new ObjectMapper().readValue(expectedskillsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, expectedskillsListTest.size());
    }
    
    @Test
    @InSequence(6)
    public void getPriceListTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ServiceRequestDTO serviceRequest = oraculo.get(0);

        Response response = target.path(serviceRequestPath)
                .path(serviceRequest.getId().toString())
                .path(priceListPath)
                .request().cookie(cookieSessionId).get();
        String resultString = response.readEntity(String.class);
        Assert.assertEquals(NotFound, response.getStatus());
    }

    @Test
    @InSequence(7)
    public void getExpectedskillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        SkillDTO expectedskills = oraculoExpectedskills.get(0);
        ServiceRequestDTO serviceRequest = oraculo.get(0);

        SkillDTO expectedskillsTest = target.path(serviceRequestPath)
                .path(serviceRequest.getId().toString()).path(expectedskillsPath)
                .path(expectedskills.getId().toString())
                .request().cookie(cookieSessionId).get(SkillDTO.class);

        Assert.assertEquals(expectedskills.getId(), expectedskillsTest.getId());
        Assert.assertEquals(expectedskills.getName(), expectedskillsTest.getName());
        Assert.assertEquals(expectedskills.getDescription(), expectedskillsTest.getDescription());
    }

    @Test
    @InSequence(8)
    public void removeExpectedskillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO expectedskills = oraculoExpectedskills.get(0);
        ServiceRequestDTO serviceRequest = oraculo.get(0);

        Response response = target.path(serviceRequestPath).path(serviceRequest.getId().toString())
                .path(expectedskillsPath).path(expectedskills.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
