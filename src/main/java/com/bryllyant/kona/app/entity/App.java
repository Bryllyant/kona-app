package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KApp.Type;
import java.io.Serializable;

public class App extends KBaseApp implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.type
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    private Type type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__app
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.type
     *
     * @return the value of kona__app.type
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.type
     *
     * @param type the value for kona__app.type
     *
     * @mbg.generated Fri Mar 16 16:32:01 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }
}