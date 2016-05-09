/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.homeservices.entities;

/**
 *
 * @author jhon
 */
public class StatisticDTO {
    
    private String name;
    private long statistic;

    /**
     * Constructor por defecto
     */
    public StatisticDTO() {
    }

    /**
     * Constructor que posee ambos parametros
     * @param name
     * @param statistic 
     */
    public StatisticDTO(String name, long statistic) {
        this.name = name;
        this.statistic = statistic;
    }

 // ------------------------ GETTERS AND SETTERS ----------------------------   
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStatistic() {
        return statistic;
    }

    public void setStatistic(long statistic) {
        this.statistic = statistic;
    }
    
    
    
}
