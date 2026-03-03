/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import adaptee.IMedidaImperial;
import target.IMedidaMetrica;

/**
 *
 * @author gabriella
 */
public class UnidadeAdapter implements IMedidaMetrica {

    private IMedidaImperial sistemaImperial;

    public UnidadeAdapter(IMedidaImperial sistemaImperial) {
        this.sistemaImperial = sistemaImperial;
    }

    @Override
    public double getDistanciaEmMetros() {
        double polegadas = sistemaImperial.getDistanciaEmPolegadas();
        return polegadas * 0.0254;
    }
}
