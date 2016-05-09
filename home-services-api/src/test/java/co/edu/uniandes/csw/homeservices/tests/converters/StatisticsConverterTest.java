/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.tests.converters;

import co.edu.uniandes.csw.homeservices.converters.ServiceRequestConverter;
import co.edu.uniandes.csw.homeservices.entities.StatisticDTO;
import java.util.ArrayList;
import java.util.List;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *  Clase que permite crear pruebas unitarias para 
 * los converters de las statistics
 * @author jhon
 */
@RunWith(Arquillian.class)
public class StatisticsConverterTest {
    
    private final PodamFactory factory = new PodamFactoryImpl();
    
    /**
     * Metodo que permite inicializar los archivos necesarios 
     * al usar las pruebas unitarias con podam
     * @return JavaArchive empaquetado de archivos necesarios 
     * para las pruebas
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(StatisticDTO.class.getPackage())
                .addPackage(co.edu.uniandes.csw.homeservices.dtos.StatisticDTO.class.getPackage())
                .addPackage(ServiceRequestConverter.class.getPackage());
    }
    
    /**
     * Metodo que nos permite realizar prueba unitaria
     * del metodo que convierte los statisticsDTO de entity
     * a dto de la capa API
     */    
    @Test
    public void statisticsEntityToStatisticsDto() {
        
        List<StatisticDTO> statisticsEntities = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            StatisticDTO dto = factory.manufacturePojo(StatisticDTO.class);
            dto.setName("name ".concat(String.valueOf(i)));
            statisticsEntities.add(dto);
        }
        
        List<co.edu.uniandes.csw.homeservices.dtos.StatisticDTO> statisticsDto = 
                ServiceRequestConverter.listEntityToDto(statisticsEntities);
        
        Assert.assertNotNull(statisticsDto);
        Assert.assertEquals(statisticsEntities.get(0).getName(), statisticsDto.get(0).getName());
    }
    
}
