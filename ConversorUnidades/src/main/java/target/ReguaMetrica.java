/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package target;

/**
 *
 * @author gabriella
 */
public class ReguaMetrica implements IMedidaMetrica {

    private double metros;

    public ReguaMetrica(double metros) {
        this.metros = metros;
    }

    @Override
    public double getDistanciaEmMetros() {
        return metros;
    }
}
