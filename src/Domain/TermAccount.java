/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author LuisGa && Sebas
 */
public final class TermAccount extends Account {

    private final float interestRate;               //Tasa de interes fija.(FINAL)
    private final int term;                         //Plazo en meses.
    private float startingAmount;             //Monto inicial.

    public TermAccount(char currency, Date openingDate, String clientId, float amount, float interestRate, int term) {
        super(currency, openingDate, clientId);
        this.interestRate = interestRate;
        this.startingAmount = (amount);               //El depósito inicial debe definirse al crear la cuenta.
        this.term = term;
    }

    public String projection() {                     //Proyección del monto final.
        return "Monto acumulado al final de plazo:" + (startingAmount + ((startingAmount * interestRate) * term));
    }

    public float getInterestRate() {
        return interestRate;
    }

    public float getStartingAmount() {
        return startingAmount;
    }
    
     public String[] dataName() {
        String[] dataName = {"id", "currency", "openingDate", "clientID", "interestRate", "startingAmount","term"};
        return dataName;
    }

    public String[] data() {
        
        String[] data = {String.valueOf(super.getId()), String.valueOf(super.getCurrency()), new SimpleDateFormat("dd-MM-yyyy").format(super.getOpeningDate()), super.getClientId(), String.valueOf(interestRate), String.valueOf(startingAmount),String.valueOf(term)};
        return data;
    }

    @Override
    public String toString() {
        
        return "A PLAZO - ID: "+String.valueOf(super.getId())+"  Tipo de moneda: "+ String.valueOf(super.getCurrency())+"  Fecha de apertura: "+ new SimpleDateFormat("dd-MM-yyyy").format(super.getOpeningDate()) +"  Tasa de interes: "+ String.valueOf(interestRate)+"  Monto inicial: "+ String.valueOf(startingAmount)+"  Plazo: "+String.valueOf(term);
    }

    
}
