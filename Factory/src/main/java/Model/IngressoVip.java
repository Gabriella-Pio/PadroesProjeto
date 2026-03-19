package Model;

public class IngressoVip extends Ingresso {

    public IngressoVip(String evento, double precoBase) {
        super(evento, precoBase);
    }

    @Override
    public double calcularValorFinal() {
        return precoBase * 1.5;
    }

    @Override
    public String getDescricaoTipo() {
        return "VIP (Acesso ao Camarote)";
    }
}