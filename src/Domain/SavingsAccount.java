/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LuisGa && Sebas
 */
public final class SavingsAccount extends Account {

    private float interestRate;           //Tasa de interes *Puede cambiar.
    private char state;        //Almacena el estado de la cuenta (activa/inactiva)
    private float balance;

    public SavingsAccount(char currency, Date openingDate, float balance, String clientId, float interestRate) {
        super(currency, openingDate, clientId);
        this.interestRate = interestRate;
        this.balance = balance;
        this.state = 'A';
    }

    public String setInterestRate(float interestRate) {
        //Solo se puede cambiar la taza de interés cada 6 meses.
        this.interestRate = interestRate;

        return "tasa de interés actualizada";

    }

    public void setState(Character state) {
        
            this.state = state ;
       
    }

    public String deposit(float amount) {
        if (state == 'A') {
            this.balance = (this.balance + amount);
            return "Monto depositado con exito";
        } else {
            return "La transacción no se pudo realizar porque la cuenta está inactiva";
        }
    }

    public String withdrawal(float amount) {
        if (state == 'A') {
            if (this.balance >= amount) {
                this.balance = (this.balance - amount);
                return "Monto retirado con exito";
            } else {
                return "El monto solicitado supera el saldo actual";
            }
        } else {
            return "La transacción no se pudo realizar porque la cuenta está inactiva";
        }
    }

    public float getInterestRate() {
        return interestRate;
    }

    public char getState() {
        return state;
    }

    public float getBalance() {
        return balance;
    }
    
     public String[] dataName() {
        String[] dataName = {"id", "currency", "openingDate", "clientID", "interestRate", "state","balance"};
        return dataName;
    }

    public String[] data() {
        
        String[] data = {String.valueOf(super.getId()), String.valueOf(super.getCurrency()), new SimpleDateFormat("dd-MM-yyyy").format(super.getOpeningDate()), super.getClientId(), String.valueOf(interestRate), String.valueOf(state),String.valueOf(balance)};
        return data;
    }

    @Override
    public String toString() {
        
            return "AHORROS - ID: "+String.valueOf(super.getId())+"  Tipo de moneda: "+ String.valueOf(super.getCurrency())+"  Fecha de apertura: "+  new SimpleDateFormat("dd-MM-yyyy").format(super.getOpeningDate()) +"  Tasa de interes: "+ String.valueOf(interestRate)+"  Estado: "+ String.valueOf(state)+"  Saldo: "+String.valueOf(balance);
        
       
    }
    
    

}
