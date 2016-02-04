package co.edu.uniandes.csw.homeservices.tests;

import co.edu.uniandes.csw.auth.model.UserDTO;
import co.edu.uniandes.csw.auth.security.JWT;
import co.edu.uniandes.csw.homeservices.dtos.ContractorDTO;
import co.edu.uniandes.csw.homeservices.dtos.WorkExperienceDTO;
import co.edu.uniandes.csw.homeservices.dtos.SkillDTO;
import co.edu.uniandes.csw.homeservices.services.ContractorService;
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
public class ContractorTest {

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();
    private final String contractorPath = "contractors";
    private final static List<ContractorDTO> oraculo = new ArrayList<>();
    private final String skillsPath = "skills";
    private final static List<SkillDTO> oraculoSkills = new ArrayList<>();
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
                .addPackage(ContractorService.class.getPackage())
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
            ContractorDTO contractor = factory.manufacturePojo(ContractorDTO.class);
            contractor.setId(i + 1L);
            List<WorkExperienceDTO> workExperiencesList = new ArrayList<>();
            for (int j = 0; j < 5; j++)
            {
                WorkExperienceDTO workExperiences = factory.manufacturePojo(WorkExperienceDTO.class);
                workExperiences.setId(i + 1L);
                workExperiencesList.add(workExperiences);
            }

            contractor.setWorkExperiences(workExperiencesList);

            oraculo.add(contractor);

            SkillDTO skills = factory.manufacturePojo(SkillDTO.class);
            skills.setId(i + 1L);
            oraculoSkills.add(skills);
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
    public void createContractorTest() throws IOException {
        ContractorDTO contractor = oraculo.get(0);
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(contractorPath)
                .request().cookie(cookieSessionId)
                .post(Entity.entity(contractor, MediaType.APPLICATION_JSON));
        ContractorDTO  contractorTest = (ContractorDTO) response.readEntity(ContractorDTO.class);
        Assert.assertEquals(contractor.getId(), contractorTest.getId());
        Assert.assertEquals(contractor.getName(), contractorTest.getName());
        Assert.assertEquals(contractor.getLastName(), contractorTest.getLastName());
        Assert.assertEquals(contractor.getDocument(), contractorTest.getDocument());
        Assert.assertEquals(Created, response.getStatus());
    }

    @Test
    @InSequence(2)
    public void getContractorById() {
        Cookie cookieSessionId = login(username, password);
        ContractorDTO contractorTest = target.path(contractorPath)
                .path(oraculo.get(0).getId().toString())
                .request().cookie(cookieSessionId).get(ContractorDTO.class);
        Assert.assertEquals(contractorTest.getId(), oraculo.get(0).getId());
        Assert.assertEquals(contractorTest.getName(), oraculo.get(0).getName());
        Assert.assertEquals(contractorTest.getLastName(), oraculo.get(0).getLastName());
        Assert.assertEquals(contractorTest.getDocument(), oraculo.get(0).getDocument());
    }

    @Test
    @InSequence(3)
    public void listContractorTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        Response response = target.path(contractorPath)
                .request().cookie(cookieSessionId).get();
        String listContractor = response.readEntity(String.class);
        List<ContractorDTO> listContractorTest = new ObjectMapper().readValue(listContractor, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listContractorTest.size());
    }

    @Test
    @InSequence(4)
    public void updateContractorTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ContractorDTO contractor = oraculo.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ContractorDTO contractorChanged = factory.manufacturePojo(ContractorDTO.class);
        contractor.setName(contractorChanged.getName());
        contractor.setLastName(contractorChanged.getLastName());
        contractor.setDocument(contractorChanged.getDocument());
        Response response = target.path(contractorPath).path(contractor.getId().toString())
                .request().cookie(cookieSessionId).put(Entity.entity(contractor, MediaType.APPLICATION_JSON));
        ContractorDTO contractorTest = (ContractorDTO) response.readEntity(ContractorDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(contractor.getName(), contractorTest.getName());
        Assert.assertEquals(contractor.getLastName(), contractorTest.getLastName());
        Assert.assertEquals(contractor.getDocument(), contractorTest.getDocument());
    }

    @Test
    @InSequence(9)
    public void deleteContractorTest() {
        Cookie cookieSessionId = login(username, password);
        ContractorDTO contractor = oraculo.get(0);
        Response response = target.path(contractorPath).path(contractor.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }

    @Test
    @InSequence(5)
    public void addSkillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO skills = oraculoSkills.get(0);
        ContractorDTO contractor = oraculo.get(0);


        Response response = target.path("skills")
                .request().cookie(cookieSessionId)
                .post(Entity.entity(skills, MediaType.APPLICATION_JSON));

        SkillDTO skillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(skills.getId(), skillsTest.getId());
        Assert.assertEquals(skills.getName(), skillsTest.getName());
        Assert.assertEquals(skills.getDescription(), skillsTest.getDescription());
        Assert.assertEquals(Created, response.getStatus());

        response = target.path(contractorPath).path(contractor.getId().toString())
                .path(skillsPath).path(skills.getId().toString())
                .request().cookie(cookieSessionId)
                .post(Entity.entity(skills, MediaType.APPLICATION_JSON));

        skillsTest = (SkillDTO) response.readEntity(SkillDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(skills.getId(), skillsTest.getId());
    }

    @Test
    @InSequence(6)
    public void listSkillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        ContractorDTO contractor = oraculo.get(0);

        Response response = target.path(contractorPath)
                .path(contractor.getId().toString())
                .path(skillsPath)
                .request().cookie(cookieSessionId).get();

        String skillsList = response.readEntity(String.class);
        List<SkillDTO> skillsListTest = new ObjectMapper().readValue(skillsList, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, skillsListTest.size());
    }

    @Test
    @InSequence(7)
    public void getSkillsTest() throws IOException {
        Cookie cookieSessionId = login(username, password);
        SkillDTO skills = oraculoSkills.get(0);
        ContractorDTO contractor = oraculo.get(0);

        SkillDTO skillsTest = target.path(contractorPath)
                .path(contractor.getId().toString()).path(skillsPath)
                .path(skills.getId().toString())
                .request().cookie(cookieSessionId).get(SkillDTO.class);

        Assert.assertEquals(skills.getId(), skillsTest.getId());
        Assert.assertEquals(skills.getName(), skillsTest.getName());
        Assert.assertEquals(skills.getDescription(), skillsTest.getDescription());
    }

    @Test
    @InSequence(8)
    public void removeSkillsTest() {
        Cookie cookieSessionId = login(username, password);

        SkillDTO skills = oraculoSkills.get(0);
        ContractorDTO contractor = oraculo.get(0);

        Response response = target.path(contractorPath).path(contractor.getId().toString())
                .path(skillsPath).path(skills.getId().toString())
                .request().cookie(cookieSessionId).delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
