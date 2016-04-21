package co.edu.uniandes.csw.homeservices.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import co.edu.uniandes.csw.homeservices.services.SkillService;
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
public class SkillTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String skillPath = "skills";
    private final static List<SkillDTO> oraculo = new ArrayList<>();
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
                .addPackage(SkillService.class.getPackage())
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
            SkillDTO skill = factory.manufacturePojo(SkillDTO.class);
            skill.setId(i + 1L);

            oraculo.add(skill);

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
    public void createSkillTest() throws IOException {
        SkillDTO skill = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(skillPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(skill, MediaType.APPLICATION_JSON));
        SkillDTO  skillTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(skill.getId(), skillTest.getId());
        Assert.assertEquals(skill.getName(), skillTest.getName());
        Assert.assertEquals(skill.getDescription(), skillTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getSkillById() {
        Cookie cookieSessionId = login(username, password);
        SkillDTO skillTest = target.path(skillPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(SkillDTO.class);
        Assert.assertEquals(skillTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(skillTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(skillTest.getDescription(), oraculo.get(0).getDescription());
    }

    @Test
    @InSequence(3)
    public void listSkillTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(skillPath)
                .request().cookie(cookieSessionId).get();
        String listSkill = response.readEntity(String.class);
        List<SkillDTO> listSkillTest = new ObjectMapper().readValue(listSkill, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listSkillTest.size());
    }

    @Test
    @InSequence(4)
    public void updateSkillTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        SkillDTO skill = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SkillDTO skillChanged = factory.manufacturePojo(SkillDTO.class);
        skill.setName(skillChanged.getName());
        skill.setDescription(skillChanged.getDescription());
        Response response = target.path(skillPath).path(skill.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(skill, MediaType.APPLICATION_JSON));
        SkillDTO skillTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(skill.getName(), skillTest.getName());
        Assert.assertEquals(skill.getDescription(), skillTest.getDescription());
    }

    @Test
    @InSequence(5)
    public void deleteSkillTest() {
        Cookie cookieSessionId = login(username, password);
        SkillDTO skill = oraculo.get(0);
        Response response = target.path(skillPath).path(skill.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
