package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.KPayment.Type;
import java.io.Serializable;

public class Purchase extends KBasePurchase implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__purchase.payment_type
     *
     * @mbg.generated Sat Mar 17 14:03:15 EDT 2018
     */
    private Type paymentType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__purchase
     *
     * @mbg.generated Sat Mar 17 14:03:15 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__purchase.payment_type
     *
     * @return the value of kona__purchase.payment_type
     *
     * @mbg.generated Sat Mar 17 14:03:15 EDT 2018
     */
    public Type getPaymentType() {
        return paymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__purchase.payment_type
     *
     * @param paymentType the value for kona__purchase.payment_type
     *
     * @mbg.generated Sat Mar 17 14:03:15 EDT 2018
     */
    public void setPaymentType(Type paymentType) {
        this.paymentType = paymentType;
    }
}