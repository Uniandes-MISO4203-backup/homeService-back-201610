package co.edu.uniandes.csw.homeservices.tests;

import co.edu.uniandes.csw.homeservices.tests.*;
import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.homeservices.dtos.ChatDTO;
import co.edu.uniandes.csw.homeservices.dtos.CustomerDTO;
import co.edu.uniandes.csw.homeservices.dtos.ContractorDTO;
import co.edu.uniandes.csw.homeservices.services.ChatNameService;
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
public class ChatNameTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String chatNamePath = "chat";
    private final static List<ChatDTO> oraculo = new ArrayList<>();
    private final static List<CustomerDTO> oraculoCustomers = new ArrayList<>();
    private final static List<ContractorDTO> oraculoContractors = new ArrayList<>();
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
                .addPackage(ChatNameService.class.getPackage())
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
            
            ChatDTO chatName = factory.manufacturePojo(ChatDTO.class);
            chatName.setId(i + 1L);

            oraculo.add(chatName);
            
            CustomerDTO customers = factory.manufacturePojo(CustomerDTO.class);
            customers.setId(i + 1L);
            oraculoCustomers.add(customers);
            System.out.println ("customer.getid" + oraculoCustomers.get(i).getId());
            
            ContractorDTO contractors = factory.manufacturePojo(ContractorDTO.class);
            contractors.setId(i + 1L);
            oraculoContractors.add(contractors);
            System.out.println ("contractor.getid" + oraculoContractors.get(i).getId());
            System.out.println ("contractor.getid" + oraculoContractors.get(i).getName());
            System.out.println ("contractor.getid" + oraculoContractors.get(i).getLastName());
            System.out.println ("contractor.getid" + oraculoContractors.get(i).getDocument());
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
    public void createChatNameTest() throws IOException {
        
        ContractorDTO contractor = oraculoContractors.get(0);
        CustomerDTO customer = oraculoCustomers.get(0);
        ChatDTO chatName = oraculo.get(0);
        
        System.out.println ("contractor.getid" + contractor.getId());
        System.out.println ("customer.getid" + customer.getId());
        
        chatName.setIdContractor(contractor.getId());
        chatName.setIdCustomer(customer.getId());
        
        Cookie cookieSessionId = login(username, password);
                     
        Response response = target.path("customers")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(customer, MediaType.APPLICATION_JSON));
            
                Assert.assertEquals(Created, response.getStatus());
    
                response = target.path("contractors")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(contractor, MediaType.APPLICATION_JSON));
        
                Assert.assertEquals(Created, response.getStatus());
                
                response = target.path(chatNamePath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(chatName, MediaType.APPLICATION_JSON));
                
                Assert.assertEquals(Created, response.getStatus());
        
        ChatDTO  chatNameTest = (ChatDTO) response.readEntity(ChatDTO.class);
                
        Assert.assertEquals(chatName.getId(), chatNameTest.getId());
        Assert.assertEquals(chatName.getCreationDate(), chatNameTest.getCreationDate());
    }
    
    @Test
    @InSequence(2)
    public void getChatNameById() {
        Cookie cookieSessionId = login(username, password);
        ChatDTO dto =oraculo.get(0);
        String name= "CU"+dto.getIdCustomer()+"CO"+dto.getIdContractor();
        
        ChatDTO chatNameTest = target.path(chatNamePath)
                .path(name)
                .request().cookie(cookieSessionId).get(ChatDTO.class);
        chatNameTest.getName();
        Assert.assertEquals(chatNameTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(chatNameTest.getCreationDate(), oraculo.get(0).getCreationDate());
        Assert.assertEquals(chatNameTest.getName(), name);
    }
}
